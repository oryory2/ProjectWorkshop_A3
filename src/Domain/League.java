package Domain;

public class League {
    private int league_id;
    private String league_name;
    private String season;
    private int number_of_teams;

    public League(int league_id, String league_name, String season, int number_of_teams) {
        this.league_id = league_id;
        this.league_name = league_name;
        this.season = season;
        this.number_of_teams = number_of_teams;
    }

    public int getLeague_id() {
        return league_id;
    }

    public void setLeague_id(int league_id) {
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

    public int getNumber_of_teams() {
        return number_of_teams;
    }

    public void setNumber_of_teams(int number_of_teams) {
        this.number_of_teams = number_of_teams;
    }
}

