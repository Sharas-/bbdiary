package pulloware.bbdiary.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.ViewFlipper;
import pulloware.bbdiary.R;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    private boolean exercising = false;
    TextView txt;
    private ViewFlipper viewFlipper;
    private float lastX;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        txt = (TextView) findViewById(R.id.txtView);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
    }

    public void onClick(View v) throws Exception
    {
        txt.setText("paspausta!!");
        final String sstate = Environment.getExternalStorageState();
        txt.setText(sstate);
        if (!Environment.MEDIA_MOUNTED.equals(sstate))
        {
            txt.setText("sd card not accessible! " + "state is: " + sstate);
            return;
        }
        File dir = new File(Environment.getExternalStorageDirectory(), "BBDiary");
        if (!dir.mkdirs())
        {
            txt.setText("couldn't create BBDiary dir on sd card");
        }
        File outFile = new File(dir, "out.txt");
        if (!outFile.createNewFile())
        {
            txt.setText("couldn't out file in BBDiary folder");
        }
        FileWriter f = new FileWriter(outFile);
        f.write("kontentas nu");
        f.flush();
        f.close();


    }

    public void onSelectFolder(View v) throws Exception
    {
        Log.d("dirs", " getDataDirectory(): " + Environment.getDataDirectory().getAbsolutePath());
        Log.d("dirs", " getExternalStorageDirectory(): " + Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.d("dirs", " getRootDirectory(): " + Environment.getRootDirectory().getAbsolutePath());
        Log.d("dirs", " getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM): " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));

        Log.d("dirs", " getFilesDir().getAbsolutePath(): " +
            getFilesDir().getAbsolutePath());
        Log.d("dirs", " getApplicationContext().getApplicationInfo().name: " +
            getApplicationContext().getApplicationInfo());

    }

    public void onTimerClick(View v)
    {
        Chronometer c = (Chronometer) findViewById(R.id.chronometer);
        if (exercising)
        {
            c.stop();
            exercising = false;
        } else
        {
            c.setBase(SystemClock.elapsedRealtime());
            txt.setText("" + c.getBase());
            c.start();
            exercising = true;
        }
    }

    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX)
                {
                    // If no more View/Child to flip
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Left and current Screen will go OUT from Right
                    viewFlipper.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_right);
                    // Show the next Screen
                    viewFlipper.showNext();
                }

                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    viewFlipper.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_left);
                    // Show The Previous Screen
                    viewFlipper.showPrevious();
                }
                break;
            }
        }
        return false;
    }
}

