package Domain;

public class Referee
{
    private String name;
    private String qualification;
    private String leagueId;
    // private ArrayList <Game> games;


    public Referee(String name, String qualification, String leagueId) {
        this.name = name;
        this.qualification = qualification;
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }
}