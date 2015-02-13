package pulloware.bbdiary.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import pulloware.bbdiary.R;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    final int FOLDER_SELECTED = 101;
    TextView txt;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        txt = (TextView) findViewById(R.id.txtView);
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
        Log.d("dirs", " getDataDirectory(): " +  Environment.getDataDirectory().getAbsolutePath());
        Log.d("dirs", " getExternalStorageDirectory(): " +  Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.d("dirs", " getRootDirectory(): " +  Environment.getRootDirectory().getAbsolutePath());
        Log.d("dirs", " getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM): " +  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));

        Log.d("dirs", " getFilesDir().getAbsolutePath(): " +
            getFilesDir().getAbsolutePath());
        Log.d("dirs", " getApplicationContext().getApplicationInfo().name: " +
            getApplicationContext().getApplicationInfo());

    }
}

