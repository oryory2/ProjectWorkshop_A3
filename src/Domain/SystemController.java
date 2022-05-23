package Domain;

import DataAccess.DB_handler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Class that represents the system controller in the Domain layer. It handles all the business logic. It connects between the Service layer to the DataAccess layer
public class SystemController
{
    public User connected_user;
    private ArrayList<Referee> list_of_referees;
    private ArrayList<League> list_of_leagues;
    private ArrayList<Team> list_of_teams;
    private ArrayList<Game> list_of_games;
    private static SystemController systemController = null;


    // Constructor
    private SystemController()
    {
        DB_handler.connect_DB();
    }

    public static SystemController getInstance()
    {
        if(systemController == null)
        {
            systemController = new SystemController();
        }
        return systemController;
    }

    // Function that handles the loginUser call from the service layer
    public boolean loginUser(String userID, String password) throws SQLException
    {
        if (userID == null || password == null)
        {
            return false;
        }

        // Checks if the user exists in the DB
        if (DB_handler.checkLogin(userID, password)){
            connected_user = new User(userID,password);
             return true;


            //connected_user.setConnection(true);

            //TODO: add a "connection status" in the User table in the DB
            //TODO: should check the "connection status" of the user first
            //TODO: after creating the user object, connect him into the system.
        }
        return false;
    }

    // Function that handles the addReferee call from the service layer
    public boolean addReferee(String referee_ID, String leagueId)
    {
        if (referee_ID == null || leagueId == null)
        {
            return false;
        }

        //Checks that the leagueID exists, that the referee_id exists, and that the referee is not already bounded to the given league
        if((isLeagueExist(leagueId)) && isRefereeExist(referee_ID) && (!isRefereeExistInLeague(referee_ID, leagueId)))
        {
            // Adds the referee to the league in the DB
            DB_handler.addRefereeToLeague(referee_ID,leagueId);

            //Construct the Referee
            Referee referee = new Referee(referee_ID, getRefereeName(referee_ID),leagueId);

            //Construct the League
            ArrayList<String> leagueParams = getLeagueParams(leagueId);
            League league = new League(leagueId, leagueParams.get(1), leagueParams.get(0), leagueParams.get(2));

            //Adds the referee to the league and the league to the referee
            league.add_referee(referee);
            referee.add_league(league);

            return true;
        }
        return false;

//        // Prepares all the column names and condition strings we will use
//        String[] league_column = {"league_id"};
//        String league_condition =   "league_id == '" + leagueId +"'";
//
//        String[] referee_column = {"referee_id"};
//        String referee_id_condition = "referee_id == '" + referee_ID + "'";
//
//        String[] referee_in_league_column = {"referee_id", "league_id"};
//        String referee_in_league_condition = "referee_id == '" + referee_ID + "' AND league_id == '" + leagueId + "'";
//
//        //Checks that the leagueID exists, that the referee_id exists, and that the referee is not already bounded to the given league
//        if (DB_handler.existInDB("leagues",league_column, league_condition) & DB_handler.existInDB("referee", referee_column, referee_id_condition) &
//                !DB_handler.existInDB("referee_in_league", referee_in_league_column, referee_in_league_condition)){
//
//            // Adds the referee to the league in the DB
//            DB_handler.addRefereeToLeague(referee_ID,leagueId);
//
//            //Creates a referee instance
//            String referee_name = (String) DB_handler.get_list("referee", new String[] {"referee_name"}, referee_id_condition).get(0).get(0);
//            Referee referee = new Referee(referee_ID, referee_name,leagueId);
//
//            //Creates a league instance
//            String conditionLeague = "league_id == '" + leagueId +"'";
//            ArrayList<ArrayList> leagueDetails = DB_handler.get_list("leagues",new String[] {"season","league_name","number_of_teams"},conditionLeague);
//            String leagueSeason = (String) leagueDetails.get(0).get(0);
//            String leagueName = (String) leagueDetails.get(0).get(1);
//            String leagueNumOfTeam = (String) leagueDetails.get(0).get(2);
//            League league = new League(leagueId, leagueName, leagueSeason, leagueNumOfTeam);
//
//            //Adds the referee to the league and the league to the referee
//            league.add_referee(referee);
//            referee.add_league(league);
//
//            return true;
//
//        }
//        return false;
    }

