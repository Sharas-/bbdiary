package pulloware.bbdiary.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pulloware.bbdiary.domain.*;
import pulloware.bbdiary.domain.Set;

import java.text.DateFormat;
import java.util.*;

public class WorkoutFormatter
{
    public static String toHumanReadable(Workout w)
    {
        return toHumanReadable(w, Locale.getDefault());
    }

    public static String toHumanReadable(Workout w, Locale l)
    {
        DateFormat fDateTime = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, l);
        DateFormat fTime = DateFormat.getTimeInstance(DateFormat.MEDIUM, l);
        String newLine = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append(fDateTime.format(w.timeFrame().getFrom()));
        sb.append(newLine);
        for (Set s : w)
        {
            sb.append(fTime.format(s.timeFrame().getFrom()));
            sb.append(newLine);
            for (Exercise e: s.getExercises())
            {
                sb.append("\t");
                sb.append(e.getName());
                sb.append(' ');
                RepWeight effort = e.getEffort();
                sb.append(effort.getReps());
                sb.append('x');
                sb.append(effort.getWeight());
                sb.append(newLine);
            }
            sb.append(fTime.format(s.timeFrame().getTo()));
            sb.append(newLine);
        }
        return sb.toString();
    }

    public static String toJSON(Workout w) throws JSONException
    {
        return JSONSerializer.serialize(w);
    }

    public static Workout fromJSON(String s) throws JSONException
    {
        return JSONSerializer.deserialize(s);
    }

    private static class JSONSerializer
    {
        private static class Token
        {
            public static final String DATE_FROM = "tf1";
            public static final String DATE_TO = "tf2";
            public static final String EXERCISES = "ex";
            public static final String SEPARATOR = ";";
        }

        public static Workout deserialize(String JSONString) throws JSONException
        {
            JSONArray serializedSets = new JSONArray(JSONString);
            ArrayList<Set> sets = new ArrayList<Set>(serializedSets.length());
            for (int i = 0; i < serializedSets.length(); ++i)
            {
                sets.add(deserializeSet(serializedSets.getJSONObject(i)));
            }
            return new Workout(sets);
        }

        public static String serialize(Workout w) throws JSONException
        {
            JSONArray values = new JSONArray();
            for (Set s : w)
            {
                values.put(serializeSet(s));
            }
            return values.toString();
        }

        private static JSONObject serializeSet(Set s) throws JSONException
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
}
