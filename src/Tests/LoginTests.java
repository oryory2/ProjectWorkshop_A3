package Tests;

import DataAccess.DB_handler;
import Domain.Game;
import Domain.SystemController;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests {

    SystemController SC = new SystemController();

//    @org.junit.jupiter.api.BeforeAll
//    static void setUp()
//    {
//        DB_handler.connect_DB();
//        System.out.println("DB is ready for testing");
//    }

    @org.junit.jupiter.api.Test
    void testCorrectCredentials() throws SQLException {
        assertTrue(SC.loginUser("amit","1234"));
    }


    @org.junit.jupiter.api.Test
    void testWrongUsername() throws SQLException {
        assertFalse(SC.loginUser("amit12","1234"));
    }


    @org.junit.jupiter.api.Test
    void testWrongPassword() throws SQLException {
        assertFalse(SC.loginUser("amit","12345"));
    }

    @org.junit.jupiter.api.Test
    void testAddingAndCredentials() throws SQLException {
        assertFalse(SC.loginUser("yoni.4", "9999"));
        DB_handler.add_row("login", new String[]{"username", "password"}, new String[]{"yoni.4", "9999"});
        assertTrue(SC.loginUser("yoni.4", "9999"));
        DB_handler.remove_row("login", "username", "yoni.4");
        assertFalse(SC.loginUser("yoni.4", "9999"));
    }
}
