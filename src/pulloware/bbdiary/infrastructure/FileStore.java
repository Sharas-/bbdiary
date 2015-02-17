package pulloware.bbdiary.infrastructure;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStore
{
    public static void writeFile(String path, String content) throws IOException
    {
        File f = new File(path);
        f.getParentFile().mkdirs();
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        try
        {
            fw.write(content);
            fw.flush();
        } finally
        {
            fw.close();
        }
    }

    public static String readFile(String path) throws IOException
    {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        char[] buf = new char[(int) file.length()];
        fr.read(buf);
        fr.close();
        return new String(buf);
    }

    public static boolean deleteDir(String path)
    {
        return deleteDir(new File(path));
    }

    private static boolean deleteDir(File file)
    {
        if (file == null)
        {
            return false;
        }
        if (!file.isDirectory())
        {
            return false;
        }
        File[] flist = file.listFiles();
        if (flist != null && flist.length > 0)
        {
            for (File f : flist)
            {
                if (!deleteDir(f))
                {
                    return false;
                }
            }
        }
        return file.delete();
    }
}
