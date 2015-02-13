package pulloware.bbdiary.infrastructure;

import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileStore
{
    private static App app = new App();

    private static class App extends Application
    {
    }

    public class ExternalStoreNotWritable extends IOException
    {
        private String storeState;

        ExternalStoreNotWritable(String storeState)
        {
            this.storeState = storeState;
        }

        public String getStoreState()
        {
            return storeState;
        }
    }

    public void WriteExternal(String fileName, String content) throws IOException
    {
        final String sstate = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(sstate))
        {
            throw new ExternalStoreNotWritable(sstate);
        }

        File dir = new File(Environment.getExternalStorageDirectory(), getAppLabel());
        dir.mkdirs();
        File f = new File(dir, fileName);
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        try
        {
            fw.write(content);
            fw.flush();
        }
        finally
        {
            fw.close();
        }
    }

    private String getAppLabel()
    {
        int stringId = app.getApplicationInfo().labelRes;
        return app.getString(stringId);
    }
}
