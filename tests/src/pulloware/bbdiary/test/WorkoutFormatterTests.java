package pulloware.bbdiary.test;


import junit.framework.TestCase;
import pulloware.bbdiary.infrastructure.WorkoutFormatter;
import pulloware.bbdiary.domain.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//In order to view my workout metrics later on
//As a sportsman
//I want to export my workout in a human readable form.

public class WorkoutFormatterTests extends TestCase
{
    public void testExportFormat()
    {
        String actual = exportWorkout(Locale.US);
        String expected = String.format("%s%n%s%n%s%n%s",
            "Thursday, January 1, 1970 5:00:00 PM",
            "5:00:00 PM",
            "pull-up 10x80.0 ",
            "5:00:11 PM");
        assertEquals(actual, expected);
        actual = exportWorkout(Locale.FRENCH);
        expected = String.format("%s%n%s%n%s%n%s",
            "jeudi 1 janvier 1970 17:00:00",
            "17:00:00",
            "pull-up 10x80.0 ",
            "17:00:11");
        assertEquals(actual, expected);
    }

    private String exportWorkout(Locale l)
    {
        long startHour = secs(60 * 60 * 14);
        Duration duration = new Duration(new Date(startHour), new Date(startHour + secs(11)));
        RepWeight effort = new RepWeight(10, 80);
        List<Exercise> exercises = Arrays.asList(new Exercise("pull-up", effort));
        List<Set> sets = Arrays.asList(new Set(duration, exercises));
        Workout w = new Workout(sets, "only pull-ups");
        return new WorkoutFormatter(l).Format(w).toString();
    }

    private long secs(int secs)
    {
        return secs * 1000;
    }

}