package pulloware.bbdiary.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import pulloware.bbdiary.R;
import pulloware.bbdiary.domain.Exercise;
import pulloware.bbdiary.domain.RepWeight;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sharas on 6/20/15.
 */
public class WorkoutHome extends Fragment
{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.workout_home, container, false);
        Button b = (Button) v.findViewById(R.id.btnStartWorkout);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onStartWorkout();
            }
        });
        return v;
    }

    public void onStartWorkout()
    {
        Collection<Exercise> exercises = new ArrayList<Exercise>(3);
        exercises.add(new Exercise("Pull ups", new RepWeight(15, 0)));
        exercises.add(new Exercise("Pull ups", new RepWeight(8, 0)));
        ((MainActivity) getActivity()).startWorkout(exercises);
    }
}
