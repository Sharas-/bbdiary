package pulloware.bbdiary.domain;

import java.util.Collection;

public class Exercise
{
    private String name;
    private Collection<RepWeight> efforts;

    public String getName() {
        return name;
    }

    public Collection<RepWeight> GetEfforts()
    {
        return  this.efforts;
    }

    public Exercise(String name, Collection<RepWeight> efforts)
    {
        this.name = name;
        this.efforts = efforts;
    }
}
