package pulloware.bbdiary.test.platformIndependent;

import junit.framework.TestCase;
import org.junit.Test;
import pulloware.bbdiary.domain.TimeFrame;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by sharas on 6/26/15.
 */
public class TimeFrameTest extends TestCase
{
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss", Locale.ENGLISH);

    @Test()
    public void testNoOverlaps() throws Exception
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        TimeFrame tf2 = new TimeFrame(df.parse("2015/01/22 13:25:00"), df.parse("2015/01/22 13:25:10"));
        assertFalse(tf1.overlaps(tf2));
    }

    @Test()
    public void testFullyOverlaps() throws Exception
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        TimeFrame tf2 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        assertTrue(tf1.overlaps(tf2));
        assertTrue(tf2.overlaps(tf1));
    }

    @Test()
    public void testPartiallyOverlaps() throws Exception
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        TimeFrame tf2 = new TimeFrame(df.parse("2015/01/22 14:25:01"), df.parse("2015/01/22 14:25:11"));
        assertTrue(tf1.overlaps(tf2));
        assertTrue(tf2.overlaps(tf1));
    }

    @Test()
    public void testStartedAfter() throws Exception
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        TimeFrame tf2 = new TimeFrame(df.parse("2015/01/22 14:25:11"), df.parse("2015/01/22 14:25:12"));
        assertTrue(tf2.startedAfter(tf1));
        assertFalse(tf1.startedAfter(tf2));
    }
}
