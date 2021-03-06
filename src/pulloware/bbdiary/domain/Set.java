package pulloware.bbdiary.domain;


import java.util.Collection;

public class Set
{
    private Collection<Exercise> exercises;
    private TimeFrame duration;

    public Set(TimeFrame duration, Collection<Exercise> exercises)
    {
        this.exercises = exercises;
        this.duration = duration;
    }

    public Collection<Exercise> getExercises()
    {
        return exercises;
    }

    public TimeFrame timeFrame()
    {
        return this.duration;
    }
}
