package Domain;

public class Pair
{
    private int scoreTeamOne;
    private int scoreTeamTwo;

    public Pair(int scoreOne, int scoreTwo)
    {
        this.scoreTeamOne = scoreOne;
        this.scoreTeamTwo = scoreTwo;
    }

    @Override
    public String toString()
    {
        return "(" + this.scoreTeamOne + "," + scoreTeamTwo + ")";
    }
}
