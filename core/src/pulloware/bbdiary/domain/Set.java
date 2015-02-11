package pulloware.bbdiary.domain;


import java.util.Collection;

public class Set {
    private Collection<Exercise> exercises;
    private Interval duration;

    public Set(Interval duration, Collection<Exercise> exercises)
    {
        this.exercises = exercises;
        this.duration = duration;
    }

    public Collection<Exercise> getExercises() {
        return exercises;
    }
    public Interval GetDuration()
    {
        return this.duration;
    }



}
