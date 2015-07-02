package pulloware.bbdiary.test;

import junit.framework.TestCase;
import pulloware.bbdiary.domain.*;
import pulloware.bbdiary.infrastructure.WorkoutSerializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by sharas on 6/28/15.
 */
public class WorkoutSerializerTests extends TestCase
{
    List<Exercise> exercises1 = Arrays.asList(new Exercise("pull-up", new RepWeight(10, 0)));
    List<Exercise> exercises2 = Arrays.asList(new Exercise("Dumbbell shoulder push", new RepWeight(7, 30)),
        new Exercise("Dumbbell shoulder push", new RepWeight(4, 25)));

    DateFormat df = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss", Locale.ENGLISH);

    public void testWorkoutDeserialized() throws Exception
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        TimeFrame tf2 = new TimeFrame(df.parse("2015/01/22 14:25:11"), df.parse("2015/01/22 14:25:20"));
        TimeFrame tf3 = new TimeFrame(df.parse("2015/01/22 14:26:00"), df.parse("2015/01/22 14:26:10"));
        Workout w1 = new Workout(Arrays.asList(new Set(tf1, exercises1), new Set(tf2, exercises1), new Set(tf3, exercises2)));
        String serialized = WorkoutSerializer.serialize(w1);
//        File out = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "out.txt");
//        FileStore.writeFile(out.getAbsolutePath(), serialized);
        Workout w2 = WorkoutSerializer.deserialize(WorkoutSerializer.serialize(w1));
        assertEquals(w1, w2);
    }
}
