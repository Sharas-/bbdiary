package pulloware.bbdiary.test.platformIndependent;


import junit.framework.TestCase;
import pulloware.bbdiary.domain.*;
import pulloware.bbdiary.infrastructure.WorkoutFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class WorkoutFormatterTests extends TestCase
{
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss", Locale.ENGLISH);

    public void testExportFormat() throws ParseException
    {
        String actual = exportWorkout(Locale.US);
        String expected =
            "Thursday, January 22, 2015 2:25:00 PM" + "\n" +
                "2:25:00 PM" + "\n" +
                "\tpull-up 10x80.0" + "\n" +
                "\tblock pull down 6x75.0" + "\n" +
                "2:25:27 PM" + "\n" +
                "2:26:31 PM" + "\n" +
                "\tpull-up 10x80.0" + "\n" +
                "\tblock pull down 6x75.0" + "\n" +
                "2:27:42 PM" + "\n";
        assertEquals(expected, actual);
        actual = exportWorkout(Locale.FRENCH);
        expected =
            "jeudi 22 janvier 2015 14:25:00" + "\n" +
                "14:25:00" + "\n" +
                "\tpull-up 10x80.0" + "\n" +
                "\tblock pull down 6x75.0" + "\n" +
                "14:25:27" + "\n" +
                "14:26:31" + "\n" +
                "\tpull-up 10x80.0" + "\n" +
                "\tblock pull down 6x75.0" + "\n" +
                "14:27:42" + "\n";
        assertEquals(expected, actual);
    }

    private String exportWorkout(Locale l) throws ParseException
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:27"));
        TimeFrame tf2 = new TimeFrame(df.parse("2015/01/22 14:26:31"), df.parse("2015/01/22 14:27:42"));
        List<Exercise> exercises = Arrays.asList(
            new Exercise("pull-up", new RepWeight(10, 80)),
            new Exercise("block pull down", new RepWeight(6, 75)));
        List<Set> sets = Arrays.asList(new Set(tf1, exercises), new Set(tf2, exercises));
        Workout w = new Workout(sets);
        return WorkoutFormatter.toHumanReadable(w, l).toString();
    }


    public void testWorkoutDeserialized() throws Exception
    {
        TimeFrame tf1 = new TimeFrame(df.parse("2015/01/22 14:25:00"), df.parse("2015/01/22 14:25:10"));
        TimeFrame tf2 = new TimeFrame(df.parse("2015/01/22 14:25:11"), df.parse("2015/01/22 14:25:20"));
        TimeFrame tf3 = new TimeFrame(df.parse("2015/01/22 14:26:00"), df.parse("2015/01/22 14:26:10"));
        List<Exercise> exercises1 = Arrays.asList(new Exercise("pull-up", new RepWeight(10, 0)));
        List<Exercise> exercises2 = Arrays.asList(new Exercise("Dumbbell shoulder push", new RepWeight(7, 30)),
            new Exercise("Dumbbell shoulder push", new RepWeight(4, 25)));
        Workout w1 = new Workout(Arrays.asList(new Set(tf1, exercises1), new Set(tf2, exercises1), new Set(tf3, exercises2)));
        Workout w2 = WorkoutFormatter.fromJSON(WorkoutFormatter.toJSON(w1));
        assertEquals(w1, w2);
    }

}