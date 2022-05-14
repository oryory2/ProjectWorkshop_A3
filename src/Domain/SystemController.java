package Domain;

import DataAccess.DB_handler;
import org.sqlite.core.DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemController
{

    public User user;


    public SystemController()
    {
        DB_handler.connect_DB();

    }

    public boolean loginUser(String userID, String password) throws SQLException {

        if (userID == null || password == null){
            return false;
        }

        if (DB_handler.checkLogin(userID, password)){
             user = new User(userID,password);
             return true;
        }

        return false;

    }

    public boolean addReferee(String name, String leagueId)
    {

        if (name == null || leagueId == null){
            return false;
        }

        String[] column = {"league_id"};
        String condition =   "league_id == '" + leagueId +"'";

        if (DB_handler.existInDB("leagues",column,condition)){

            DB_handler.addReferee(name,leagueId);
            Referee referee = new Referee(DB_handler.get_max_id_row("referee","referee_id"),name,leagueId);
            return true;
        }
        return false;
    }

    public boolean assignGame(String homeTeam_id, String visitorTeam_id, String leagueIdOfTheGame, String dateAndTime, String referee_id)
    {
        if (homeTeam_id == null || visitorTeam_id == null || leagueIdOfTheGame == null || dateAndTime == null || referee_id == null){
            return false;
        }

        if (homeTeam_id.equals(visitorTeam_id)){
            return false;
        }


        Pattern regex = Pattern.compile("^([0-9]{4})-([0-1][0-9])-([0-3][0-9])\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):" +
                "([0-5][0-9]).([0-9][0-9][0-9])$");
        Matcher matcher = regex.matcher(dateAndTime);
        if (matcher.find()){

            String[] columnTeam = {"team_id"};
            String conditionHome =   "team_id == '" + homeTeam_id +"'";
            String conditionVisitor =   "team_id == '" + visitorTeam_id +"'";

            String[] columnLeague = {"league_id"};
            String conditionLeague =   "league_id == '" + leagueIdOfTheGame +"'";

            String[] columnReferee = {"referee_id"};
            String conditionReferee =   "referee_id == '" + referee_id +"'";

            if (DB_handler.existInDB("teams",columnTeam,conditionHome)
                    & DB_handler.existInDB("teams",columnTeam,conditionVisitor)
                    &  DB_handler.existInDB("leagues",columnLeague,conditionLeague)
                    & DB_handler.existInDB("referee",columnReferee,conditionReferee)){


                ArrayList<ArrayList> refereeDetails = DB_handler.get_list("referee",new String[] {"referee_name","league_id"},conditionReferee);
                ArrayList<ArrayList> leagueDetails = DB_handler.get_list("leagues",new String[] {"season","league_name","number_of_teams"},conditionLeague);
                ArrayList<ArrayList> homeTeamDetails = DB_handler.get_list("teams",new String[] {"team_name","league_id","field_name"},conditionHome);
                ArrayList<ArrayList> visitorTeamDetails = DB_handler.get_list("teams",new String[] {"team_name","league_id","field_name"},conditionVisitor);

                if (DB_handler.existInDB())

                if (((String) refereeDetails.get(0).get(1)).equals(leagueIdOfTheGame) & ((String) homeTeamDetails.get(0).get(1)).equals(leagueIdOfTheGame) &
                        ((String) visitorTeamDetails.get(0).get(1)).equals(leagueIdOfTheGame)) {

                    DB_handler.addGame(homeTeam_id, visitorTeam_id, leagueIdOfTheGame, dateAndTime, referee_id);

                    // Constructing league.
                    String leagueSeason = (String) leagueDetails.get(0).get(0);
                    String leagueName = (String) leagueDetails.get(0).get(1);
                    String leagueNumOfTeam = (String) leagueDetails.get(0).get(2);
                    League league = new League(leagueIdOfTheGame,leagueName,leagueSeason,leagueNumOfTeam);

                    // Constructing Home Team.
                    String homeName = (String) homeTeamDetails.get(0).get(0);
                    String homeField = (String) homeTeamDetails.get(0).get(2);
                    Team homeTeam = new Team(homeName,homeTeam_id,league,homeField);

                    // Constructing Visitor Team.
                    String visitorName = (String) homeTeamDetails.get(0).get(0);
                    String visitorField = (String) homeTeamDetails.get(0).get(2);
                    Team visitorTeam = new Team(visitorName,visitorTeam_id,league,visitorField);

                    // Constructing Referee
                    String refereeName = (String) refereeDetails.get(0).get(0);
                    Referee referee = new Referee(referee_id,refereeName,leagueIdOfTheGame);

                    // Constructing Game
                    Game game = new Game(DB_handler.get_max_id_row("games", "game_id"),
                            homeTeam, visitorTeam, league, dateAndTime,referee,homeField);
                    return true;
                }
            }
        }
        return false;
    }
}
