package pulloware.bbdiary.application;

import pulloware.bbdiary.domain.Routine;
import pulloware.bbdiary.domain.Set;
import pulloware.bbdiary.domain.Workout;

import java.util.ArrayList;

public class WorkoutBuilder
{
    //public String GetTag(){ return this.tag;}
    //public String SetTag(String tag){ this.tag = tag; }
    ArrayList<Set> sets = new ArrayList<Set>(20);
    Workout workout;
    Routine routine;

    public void AddSet(Set s)
    {
        sets.add(s);
        this.workout = new Workout(sets);
    }

}
