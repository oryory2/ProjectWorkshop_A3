package Service;


import Domain.*;
import java.sql.SQLException;


public class UserApplication {

    SystemController SC=new SystemController();

    public void loginUser(String iUserName, String iPassword) throws SQLException {
        SC.loginUser(iUserName, iPassword);
    }

}