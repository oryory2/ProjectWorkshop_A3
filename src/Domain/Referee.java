package Domain;

public class Referee
{
    private final String refereeID;
    private String name;
    private String leagueId;
    // private ArrayList <Game> games;


    public Referee(String refereeID,String name, String leagueId) {
        this.name = name;
        this.refereeID = refereeID;
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }
}