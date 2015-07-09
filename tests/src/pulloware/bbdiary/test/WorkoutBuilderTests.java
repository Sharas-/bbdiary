package pulloware.bbdiary.test;

import junit.framework.TestCase;
import pulloware.bbdiary.application.WorkoutBuilder;
import pulloware.bbdiary.domain.Exercise;
import pulloware.bbdiary.domain.RepWeight;
import pulloware.bbdiary.domain.Set;
import pulloware.bbdiary.domain.TimeFrame;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by sharas on 7/8/15.
 */
public class WorkoutBuilderTests extends TestCase
{
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss", Locale.ENGLISH);

    public void testWorkoutExported() throws Exception
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        TimeFrame tf2 = new TimeFrame(df.parse("2015/01/22 14:25:11"), df.parse("2015/01/22 14:25:20"));
        TimeFrame tf3 = new TimeFrame(df.parse("2015/01/22 14:26:00"), df.parse("2015/01/22 14:26:10"));
        List<Exercise> exercises = Arrays.asList(new Exercise("pull-up", new RepWeight(10, 80)));
        WorkoutBuilder wb = new WorkoutBuilder();
        wb.addSet(new Set(tf1, exercises));
        wb.addSet(new Set(tf2, exercises));
        wb.addSet(new Set(tf3, exercises));
        String path = wb.exportWorkout("pull-ups");
        File f = new File(path);
        assertTrue(f.exists());
        assertTrue(f.length() > 0);
        f.delete();
    }
}
