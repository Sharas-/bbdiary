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
import pulloware.bbdiary.domain.Set;

import java.util.ArrayList;

/**
 * Created by sharas on 6/23/15.
 */
public class SetEditor extends Fragment
{
    View view;

    int[][] entryIds =
        {{R.id.tbExercise1, R.id.tbReps1, R.id.tbWeight1},
            {R.id.tbExercise2, R.id.tbReps2, R.id.tbWeight2},
            {R.id.tbExercise3, R.id.tbReps3, R.id.tbWeight3}};

    private Set set;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.set_editor, container, false);
        return view;
    }

    public Set getSet()
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
        return new Set(this.set.timeFrame(), exercises);
    }

    public void displaySet(Set s)
    {
        this.set = s;
        int rowIdx = 0;
        for (Exercise e : s.getExercises())
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
