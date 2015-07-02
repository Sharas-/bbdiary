package pulloware.bbdiary.domain;

public class Exercise
{
    private String name;
    private RepWeight effort;

    public String getName()
    {
        return name;
    }

    public RepWeight getEffort()
    {
        return this.effort;
    }

    public Exercise(String name, RepWeight effort)
    {
        this.name = name;
        this.effort = effort;
    }
}
