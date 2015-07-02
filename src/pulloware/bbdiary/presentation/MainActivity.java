package pulloware.bbdiary.presentation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import pulloware.bbdiary.R;
import pulloware.bbdiary.application.WorkoutBuilder;
import pulloware.bbdiary.domain.Exercise;
import pulloware.bbdiary.domain.Set;

import java.util.Collection;

/**
 * Mediator for workout activities
 */
public class MainActivity extends Activity
{
    private WorkoutBuilder workoutBuilder;

    public MainActivity()
    {
        this.workoutBuilder = new WorkoutBuilder();
    }

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        displayView(new WorkoutStart());
    }

//    public void onClick(View v) throws Exception
//    {
//        bulletin.setText("paspausta!!");
//        final String sstate = Environment.getExternalStorageState();
//        bulletin.setText(sstate);
//        if (!Environment.MEDIA_MOUNTED.equals(sstate))
//        {
//            bulletin.setText("sd card not accessible! " + "state is: " + sstate);
//            return;
//        }
//        File dir = new File(Environment.getExternalStorageDirectory(), "BBDiary");
//        if (!dir.mkdirs())
//        {
//            bulletin.setText("couldn't create BBDiary dir on sd card");
//        }
//        File outFile = new File(dir, "out.bulletin");
//        if (!outFile.createNewFile())
//        {
//            bulletin.setText("couldn't out file in BBDiary folder");
//        }
//        FileWriter f = new FileWriter(outFile);
//        f.write("kontentas nu");
//        f.flush();
//        f.close();
//
//
//    Log.d("dirs", " getDataDirectory(): " + Environment.getDataDirectory().getAbsolutePath());
//    Log.d("dirs", " getExternalStorageDirectory(): " + Environment.getExternalStorageDirectory().getAbsolutePath());
//    Log.d("dirs", " getRootDirectory(): " + Environment.getRootDirectory().getAbsolutePath());
//    Log.d("dirs", " getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM): " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
//
//    Log.d("dirs", " getFilesDir().getAbsolutePath(): " +
//    getFilesDir().getAbsolutePath());
//    Log.d("dirs", " getApplicationContext().getApplicationInfo().name: " +
//    getApplicationContext().getApplicationInfo());

//    }

    public void startSet(Collection<Exercise> exercises)
    {
        WorkoutExercising ev = new WorkoutExercising();
        ev.setExercises(exercises);
        displayView(ev);
    }

    public void finishSet(Set s)
    {
        WorkoutResting rv = new WorkoutResting();
        rv.setFinishedSet(s);
        displayView(rv);
    }

    public void saveSet(Set s)
    {
        workoutBuilder.AddSet(s);
    }

    private void displayView(Fragment view)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, view);
        fragmentTransaction.commit();
    }
}

