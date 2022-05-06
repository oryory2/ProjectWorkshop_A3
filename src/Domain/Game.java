package Domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game {
    private String gameId;
    private String team1;
    private String team2;
    private String leagueId;
    private Pair score;
    private LocalDateTime date;
    private ArrayList<Referee> referees;
    private String field;



    public Game(String gameId, String team1, String team2, String leagueId, LocalDateTime date, ArrayList<Referee> referees, String field){

        this.gameId = gameId;
        this.team1 = team1;
        this.team2 = team2;
        this.leagueId = leagueId;
        this.date = date;
        this.referees = referees;
        this.field = field;
    }

    public String getGameId()
    {
        return gameId;
    }

    public void setGameId(String gameId)
    {
        this.gameId = gameId;
    }

    public String getTeam1()
    {
        return team1;
    }

    public void setTeam1(String team1)
    {
        this.team1 = team1;
    }

    public String getTeam2()
    {
        return team2;
    }

    public void setTeam2(String team2)
    {
        this.team2 = team2;
    }

    public String getLeagueId()
    {
        return leagueId;
    }

    public void setLeagueId(String leagueId)
    {
        this.leagueId = leagueId;
    }

    public Pair getScore()
    {
        return score;
    }

    public void setScore(int scoreTeamOne, int scoreTeamTwo)
    {
        this.score = new Pair(scoreTeamOne, scoreTeamTwo);
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public void setDate(LocalDateTime date)
    {
        this.date = date;
    }

    public ArrayList<Referee> getReferees()
    {
        return referees;
    }

    public void setReferees(ArrayList<Referee> referees)
    {
        this.referees = referees;
    }

    public String getField()
    {
        return field;
    }

    public void setField(String field)
    {
        this.field = field;
    }

}
