package pulloware.bbdiary.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;
import pulloware.bbdiary.R;
import pulloware.bbdiary.domain.TimeFrame;
import pulloware.bbdiary.domain.Exercise;
import pulloware.bbdiary.domain.Set;

import java.util.Collection;
import java.util.Date;

/**
 * Created by sharas on 6/15/15.
 */
public class WorkoutExercising extends Fragment
{
    private Date setStart;
    Chronometer tw;
    Collection<Exercise> exercises;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.workout_exercising, container, false);
        tw = (Chronometer) v.findViewById(R.id.timeWidget);
        tw.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onDoneSet();
            }
        });
        TextView lblExercises = (TextView) v.findViewById(R.id.lblExercises);
        lblExercises.setText(formatExercisesText(this.exercises));
        return v;
    }

    private String formatExercisesText(Collection<Exercise> exercises)
    {
        String txt = "";
        for (Exercise e: exercises)
        {
            txt += e.getName() + " " + e.getEffort().getWeight() + "x" + e.getEffort().getReps() + "\n";
        }
        return txt;
    }

    public void onResume()
    {
        super.onResume();
        this.setStart = new Date();
        tw.stop();
        tw.setBase(SystemClock.elapsedRealtime());
        tw.start();
    }

    public void onDoneSet()
    {
        Set s = new Set(new TimeFrame(this.setStart, new Date()), this.exercises);
        ((MainActivity) getActivity()).finishSet(s);
    }

    public void setExercises(Collection<Exercise> exercises)
    {
        this.exercises = exercises;
    }
}
