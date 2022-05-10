package Domain;

import DataAccess.DB_handler;

import java.sql.SQLException;

public class SystemController
{
    public SystemController()
    {
        DB_handler.connect_DB();
    }

    public boolean loginUser(String userID, String password) throws SQLException {
        return DB_handler.checkLogin(userID, password);
    }

    public boolean addReferee(String name, String qualification, String leagueId)
    {
        return true;
    }

    public boolean assignGame(String gameId, String teamOne, String teamTwo, String leagueIdOfTheGame, String dateAndTime, String referees, String field)
    {
        return true;
    }
}
