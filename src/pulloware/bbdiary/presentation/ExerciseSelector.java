package pulloware.bbdiary.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import pulloware.bbdiary.R;
import pulloware.bbdiary.domain.Exercise;
import pulloware.bbdiary.domain.RepWeight;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sharas on 6/23/15.
 */
public class ExerciseSelector extends Fragment
{
    View view;

    int[][] entryIds =
        {{R.id.tbExercise1, R.id.tbReps1, R.id.tbWeight1},
            {R.id.tbExercise2, R.id.tbReps2, R.id.tbWeight2},
            {R.id.tbExercise3, R.id.tbReps3, R.id.tbWeight3}};

    private Collection<Exercise> exercises;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.exercise_selector, container, false);
        return view;
    }

    public Collection<Exercise> getExercises()
    {
        ArrayList<Exercise> exercises = new ArrayList<Exercise>(3);
        String exercise;
        float weight;
        int reps;
        for (int i = 0; i < 3; ++i)
        {
            exercise = getEntryText(entryIds[i][0]);
            if (exercise.length() > 0)
            {
                reps = Integer.parseInt(getEntryText(entryIds[i][1]));
                weight = Float.parseFloat(getEntryText(entryIds[i][2]));
                exercises.add(new Exercise(exercise, new RepWeight(reps, weight)));
            }
        }
        return exercises;
    }

    public void displayExercises(Collection<Exercise> exercises)
    {
        this.exercises = exercises;
        int rowIdx = 0;
        for (Exercise e : exercises)
        {
            setEntryText(entryIds[rowIdx][0], e.getName());
            setEntryText(entryIds[rowIdx][1], "" + e.getEffort().getReps());
            setEntryText(entryIds[rowIdx][2], "" + e.getEffort().getWeight());
            ++rowIdx;
        }
    }

    private String getEntryText(int txtId)
    {
        return ((EditText) view.findViewById(txtId)).getText().toString().trim();
    }

    private void setEntryText(int txtId, String text)
    {
        ((EditText) view.findViewById(txtId)).setText(text);
    }
}
