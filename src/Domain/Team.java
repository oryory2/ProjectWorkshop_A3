package Domain;

public class Team {

    private String team_name;
    private String team_id;
    private League league;
    private String field_name;

    public Team(String team_name, String team_id, League league, String field_name) {
        this.team_name = team_name;
        this.team_id = team_id;
        this.league = league;
        this.field_name = field_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }
}
