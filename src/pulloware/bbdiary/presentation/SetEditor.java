package pulloware.bbdiary.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pulloware.bbdiary.R;
import pulloware.bbdiary.domain.Set;

/**
 * Created by sharas on 6/23/15.
 */
public class SetEditor extends Fragment
{
    private TextView lblSetDuration;
    private ExerciseSelector exSelector;
    private Set set;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.set_editor, container, false);
        lblSetDuration = (TextView) view.findViewById(R.id.lblSetDuration);
        exSelector = (ExerciseSelector) getFragmentManager().findFragmentById(R.id.setEditorExerciseSelector);
        return view;
    }

    public Set getSet()
    {
        return new Set(this.set.timeFrame(), exSelector.getExercises());
    }

    public void displaySet(Set s)
    {
        this.set = s;
        this.exSelector.displayExercises(s.getExercises());
    }
}