    // Function that handles the assignGame call from the service layer
    public boolean assignGame(String homeTeam_id, String visitorTeam_id, String leagueIdOfTheGame, String dateAndTime, String referee_id)
    {
        if (homeTeam_id == null || visitorTeam_id == null || leagueIdOfTheGame == null || dateAndTime == null || referee_id == null)
        {
            return false;
        }

        // Checks that the home team is not the same as the visitor team
        if (homeTeam_id.equals(visitorTeam_id)){
            return false;
        }

        // Checks that the datetime pattern is YYYY-MM-DD HH:MM:SS.SSS
        Pattern regex = Pattern.compile("^([0-9]{4})-([0-1][0-9])-([0-3][0-9])\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9]).([0-9][0-9][0-9])$");
        Matcher matcher = regex.matcher(dateAndTime);

        // If pattern is correct
        // TODO: need to remove !
        if (!matcher.find())
        {
            // Checks that home team and visitor team actually exists in the DB
            // Checks that the league_id exists in the DB
            // Checks that the referee_id exists in the DB
            // Checks that the referee_id and the league_id exists in the referee_in_league table (that means referee is already associated with this league) in the DB
            // Checks that this game (same home team, same visitor team, same league) doesn't already exist in the DB

            boolean bla = isLeagueExist(leagueIdOfTheGame);
            boolean A = isTeamExist(homeTeam_id) && isTeamExist(visitorTeam_id) && isLeagueExist(leagueIdOfTheGame);
            boolean B = isRefereeExist(referee_id) && isRefereeExistInLeague(referee_id, leagueIdOfTheGame);
            boolean C = !isGameExistInLeague(homeTeam_id, visitorTeam_id, leagueIdOfTheGame);
            boolean D = isRefereeAvailable(referee_id, dateAndTime) && isTeamAvailable(homeTeam_id, dateAndTime) && isTeamAvailable(visitorTeam_id, dateAndTime);

            if(isTeamExist(homeTeam_id) && isTeamExist(visitorTeam_id) && isLeagueExist(leagueIdOfTheGame)
                    && isRefereeExist(referee_id) && isRefereeExistInLeague(referee_id, leagueIdOfTheGame)
                    && !isGameExistInLeague(homeTeam_id, visitorTeam_id, leagueIdOfTheGame)
                    && isRefereeAvailable(referee_id, dateAndTime) && isTeamAvailable(homeTeam_id, dateAndTime) && isTeamAvailable(visitorTeam_id, dateAndTime))
            {
                // Get the details of both teams
                ArrayList<String> homeTeamDetails =  getTeamParams(homeTeam_id);
                ArrayList<String> visitorTeamDetails =  getTeamParams(visitorTeam_id);

                // Checks that the home team and the visitor teams are both teams in the (same) given league
                if (homeTeamDetails.get(1).equals(leagueIdOfTheGame) && (homeTeamDetails.get(1).equals(leagueIdOfTheGame)))
                {
                    // Adds the game to the DB
                    DB_handler.addGame(homeTeam_id, visitorTeam_id, leagueIdOfTheGame, dateAndTime, referee_id);

                    // Constructs the league
                    ArrayList<String> leagueParams = getLeagueParams(leagueIdOfTheGame);
                    League league = new League(leagueIdOfTheGame, leagueParams.get(1), leagueParams.get(0), leagueParams.get(2));

                    // Constructs the Home Team.
                    Team homeTeam = new Team(homeTeamDetails.get(0),homeTeam_id,league,homeTeamDetails.get(2));

                    // Constructs the Visitor Team.
                    Team visitorTeam = new Team(visitorTeamDetails.get(0),homeTeam_id,league,visitorTeamDetails.get(2));

                    //Construct the Referee
                    Referee referee = new Referee(referee_id, getRefereeName(referee_id),leagueIdOfTheGame);

                    // Constructs the Game
                    Game game = new Game(DB_handler.get_max_id_row("games", "game_id"), homeTeam, visitorTeam, league, dateAndTime,referee,homeTeamDetails.get(2));

                    // Add the game to the list of games in the league
                    league.add_game(game);
                    return true;
                }
            }
        }
        return false;



//            }
//
//
//            // Prepares all the column names and condition strings we will use
//            String[] columnTeam = {"team_id"};
//            String conditionHome = "team_id == '" + homeTeam_id +"'";
//            String conditionVisitor = "team_id == '" + visitorTeam_id +"'";
//
//            String[] columnLeague = {"league_id"};
//            String conditionLeague = "league_id == '" + leagueIdOfTheGame +"'";
//
//            String[] columnReferee = {"referee_id"};
//            String conditionReferee = "referee_id == '" + referee_id +"'";
//            String conditionRefereeInLeague = "referee_id == '" + referee_id +"' AND league_id == '" + leagueIdOfTheGame + "'";
//
//            String[] columnGame = {"home_team_id", "visitor_team_id", "league_id"};
//            String conditionGame = "home_team_id == '" + homeTeam_id + "' AND visitor_team_id == '" + visitorTeam_id + "' AND league_id == '" + leagueIdOfTheGame + "'";
//
//            // Checks that home team and visitor team actually exists in the DB
//            // Checks that the league_id exists in the DB
//            // Checks that the referee_id exists in the DB
//            // Checks that the referee_id and the league_id exists in the referee_in_league table (that means referee is already associated with this league) in the DB
//            // Checks that this game (same home team, same visitor team, same league) doesn't already exist in the DB
//            if (DB_handler.existInDB("teams",columnTeam,conditionHome)
//                    & DB_handler.existInDB("teams",columnTeam,conditionVisitor)
//                    & DB_handler.existInDB("leagues",columnLeague,conditionLeague)
//                    & DB_handler.existInDB("referee",columnReferee,conditionReferee)
//                    & DB_handler.existInDB("referee_in_league", columnReferee, conditionRefereeInLeague)
//                    & !DB_handler.existInDB("games", columnGame, conditionGame)){
//
//                // Extracts details about the different entities
//                ArrayList<ArrayList> homeTeamDetails = DB_handler.get_list("teams",new String[] {"team_name","league_id","field_name"},conditionHome);
//                ArrayList<ArrayList> visitorTeamDetails = DB_handler.get_list("teams",new String[] {"team_name","league_id","field_name"},conditionVisitor);
//                ArrayList<ArrayList> refereeDetails = DB_handler.get_list("referee",new String[] {"referee_name"},conditionReferee);
//                ArrayList<ArrayList> leagueDetails = DB_handler.get_list("leagues",new String[] {"season","league_name","number_of_teams"},conditionLeague);
//
//
//                // Checks that the home team and the visitor teams are both teams in the (same) given league
//                if (((String) homeTeamDetails.get(0).get(1)).equals(leagueIdOfTheGame) & ((String) visitorTeamDetails.get(0).get(1)).equals(leagueIdOfTheGame))
//                {
//                    // Adds the game to the DB
//                    DB_handler.addGame(homeTeam_id, visitorTeam_id, leagueIdOfTheGame, dateAndTime, referee_id);
//
//                    // Constructs the league.
//                    String leagueSeason = (String) leagueDetails.get(0).get(0);
//                    String leagueName = (String) leagueDetails.get(0).get(1);
//                    String leagueNumOfTeam = (String) leagueDetails.get(0).get(2);
//                    League league = new League(leagueIdOfTheGame,leagueName,leagueSeason,leagueNumOfTeam);
//
//                    // Constructs the Home Team.
//                    String homeName = (String) homeTeamDetails.get(0).get(0);
//                    String homeField = (String) homeTeamDetails.get(0).get(2);
//                    Team homeTeam = new Team(homeName,homeTeam_id,league,homeField);
//
//                    // Constructs the Visitor Team.
//                    String visitorName = (String) homeTeamDetails.get(0).get(0);
//                    String visitorField = (String) homeTeamDetails.get(0).get(2);
//                    Team visitorTeam = new Team(visitorName,visitorTeam_id,league,visitorField);
//
//                    // Constructs the Referee
//                    String refereeName = (String) refereeDetails.get(0).get(0);
//                    Referee referee = new Referee(referee_id,refereeName,leagueIdOfTheGame);
//
//                    // Constructs the Game
//                    Game game = new Game(DB_handler.get_max_id_row("games", "game_id"), homeTeam, visitorTeam, league, dateAndTime,referee,homeField);
//
//                    // Add the game to the list of games in the league
//                    league.add_game(game);
//                    return true;
//
//
//
//                }
//            }
//        }
//        return false;
    }

