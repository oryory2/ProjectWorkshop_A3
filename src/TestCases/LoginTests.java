package TestCases;

import DataAccess.DB_handler;
import Domain.Game;
import Domain.SystemController;
import Service.UserApplication;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends Tests {


    @org.junit.jupiter.api.BeforeAll
    static void beforeLoginTests(){
        DB_handler.connect_DB();
        DB_handler.add_row("login", new String[]{"username", "password"} ,new String[]{"amit", "12345678"});}

    @org.junit.jupiter.api.AfterAll
    static void afterLoginTests() throws SQLException {
        DB_handler.remove_row("login","username","amit");
        DB_handler.disconnect_DB();
    }

    @org.junit.jupiter.api.Test
    void testCorrectCredentials() throws SQLException {
        assertTrue(UA.loginUser("amit","12345678"));
    }

    @org.junit.jupiter.api.Test
    void testWrongUsername() throws SQLException {
        assertFalse(UA.loginUser("amit12","1234"));
    }

    @org.junit.jupiter.api.Test
    void testWrongPassword() throws SQLException {
        assertFalse(UA.loginUser("amit","12345"));
    }

    @org.junit.jupiter.api.Test
    void testNullPasswordWithExistUserName() throws SQLException {
        assertFalse(UA.loginUser("amit",null));
    }

    @org.junit.jupiter.api.Test
    void testNullUsername() throws SQLException {
        assertFalse(UA.loginUser(null,"12345"));
    }

    @org.junit.jupiter.api.Test
    void testNullPassword() throws SQLException {
        assertFalse(UA.loginUser("test",null));
    }

}
