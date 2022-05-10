import DataAccess.DB_handler;
import UserInterface.UserInterface;

import java.sql.SQLException;


public class Main
{
    public UserInterface ui;


    public static void main(String[] args) throws SQLException {
//        UserInterface ui = new UserInterface();
//        ui.runMenu();
        DB_handler.connect_DB();
//        DB_handler.add_row("login", new String[]{"username", "password"}, new String[]{"yoni.5", "9999"});
        DB_handler.remove_row("login", "username", "yoni.5");
        DB_handler.checkLogin("yoni.1","99998");
//        DB_handler.disconnect_DB();
    }
}
