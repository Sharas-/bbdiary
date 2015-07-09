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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sharas on 7/3/15.
 */
public class WorkoutFinish extends Fragment
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.workout_finish, container, false);
        Button b = (Button) v.findViewById(R.id.btnExportWorkout);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onExportWorkout();
            }
        });
        return v;
    }

    public void onExportWorkout()
    {
        ((MainActivity) getActivity()).exportWorkout();
    }
}
