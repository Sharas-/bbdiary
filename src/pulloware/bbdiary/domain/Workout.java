package pulloware.bbdiary.domain;

import java.util.*;

/**
 * ordered non overlapping sequence of sets
 */
public class Workout implements Iterable<Set>
{
    private TreeSet<Set> orderedNonOverlappingSetSequence;

    public Collection<Set> getSets()
    {
        ArrayList<Set> sets = new ArrayList<Set>(orderedNonOverlappingSetSequence.size());
        for (Set s : orderedNonOverlappingSetSequence)
        {
            sets.add(s);
        }
        return sets;
    }

    @Override
    public Iterator iterator()
    {
        return orderedNonOverlappingSetSequence.iterator();
    }

    private class SetComparator implements Comparator<Set>
    {
        @Override
        public int compare(Set set1, Set set2)
        {
            if (set1.timeFrame().overlaps(set2.timeFrame()))
                return 0;
            if (set1.timeFrame().startedAfter(set2.timeFrame()))
                return 1;
            return -1;
        }
    }

    public Workout(Collection<Set> sets) throws SetsOverlapException
    {
        if (sets == null || sets.size() == 0)
        {
            throw new IllegalArgumentException("Cannot have workout without sets");
        }
        this.orderedNonOverlappingSetSequence = new TreeSet<Set>(new SetComparator());
        for (Set s : sets)
        {
            if (!orderedNonOverlappingSetSequence.add(s))
            {
                throw new SetsOverlapException("Set execution times cannot overlap");
            }
        }
    }

    public TimeFrame timeFrame()
    {
        return new TimeFrame(orderedNonOverlappingSetSequence.first().timeFrame().getFrom(),
            orderedNonOverlappingSetSequence.last().timeFrame().getTo());
    }
}
