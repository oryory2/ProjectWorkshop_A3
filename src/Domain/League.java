package Domain;

import DataAccess.DB_handler;

import java.util.ArrayList;

// Class that represents a League
public class League {
    private String league_id;
    private String league_name;
    private String season;
    private String number_of_teams;
    private ArrayList<Referee> list_of_referees;
    private ArrayList<Game> list_of_games;

    // Constructor
    public League(String league_id, String league_name, String season, String number_of_teams) {
        this.league_id = league_id;
        this.league_name = league_name;
        this.season = season;
        this.number_of_teams = number_of_teams;
        this.list_of_referees = new ArrayList<Referee>();
        this.list_of_games = new ArrayList<Game>();
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getNumber_of_teams() {
        return number_of_teams;
    }

    public void setNumber_of_teams(String number_of_teams) {
        this.number_of_teams = number_of_teams;
    }

    public void add_referee(Referee referee_to_add)
    {
        this.list_of_referees.add(referee_to_add);
    }

    public void add_game(Game game_to_add)
    {
        this.list_of_games.add(game_to_add);
    }
}

