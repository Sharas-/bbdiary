package pulloware.bbdiary.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import pulloware.bbdiary.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View v) throws Exception {
        TextView txt = (TextView) findViewById(R.id.txtView);
        txt.setText("paspausta!!");

        final String sstate = Environment.getExternalStorageState();
        txt.setText(sstate);
        if (!Environment.MEDIA_MOUNTED.equals(sstate)) {
            txt.setText("sd card not accessible! " + "state is: " + sstate);
            return;
        }
        File dir = new File(Environment.getExternalStorageDirectory(), "BBDiary");
        if(!dir.mkdirs())
        {
            txt.setText("couldn't create BBDiary dir on sd card");
        }
        File outFile = new File(dir, "out.txt");
        if(!outFile.createNewFile())
        {
            txt.setText("couldn't out file in BBDiary folder");
        }
        FileWriter f = new FileWriter(outFile);
        f.write("kontentas nu");
        f.flush();
        f.close();


    }
}

