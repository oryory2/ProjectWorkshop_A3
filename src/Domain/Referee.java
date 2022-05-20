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

    public static boolean isRefereeExist(String referee_ID)
    {
        String[] referee_column = {"referee_id"};
        String referee_id_condition = "referee_id == '" + referee_ID + "'";
        return DB_handler.existInDB("referee", referee_column, referee_id_condition);
    }

    public static boolean isRefereeExistInLeague(String referee_ID, String leagueId)
    {
        String[] referee_in_league_column = {"referee_id", "league_id"};
        String referee_in_league_condition = "referee_id == '" + referee_ID + "' AND league_id == '" + leagueId + "'";
        return DB_handler.existInDB("referee_in_league", referee_in_league_column, referee_in_league_condition);
    }

    public static String getRefereeName(String referee_ID)
    {
        String[] referee_column = {"referee_id"};
        String referee_id_condition = "referee_id == '" + referee_ID + "'";
        return (String) DB_handler.get_list("referee", new String[] {"referee_name"}, referee_id_condition).get(0).get(0);
    }
}
