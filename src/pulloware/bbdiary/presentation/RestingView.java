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

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sharas on 6/15/15.
 */
public class RestingView extends Fragment
{
    Chronometer tw;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.resting_view, container, false);
        Button b = (Button) v.findViewById(R.id.btnStartSet);
        tw = (Chronometer) v.findViewById(R.id.timeWidget);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onStartSet();
            }
        });
        return v;
    }

    public void onResume()
    {
        super.onResume();
        tw.setBase(SystemClock.elapsedRealtime());
        tw.start();
    }

    public void onStartSet()
    {
        Collection<Exercise> exercises = new ArrayList<Exercise>(3);
        exercises.add(new Exercise("Squats", new RepWeight(10, 100)));
        exercises.add(new Exercise("Lunges", new RepWeight(8, 40)));
        ((MainActivity) getActivity()).startSet(exercises);
    }
}