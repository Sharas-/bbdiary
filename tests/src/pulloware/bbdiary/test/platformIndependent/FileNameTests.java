package pulloware.bbdiary.test.platformIndependent;

import junit.framework.TestCase;
import pulloware.bbdiary.infrastructure.FileName;

import java.text.MessageFormat;

/**
 * Created by sharas on 7/5/15.
 */
public class FileNameTests extends TestCase
{
    public void testReservedSymbolsNotAllowed()
    {
        char[] bad_chars = FileName.RESERVED_CHARS.toCharArray();
        for (char c : bad_chars)
        {
            try
            {
                new FileName("fi" + c + "le.txt");
                fail("File Name shouldn't be successfully created with reserved char");
            } catch (IllegalArgumentException e)
            {
                //OK
            }
        }
    }

    public void testSanitationTrimsSpaces()
    {
        FileName sanitized = FileName.sanitize(" file.txt ");
        assertEquals(sanitized.toString(), "file.txt");
    }

    public void testSanitationReplacesReservedChars()
    {
        String separator = "_SNIP_";
        String badName = MessageFormat.format("fi{1}{0}{1}le.{1}{0}", FileName.RESERVED_CHARS, separator);
        FileName sanitized = FileName.sanitize(badName);
        String[] parts = sanitized.toString().split(separator);
        assertEquals(parts.length, 4);
        assertEquals(parts[1].length(), FileName.RESERVED_CHARS.length());
        assertEquals(parts[3].length(), FileName.RESERVED_CHARS.length());
    }

    public void testFileNameCannotStartWithSpace()
    {
        try
        {
            new FileName(" filename.txt");
            fail("File name starting with space shouldn't be successfully created");
        }
        catch(IllegalArgumentException e)
        {
            //OK
        }
    }
}
