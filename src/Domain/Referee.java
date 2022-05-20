package Domain;

import DataAccess.DB_handler;

import java.util.ArrayList;

// Class that represents a Referee
public class Referee
{
    private final String refereeID;
    private String name;
    private String leagueId;
    private ArrayList<League> list_of_leagues;
    // private ArrayList <Game> games; TODO maybe need to remove?


    public Referee(String refereeID,String name, String leagueId) {
        this.name = name;
        this.refereeID = refereeID;
        this.leagueId = leagueId;
        this.list_of_leagues = new ArrayList<League>();
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

    public void add_league(League league_to_add)
    {
        this.list_of_leagues.add(league_to_add);
    }

    public String getRefereeID()
    {
        return this.refereeID;
    }
}
