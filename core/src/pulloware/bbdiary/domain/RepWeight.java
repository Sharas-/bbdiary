package pulloware.bbdiary.domain;

public class RepWeight
{
    public int getReps() {
        return reps;
    }

    public float getWeight() {
        return weight;
    }

    private int reps;
    private float weight;

    public RepWeight(int reps, float weight)
    {
        this.reps = reps;
        this.weight = weight;
    }

}