    // Function that check if the referee exist in the DB
    public boolean isRefereeExist(String referee_ID)
    {
        String[] referee_column = {"referee_id"};
        String referee_id_condition = "referee_id == '" + referee_ID + "'";
        return DB_handler.existInDB("referee", referee_column, referee_id_condition);
    }

    // Function that check if the referee exist in the League in the DB
    public boolean isRefereeExistInLeague(String referee_ID, String leagueId)
    {
        String[] referee_in_league_column = {"referee_id", "league_id"};
        String referee_in_league_condition = "referee_id == '" + referee_ID + "' AND league_id == '" + leagueId + "'";
        return DB_handler.existInDB("referee_in_league", referee_in_league_column, referee_in_league_condition);
    }

    // Function that gets the referee name from the DB
    public String getRefereeName(String referee_ID)
    {
        String[] referee_column = {"referee_id"};
        String referee_id_condition = "referee_id == '" + referee_ID + "'";
        ArrayList<ArrayList> refereeDetails =  DB_handler.get_list("referee", new String[] {"referee_name"}, referee_id_condition);
        if (refereeDetails.size() == 0){
            return null;
        }
        return (String) refereeDetails.get(0).get(0);
    }

    // Function that check if the League exist in the DB
    public boolean isLeagueExist(String leagueId)
    {
        // Prepares all the column names and condition strings we will use
        String[] league_column = {"league_id"};
        String league_condition =   "league_id == '" + leagueId +"'";
        return DB_handler.existInDB("leagues",league_column, league_condition);
    }

