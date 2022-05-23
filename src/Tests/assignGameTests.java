package Tests;

import DataAccess.DB_connector;
import DataAccess.DB_handler;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class assignGameTests extends Tests{

    @org.junit.jupiter.api.BeforeAll
    static void beforeAssignTests(){
        DB_handler.connect_DB();
        DB_handler.add_league("2020","Champions","10");
        DB_handler.add_referee("1","or");
        DB_handler.add_team("1","maccabi","1","Blumfield");
        DB_handler.add_team("2","Hapoel","1","Tedy");
        DB_handler.addRefereeToLeague("1","1");
    }



    @org.junit.jupiter.api.AfterAll
    static void deleteChangesUnitTests() throws SQLException {
        DB_handler.remove_row("referee_in_league","referee_id","1");
        DB_handler.remove_row("referee","referee_id","1");
        DB_handler.remove_row("teams","team_id","1");
        DB_handler.remove_row("teams","team_id","2");
        DB_handler.remove_row("games","game_id","1");
        DB_handler.remove_row("leagues","league_name","Champions");
        DB_handler.deleteFromSQLSequence();

    }


    @Order(1)
    @org.junit.jupiter.api.Test
    void testAssignGame() throws SQLException {
        assertTrue(UA.assignGame("1","2" ,"1","2023-01-01 16:30:00.000","1"));

    }

    @Order(2)
    @org.junit.jupiter.api.Test
    void testGameSameTime() throws SQLException {
        assertFalse(UA.assignGame("2","1" ,"1","2023-01-01 16:30:00.000","1"));

    }

    @Order(3)
    @org.junit.jupiter.api.Test
    void testGameDiffTime() throws SQLException {
        assertTrue(UA.assignGame("2","1" ,"1","2023-02-01 16:30:00.000","1"));
    }

    @Order(4)
    @org.junit.jupiter.api.Test
    void testWrongTeams() throws SQLException {
        assertFalse(UA.assignGame("3","1" ,"1","2022-09-02 11:13:22.002","1"));
    }

    @Order(5)
    @org.junit.jupiter.api.Test
    void testWrongLeague() throws SQLException {
        assertFalse(UA.assignGame("1","2" ,"4","2022-09-02 11:13:22.002","1"));
    }

    @Order(6)
    @org.junit.jupiter.api.Test
    void testWrongDateFormat() throws SQLException {
        assertFalse(UA.assignGame("1","2" ,"1","20222-09-02 11:13:22.002","1"));
    }

    @Order(7)
    @org.junit.jupiter.api.Test
    void testWrongTimeFormat() throws SQLException {
        assertFalse(UA.assignGame("1","2" ,"1","2022-09-02 11:1322:22.002","1"));
    }

    @org.junit.jupiter.api.Test
    void testWrongReferee() throws SQLException {
        assertFalse(UA.assignGame("1","2" ,"1","2022-09-02 11:13:22.002","3"));
    }


}
