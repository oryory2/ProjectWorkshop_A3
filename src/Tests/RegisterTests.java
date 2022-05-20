package Tests;

import DataAccess.DB_handler;
import Domain.SystemController;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTests extends Tests{


    @org.junit.jupiter.api.BeforeAll
    static void beforeRegisterRefereeTests(){
        DB_handler.connect_DB();
        DB_handler.add_row("referee", new String[]{"referee_id", "referee_name"} ,new String[]{"1", "Hapoel"});
        DB_handler.add_row("leagues", new String[]{"league_id", "season", "league_name", "number_of_teams"} ,new String[]{"1", "2020", "Lumit", "10"});}

    @org.junit.jupiter.api.Test
    void testRegisterReferee() throws SQLException {
        assertTrue(UA.addReferee("1","1"));
    }

    @org.junit.jupiter.api.Test
    void testWrongLeague() throws SQLException {
        assertFalse(UA.addReferee("Ref2","1"));
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
        DB_handler.remove_row("referee","referee_id","1");
        DB_handler.remove_row("leagues","league_id","1");
    }

}
