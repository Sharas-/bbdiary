package pulloware.bbdiary.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pulloware.bbdiary.domain.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by sharas on 6/28/15.
 */
public class WorkoutSerializer
{
    private static class Token
    {
        public static final String DATE_FROM = "tf1";
        public static final String DATE_TO = "tf2";
        public static final String EXERCISES = "ex";
        public static final String SEPARATOR = "|";
    }

    public static String serialize(Workout w) throws Exception
    {
        JSONArray values = new JSONArray();
        for (Set s : w)
        {
            values.put(serializeSet(s));
        }
        return values.toString();
    }

    public static Workout deserialize(String s) throws Exception
    {
        JSONArray serializedSets = new JSONArray(s);
        ArrayList<Set> sets = new ArrayList<Set>(serializedSets.length());
        for (int i = 0; i < serializedSets.length(); ++i)
        {
            sets.add(deserializeSet(serializedSets.getJSONObject(i)));
        }
        return new Workout(sets);
    }

    private static Object serializeSet(Set s) throws JSONException
    {
        JSONObject jsonFormatter = new JSONObject();
        jsonFormatter.put(
            Token.DATE_FROM, s.timeFrame().getFrom().getTime()).put(
            Token.DATE_TO, s.timeFrame().getTo().getTime()).put(
            Token.EXERCISES, serializeExercises(s.getExercises()));
        return jsonFormatter;
    }

    private static Set deserializeSet(JSONObject serialized) throws JSONException
    {
        Date t1 = new Date(serialized.getLong(Token.DATE_FROM));
        Date t2 = new Date(serialized.getLong(Token.DATE_TO));
        JSONArray serializedExercises = serialized.getJSONArray(Token.EXERCISES);
        ArrayList<Exercise> exercises = new ArrayList<Exercise>(serializedExercises.length());
        for (int i = 0; i < serializedExercises.length(); ++i)
        {
            exercises.add(deserializeExercise(serializedExercises.getString(i)));
        }
        return new Set(new TimeFrame(t1, t2), exercises);
    }

    private static Object serializeExercises(Collection<Exercise> exercises)
    {
        JSONArray values = new JSONArray();
        for (Exercise e : exercises)
        {
            values.put(serializeExercise(e));
        }
        return values;
    }

    private static String serializeExercise(Exercise e)
    {
        RepWeight effort = e.getEffort();
        return e.getName() + Token.SEPARATOR + effort.getReps() + Token.SEPARATOR + effort.getWeight();
    }

    private static Exercise deserializeExercise(String serialized)
    {
        String[] values = serialized.split(Token.SEPARATOR);
        String name = values[0];
        int reps = Integer.parseInt(values[1]);
        float weight = Float.parseFloat(values[2]);
        return new Exercise(name, new RepWeight(reps, weight));
    }
}
