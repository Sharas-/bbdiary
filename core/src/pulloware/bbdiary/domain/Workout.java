package pulloware.bbdiary.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Workout {
    public ArrayList<Set> getSets() {
        return sets;
    }

    private ArrayList<Set> sets;
    private String tag;

    public Workout(Collection<Set> sets, String tag) {
        this.sets = new ArrayList<Set>(sets);
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public Date GetStartedAt() {
        return sets.get(0).GetDuration().getFrom();
    }

    public Date GetEndedAt() {
        return sets.get(sets.size() - 1).GetDuration().getTo();
    }
}
