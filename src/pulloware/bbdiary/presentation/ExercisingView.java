package pulloware.bbdiary.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import pulloware.bbdiary.R;
import pulloware.bbdiary.domain.Duration;

import java.util.Date;

/**
 * Created by sharas on 6/15/15.
 */
public class ExercisingView extends Fragment
{
    private Date setStart;
    Chronometer tw;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.exercising_view, container, false);
        tw = (Chronometer) v.findViewById(R.id.timeWidget);
        tw.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onDoneSet();
            }
        });
        return v;
    }

    public void  onResume()
    {
        super.onResume();
        this.setStart = new Date();
        tw.setBase(SystemClock.elapsedRealtime());
        tw.start();
    }

    public void onDoneSet()
    {
        ((MainActivity) getActivity()).doneSet(new Duration(this.setStart, new Date()));
    }

}