    // Function that gets all the details about the League from the DB
    public ArrayList<String> getLeagueParams(String leagueId)
    {
        ArrayList<String> paramsArr = new ArrayList<>();
        String conditionLeague = "league_id == '" + leagueId +"'";
        ArrayList<ArrayList> leagueDetails = DB_handler.get_list("leagues",new String[] {"season","league_name","number_of_teams"},conditionLeague);
        if (leagueDetails.size() == 0){
            return null;
    }
        paramsArr.add((String) leagueDetails.get(0).get(0)); // League Season
        paramsArr.add((String) leagueDetails.get(0).get(1)); // League Name
        paramsArr.add((String) leagueDetails.get(0).get(2)); // League Number of teams
        return paramsArr;
    }

    // Function that gets all the details about the Team from the DB
    public ArrayList<String> getTeamParams(String team_id)
            {
        ArrayList<String> paramsArr = new ArrayList<>();
        String conditionHome = "team_id == '" + team_id + "'";
        ArrayList<ArrayList> TeamDetails = DB_handler.get_list("teams", new String[]{"team_name", "league_id", "field_name"}, conditionHome);

        paramsArr.add((String) TeamDetails.get(0).get(0)); // Team Name
        paramsArr.add((String) TeamDetails.get(0).get(1)); // League Id
        paramsArr.add((String) TeamDetails.get(0).get(2)); // Field Name

        return paramsArr;
    }

    // Function that check if the Team exist in the DB
    public boolean isTeamExist(String team_id)
    {
        String[] columnTeam = {"team_id"};
        String condition = "team_id == '" + team_id +"'";
        return DB_handler.existInDB("teams",columnTeam, condition);
    }

    // Function that check if the Game exist in the League in the DB
    public boolean isGameExistInLeague(String homeTeam_id, String visitorTeam_id, String leagueIdOfTheGame)
    {
        String[] columnGame = {"home_team_id", "visitor_team_id", "league_id"};
        String conditionGame = "home_team_id == '" + homeTeam_id + "' AND visitor_team_id == '" + visitorTeam_id + "' AND league_id == '" + leagueIdOfTheGame + "'";
        return DB_handler.existInDB("games", columnGame, conditionGame);
    }

    // Helper function that checks if a referee is already assigned to a game on the given date
    public Boolean isRefereeAvailable(String referee_id, String date)
    {
        String table = "games";
        String[] columns_name = {"referee_id", "game_datetime"};
        String condition = "refereee_id == " + referee_id + " AND game_datetime == '" + date + "'";
        return !DB_handler.existInDB(table, columns_name, condition);
    }

    // Helper function that checks if a team is already assigned to a game on the given date
    public Boolean isTeamAvailable(String team_id, String date)
    {
        String table = "games";
        String[] columns_name = {"home_team_id", "visitor_team_id", "game_datetime"};
        String condition = "(home_team_id == " + team_id + " OR visitor_team_id == " + team_id + ") AND game_datetime == '" + date + "'";
        return !DB_handler.existInDB(table, columns_name, condition);
    }

}
