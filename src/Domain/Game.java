import java.time.LocalDateTime;

public class UserTests
{

    public class Game {
        private String gameId;
        private String team1;
        private String team2;
        private String leagueId;
        private Pair<int, int> score;
        private LocalDateTime date;
        private ArratList<Referee> referees;
        private String field;



        public Game(String gameId, String team1, String team2, String leagueId, LocalDateTime date, ArratList<Referee> referees, String field){

            gameId = gameId;
            team1 = team1;
            team2 = team2;
            leagueId = leagueId;
            date = date;
            referees = referees;
            field = field;

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

        public Pair<int, int> getScore() 
        {
            return score;
        }

        public void setScore(Pair<int, int> score) 
        {
            this.score = score;
        }

        public LocalDateTime getDate() 
        {
            return date;
        }

        public void setDate(LocalDateTime date) 
        {
            this.date = date;
        }

        public ArratList<Referee> getReferees()
        {
            return referees;
        }

        public void setReferees(ArratList<Referee> referees)
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

}