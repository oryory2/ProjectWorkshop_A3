
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

    public boolean setGameId(String gameId)
    {
        if(gameId == null)
            return false;

        // should we check if there is already a game with this gameId
        // should we check those functions at all? we are not going to use them in this Assigment............

        this.gameId = gameId;
        return true;
    }

    public String getTeam1()
    {
        return team1;
    }

    public boolean setTeam1(String team1)
    {
        if(team1 == null)
            return false;

        if(team2 != null)
        {
            if(team2.equals(team1))
                return false;
        }
        this.team1 = team1;
        return true;
    }

    public String getTeam2()
    {
        return team2;
    }

    public boolean setTeam2(String team2)
    {
        if(team2 == null)
            return false;

        if(team1 != null)
        {
            if(team2.equals(team1))
                return false;
        }
        this.team2 = team2;
        return true;
    }

    public String getLeagueId()
    {
        return leagueId;
    }

    public boolean setLeagueId(String leagueId)
    {
        if(leagueId == null)
            return false;
        // should we check if there is an existing League with this  this leagueId

        this.leagueId = leagueId;
        return true;
    }

    public Pair getScore()
    {
        return score;
    }

    public boolean setScore(int scoreTeamOne, int scoreTeamTwo)
    {
        if((scoreTeamOne < 0) || (scoreTeamTwo < 0))
        {
            return false;
        }

        this.score = new Pair(scoreTeamOne, scoreTeamTwo);
        return true;
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

    public boolean setReferees(ArrayList<Referee> referees)
    {
        if(referees == null)
            return false;

        this.referees = referees;
        return true;
    }

    public String getField()
    {
        return field;
    }

    public boolean setField(String field)
    {
        if(field == null)
            return false;

        this.field = field;
        return true;
    }

}
