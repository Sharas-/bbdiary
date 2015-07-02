package pulloware.bbdiary.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import pulloware.bbdiary.R;
import pulloware.bbdiary.domain.Exercise;
import pulloware.bbdiary.domain.RepWeight;
import pulloware.bbdiary.domain.Set;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sharas on 6/15/15.
 */
public class WorkoutResting extends Fragment
{
    View view;
    SetEditor setEditor;
    private Set finishedSet;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.workout_resting, container, false);
        setEditor = (SetEditor) getFragmentManager().findFragmentById(R.id.setEditor);
        setEditor.displaySet(this.finishedSet);
        Button b = (Button) view.findViewById(R.id.btnStartSet);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onStartSet();
            }
        });
        b = (Button) view.findViewById(R.id.btnSaveSet);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onSaveSet();
            }
        });
        return view;
    }

    public void onResume()
    {
        super.onResume();
        Chronometer tw = (Chronometer) view.findViewById(R.id.timeWidget);
        tw.setBase(SystemClock.elapsedRealtime());
        tw.start();
    }

    public void onSaveSet()
    {
        getCoordinator().saveSet(this.setEditor.getSet());
    }

    public void onStartSet()
    {
        //TODO: get exercises from workout navigator
        Collection<Exercise> exercises = new ArrayList<Exercise>(3);
        exercises.add(new Exercise("Squats", new RepWeight(10, 100)));
        exercises.add(new Exercise("Lunges", new RepWeight(8, 40)));
        getCoordinator().startSet(exercises);
    }

    public void setFinishedSet(Set s)
    {
        this.finishedSet = s;
    }

    private MainActivity getCoordinator()
    {
        return ((MainActivity) getActivity());
    }
}