package Tests;

import DataAccess.DB_handler;
import Domain.Game;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests {


    @org.junit.jupiter.api.BeforeAll
    static void setUp()
    {
        DB_handler.connect_DB();
        System.out.println("DB is ready for testing");
    }

    @org.junit.jupiter.api.Test
    void testCorrectCredentials() throws SQLException {
        assertTrue(DB_handler.checkLogin("yoni.1", "9999"));
    }


    @org.junit.jupiter.api.Test
    void testWrongUsername() throws SQLException {
        assertFalse(DB_handler.checkLogin("yoni.2", "9999"));
    }


    @org.junit.jupiter.api.Test
    void testWrongPassword() throws SQLException {
        assertFalse(DB_handler.checkLogin("yoni.1", "99997"));
    }

    @org.junit.jupiter.api.Test
    void testAddingAndCredentials() throws SQLException {
        assertFalse(DB_handler.checkLogin("yoni.4", "9999"));
        DB_handler.add_row("login", new String[]{"username", "password"}, new String[]{"yoni.4", "9999"});
        assertTrue(DB_handler.checkLogin("yoni.4", "9999"));
        DB_handler.remove_row("login", "username", "yoni.4");
        assertFalse(DB_handler.checkLogin("yoni.4", "9999"));

    }
}
