package pulloware.bbdiary.test.platformIndependent;

import junit.framework.TestCase;
import pulloware.bbdiary.domain.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by sharas on 6/26/15.
 */
public class WorkoutTest extends TestCase
{
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss", Locale.ENGLISH);
    List<Exercise> exercises = Arrays.asList(new Exercise("pull-up", new RepWeight(10, 80)));

    public void testSetsOrderedByTime() throws Exception
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        TimeFrame tf2 = new TimeFrame(df.parse("2015/01/22 14:25:11"), df.parse("2015/01/22 14:25:20"));
        TimeFrame tf3 = new TimeFrame(df.parse("2015/01/22 14:26:00"), df.parse("2015/01/22 14:26:10"));
        Set s1 = new Set(tf1, exercises);
        Set s2 = new Set(tf2, exercises);
        Set s3 = new Set(tf3, exercises);
        List<Set> unordered = Arrays.asList(s3, s1, s2);
        List<Set> ordered = Arrays.asList(s1, s2, s3);
        Workout w = new Workout(unordered);
        assertEquals(ordered, w.getSets());
    }

    public void testOverlappingSetsNotAllowed() throws Exception
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        createWorkoutWithSetOverlap(tf1, tf1);
    }

    public void testPartlyOverlappingSetsNotAllowed() throws Exception
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        TimeFrame tf2 = new TimeFrame(df.parse("2015/01/22 14:25:09"), df.parse("2015/01/22 14:25:19"));
        createWorkoutWithSetOverlap(tf1, tf2);
    }

    private void createWorkoutWithSetOverlap(TimeFrame tf1, TimeFrame tf2)
    {
        Set s1 = new Set(tf1, exercises);
        Set s2 = new Set(tf2, exercises);
        try
        {
            Workout w = new Workout(Arrays.asList(s1, s2));
            fail(SetsOverlapException.class.getSimpleName() + " wasn't thrown");
        } catch (SetsOverlapException e)
        {
            //OK
        }
    }
}
