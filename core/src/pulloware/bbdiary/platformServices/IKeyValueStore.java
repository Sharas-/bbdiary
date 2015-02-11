package pulloware.bbdiary.platformServices;

import java.io.*;

public class IKeyValueStore
{
    public void Save(String key, String value)
    {
        Writer writer = null;
        try
        {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("key"+".dat"), "utf-8"));
            writer.write(value);
        }
        catch (IOException ex)
        {

        }
        finally
        {
            try {writer.close();} catch (Exception ex) {}
        }
    }
}
