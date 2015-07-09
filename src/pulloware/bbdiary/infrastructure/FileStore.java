package pulloware.bbdiary.infrastructure;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileStore
{


    private static File writeFile(File outFile, String content) throws IOException
    {
        outFile.getParentFile().mkdirs();
        outFile.createNewFile();
        FileWriter fw = new FileWriter(outFile);
        try
        {
            fw.write(content);
            fw.flush();
        } finally
        {
            fw.close();
        }
        return outFile;
    }

    public static String externalDir()
    {
        return new File(Environment.getExternalStorageDirectory(), "Workouts").getAbsolutePath();
    }

    public static String writeExternal(FileName file, String content) throws IOException
    {
        File outFile = new File(externalDir(), file.toString());
        return writeFile(outFile, content).getPath();
    }
}
