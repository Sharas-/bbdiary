package pulloware.bbdiary.test;

import android.test.AndroidTestCase;
import org.junit.After;
import org.junit.Test;
import pulloware.bbdiary.infrastructure.FileName;
import pulloware.bbdiary.infrastructure.FileStore;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileStoreTest extends AndroidTestCase
{
    @Test()
    public void testExternalDirCreatedOnWrite() throws IOException
    {
        deleteDir(FileStore.externalDir());
        assertFalse(new File(FileStore.externalDir()).exists());
        FileStore.writeExternal(new FileName("testfile.txt"), "content");
        assertTrue(new File(FileStore.externalDir()).exists());
    }

    @Test()
    public void testSameContentWrittenAndRead() throws IOException
    {
        String content = "expected content";
        String outFile = FileStore.writeExternal(new FileName("testfile.txt"), content);
        String readContent = readFile(outFile);
        assertEquals(readContent, content);
    }

    @Test()
    public void testFileOverwrittenWhenExists() throws IOException
    {
        FileName fname = new FileName("testfile.txt");
        String content = "expected content";
        String outFile1 = FileStore.writeExternal(fname, "old content");
        String outFile2 = FileStore.writeExternal(fname, content);
        assertEquals(outFile1, outFile2);
        String readContent = readFile(outFile2);
        assertEquals(readContent, content);
    }

    private String readFile(String path) throws IOException
    {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        char[] buf = new char[(int) file.length()];
        fr.read(buf);
        fr.close();
        return new String(buf);
    }

    private void deleteDir(String file)
    {
        deleteDir(new File(file));
    }

    private boolean deleteDir(File file)
    {
        File[] children = file.listFiles();
        if (children == null)
        {
            return false;
        }
        for (File f : children)
        {
            if (f.isDirectory())
            {
                deleteDir(f);
            }
            f.delete();
        }
        return file.delete();
    }

    @After
    public void tearDown()
    {
        deleteDir(FileStore.externalDir());
    }
}
