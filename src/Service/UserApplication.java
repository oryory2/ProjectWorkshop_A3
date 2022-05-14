package Service;


import Domain.*;
import java.sql.SQLException;


public class UserApplication {

    SystemController SC = new SystemController();

    public boolean loginUser(String iUserName, String iPassword) throws SQLException {
        return SC.loginUser(iUserName, iPassword);
    }

    public boolean addReferee(String name, String leagueId)
    {
        return SC.addReferee(name, leagueId);

    }

    public boolean assignGame( String homeTeamID, String visitorTeamID, String leagueIdOfTheGame, String dateAndTime, String refereeID)
    {
        return SC.assignGame( homeTeamID,  visitorTeamID,  leagueIdOfTheGame,  dateAndTime,  refereeID);
    }

}