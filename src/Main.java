import DataAccess.DB_handler;
import Service.UserApplication;

import org.sqlite.core.DB;

import java.sql.SQLException;


public class Main
{

    public static void main(String[] args) throws SQLException {
        UserApplication ua = new UserApplication();
        ua.assignGame("2","1" ,"1","2022-09-02 11:13:22.002","1");
//        UserInterface ui = new UserInterface();
//        ui.runMenu();
//        DB_handler.connect_DB();
//        DB_handler.add_league("2020","league-1","10");
////        DB_handler.add_row("login", new String[]{"username", "password"}, new String[]{"yoni.5", "9999"});
//        DB_handler.remove_row("login", "username", "yoni.5");
//        DB_handler.checkLogin("yoni.1","99998");
//        ua.addReferee("Amit2","6");
//        DB_handler.disconnect_DB();
    }
}
