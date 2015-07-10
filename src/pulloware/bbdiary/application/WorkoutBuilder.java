package pulloware.bbdiary.application;

import pulloware.bbdiary.domain.Routine;
import pulloware.bbdiary.domain.Set;
import pulloware.bbdiary.domain.Workout;
import pulloware.bbdiary.infrastructure.FileName;
import pulloware.bbdiary.infrastructure.FileStore;
import pulloware.bbdiary.infrastructure.WorkoutFormatter;

import java.io.IOException;
import java.util.ArrayList;

public class WorkoutBuilder
{
    ArrayList<Set> sets = new ArrayList<Set>(20);
    Workout workout;
    Routine routine;

    public void addSet(Set s)
    {
        sets.add(s);
        this.workout = new Workout(sets);
    }

    /**
     * Writes athlete friendly representation of workout to devices' external store.
     * @param tag to attach to exported workouts' file name
     * @return path to file where workout was exported
     */
    public String exportWorkout(String tag) throws IOException
    {
        FileName outFile = FileName.sanitize(tag + " " + workout.timeFrame().getFrom().toString() + ".txt");
        return FileStore.writeExternal(outFile, WorkoutFormatter.toHumanReadable(workout));
    }
}
