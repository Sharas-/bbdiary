package pulloware.bbdiary.domain;

import java.util.Date;

public class Duration
{
    public Date getFrom() {
        return from;
    }

    private Date from;

    public Date getTo() {
        return to;
    }

    private Date to;

    public Duration(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

}
