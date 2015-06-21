package pulloware.bbdiary.domain;


import java.util.Collection;

public class Set
{
    private Collection<Exercise> exercises;
    private Duration duration;

    public Set(Duration duration, Collection<Exercise> exercises)
    {
        this.exercises = exercises;
        this.duration = duration;
    }

    public Collection<Exercise> getExercises()
    {
        return exercises;
    }

    public Duration GetDuration()
    {
        return this.duration;
    }


}
