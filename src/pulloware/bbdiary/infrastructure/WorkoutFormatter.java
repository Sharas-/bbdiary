package pulloware.bbdiary.infrastructure;

import pulloware.bbdiary.domain.Exercise;
import pulloware.bbdiary.domain.RepWeight;
import pulloware.bbdiary.domain.Set;
import pulloware.bbdiary.domain.Workout;

import java.text.DateFormat;
import java.util.Iterator;
import java.util.Locale;

public class WorkoutFormatter
{
    private DateFormat fDateTime;
    private DateFormat fTime;

    public WorkoutFormatter()
    {
        this(Locale.getDefault());
    }

    public WorkoutFormatter(Locale l)
    {
        fDateTime = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, l);
        fTime = DateFormat.getTimeInstance(DateFormat.MEDIUM, l);
    }

    public StringBuilder Format(Workout w)
    {
        String newLine = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        final Set firstSet = w.getSets().iterator().next();
        sb.append(fDateTime.format(firstSet.GetDuration().getFrom()));
        sb.append(newLine);
        for (Iterator<Set> sets = w.getSets().iterator(); sets.hasNext(); )
        {
            Set s = sets.next();
            sb.append(fTime.format(s.GetDuration().getFrom()));
            sb.append(newLine);
            for (Iterator<Exercise> exercises = s.getExercises().iterator(); exercises.hasNext(); )
            {
                Exercise e = exercises.next();
                sb.append(e.getName());
                sb.append(' ');
                RepWeight effort = e.GetEffort();
                sb.append(effort.getReps());
                sb.append('x');
                sb.append(effort.getWeight());
                sb.append(' ');
            }
            sb.append(newLine);
            sb.append(fTime.format(s.GetDuration().getTo()));
        }
        return sb;
    }
}
