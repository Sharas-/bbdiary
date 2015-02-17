package pulloware.bbdiary.test;

import android.os.Environment;
import android.test.AndroidTestCase;
import org.junit.After;
import org.junit.Test;
import pulloware.bbdiary.infrastructure.FileStore;

import java.io.File;
import java.io.IOException;

public class FileStoreTest extends AndroidTestCase
{
    private final File testDir = new File(Environment.getExternalStorageDirectory(), "TestDir");

    @Test()
    public void testFileAndDirsCreatedOnWrite() throws IOException
    {
        String fileContent = "test content";
        File f = new File(new File(new File(testDir, "subfloder1"), "subfolder2"), "test1.txt");
        FileStore.writeFile(f.getAbsolutePath(), fileContent);
        assertTrue(f.exists());
    }

    @Test()
    public void testSameContentWrittenAndRead() throws IOException
    {
        String path = new File(testDir, "testfile.txt").getAbsolutePath();
        checkSameContentWrittenAndRead(path, "test content");
    }

    @Test()
    public void testFileOverwrittenWhenExists() throws IOException
    {
        String path = new File(testDir, "testfile.txt").getAbsolutePath();
        checkSameContentWrittenAndRead(path, "old content");
        assertTrue(new File(path).exists());
        checkSameContentWrittenAndRead(path, "new content");
    }

    private void checkSameContentWrittenAndRead(String path, String content) throws IOException
    {
        FileStore.writeFile(path, content);
        String readContent = FileStore.readFile(path);
        assertEquals(readContent, content);
    }

    @After
    public void tearDown()
    {
        FileStore.deleteDir(testDir.getPath());
    }
}
