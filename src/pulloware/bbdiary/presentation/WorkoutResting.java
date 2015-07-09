package pulloware.bbdiary.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ViewSwitcher;
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
    Chronometer tw;
    ViewSwitcher viewSwitcher;
    SetEditor setEditor;
    private Set finishedSet;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.workout_resting, container, false);
        setEditor = new SetEditor();
        setEditor.setSet(this.finishedSet);
        getFragmentManager().beginTransaction().replace(R.id.setEditorPlaceholder, setEditor).commit();
        tw = (Chronometer) view.findViewById(R.id.timeWidget);
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.restingViewSwitcher);
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
        b = (Button) view.findViewById(R.id.btnFinishWorkout);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onFinishWorkout();
            }
        });
        return view;
    }

    private void onFinishWorkout()
    {
        getCoordinator().finishWorkout();
    }

    public void onResume()
    {
        super.onResume();
        tw.setBase(SystemClock.elapsedRealtime());
        tw.start();
    }

    public void onSaveSet()
    {
        getCoordinator().saveSet(this.setEditor.getSet());
        ExerciseSelector es = new ExerciseSelector();
        es.setExercises(finishedSet.getExercises());
        getFragmentManager().beginTransaction().replace(R.id.exerciseSelectorPlaceholder, es).commit();
        //TODO: populate exercises from workout navigator
        viewSwitcher.showNext();
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