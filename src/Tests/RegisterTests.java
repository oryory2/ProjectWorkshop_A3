package Tests;

import DataAccess.DB_handler;
import Domain.SystemController;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTests extends Tests{

    @org.junit.jupiter.api.Test
    void testRegisterReferee() throws SQLException {
        assertTrue(UA.addReferee("1","1"));
    }

    @org.junit.jupiter.api.Test
    void testWrongLeague() throws SQLException {
        assertFalse(UA.addReferee("Ref2","7"));
    }


    @org.junit.jupiter.api.Test
    void testNullLeague() throws SQLException {
        assertFalse(UA.addReferee(null,"6"));
    }

    @org.junit.jupiter.api.Test
    void testNullname() throws SQLException {
        assertFalse(UA.addReferee("Ref2",null));
    }

    @org.junit.jupiter.api.AfterAll
    static void deleteChanges() throws SQLException {
        DB_handler.remove_row("referee_in_league","referee_id","1");
    }


}
