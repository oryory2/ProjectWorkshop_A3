package Tests;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class assignTests extends Tests{

    @org.junit.jupiter.api.Test
    void testAssignGame() throws SQLException {
        assertTrue(UA.assignGame("2","1" ,"1","2022-10-02 11:13:22.002","1"));
    }

    @org.junit.jupiter.api.Test
    void testWrongTeams() throws SQLException {
        assertFalse(UA.assignGame("3","4" ,"1","2022-09-02 11:13:22.002","1"));
    }


    @org.junit.jupiter.api.Test
    void testWrongLeague() throws SQLException {
        assertFalse(UA.assignGame("1","2" ,"4","2022-09-02 11:13:22.002","1"));
    }

    @org.junit.jupiter.api.Test
    void testWrongDateFormat() throws SQLException {
        assertFalse(UA.assignGame("1","2" ,"1","20222-09-02 11:13:22.002","1"));
    }

    @org.junit.jupiter.api.Test
    void testWrongTimeFormat() throws SQLException {
        assertFalse(UA.assignGame("1","2" ,"1","2022-09-02 11:1322:22.002","1"));
    }

    @org.junit.jupiter.api.Test
    void testWrongReferee() throws SQLException {
        assertFalse(UA.assignGame("1","2" ,"1","2022-09-02 11:13:22.002","3"));
    }





}
