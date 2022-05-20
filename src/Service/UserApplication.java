package Service;

import Domain.*;
import java.sql.SQLException;


// Class that represents the user application in the Service Layer. It handles inputs from the user and pass them to the Domain layer.
public class UserApplication {

    SystemController SC = SystemController.getInstance();

    // Function that handles a loginUser operation
    public boolean loginUser(String iUserName, String iPassword) throws SQLException {
        return SC.loginUser(iUserName, iPassword);
    }

    // Function that handles a addReferee operation
    public boolean addReferee(String referee_ID, String leagueId)
    {
        return SC.addReferee(referee_ID, leagueId);
    }

    // Function that handles a assignGame operation
    public boolean assignGame(String homeTeamID, String visitorTeamID, String leagueIdOfTheGame, String dateAndTime, String refereeID)
    {
        return SC.assignGame(homeTeamID, visitorTeamID, leagueIdOfTheGame, dateAndTime, refereeID);
    }

}