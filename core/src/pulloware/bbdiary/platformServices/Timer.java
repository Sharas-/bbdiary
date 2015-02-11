package pulloware.bbdiary.platformServices;

import pulloware.bbdiary.domain.Interval;

import java.util.Date;

public class Timer
{
    private Date started;

    public void Start()
    {
        this.started = new Date();
    }

    public Interval Stop()
    {
        Date stoped = new Date();
        return new Interval(started, stoped);
    }
}
