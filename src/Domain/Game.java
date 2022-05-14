package Domain;

// Class that represents a game
public class Game {
    private String gameId;
    private Team homeTeam;
    private Team visitorTeam;
    private League league;
    private Pair score;
    private String dateAndTime;
    private Referee referee;
    private String field;


    // Constructor
    public Game(String gameId, Team homeTeam, Team visitorTeam, League league, String dateAndTime, Referee referee, String field){
        this.gameId = gameId;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.league = league;
        this.dateAndTime = dateAndTime;
        this.referee = referee;
        this.field = field;
    }


    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(Team visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Pair getScore() {
        return score;
    }

    public void setScore(Pair score) {
        this.score = score;
    }


    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
