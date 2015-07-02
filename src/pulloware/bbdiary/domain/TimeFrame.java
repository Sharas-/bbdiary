package pulloware.bbdiary.domain;

import java.util.Date;

public class TimeFrame
{
    public Date getFrom()
    {
        return from;
    }

    private Date from;

    public Date getTo()
    {
        return to;
    }

    private Date to;

    public TimeFrame(Date from, Date to)
    {
        if (!from.before(to))
        {
            throw new IllegalArgumentException("Date from must be before date to");
        }
        this.from = from;
        this.to = to;
    }

    public boolean overlaps(TimeFrame other)
    {
        return !(other.startedAfter(this) || this.startedAfter(other));
    }

    public boolean startedAfter(TimeFrame other)
    {
        return this.from.after(other.to);
    }
}
