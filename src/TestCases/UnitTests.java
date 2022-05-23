package TestCases;

import DataAccess.DB_handler;
import Domain.SystemController;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests extends Tests{

    private static SystemController SC;

    @org.junit.jupiter.api.BeforeAll
    static void beforeUnitTests(){
        SC = SystemController.getInstance();
        DB_handler.connect_DB();
        DB_handler.add_league("2020","Champions","10");
        DB_handler.add_referee("1","or");
        DB_handler.addRefereeToLeague("1","1");
        DB_handler.add_team("1","maccabi","1","Blumfield");
        DB_handler.add_team("2","Hapoel","1","Blumfield");
        DB_handler.addGame("1","2","1","2022-09-02 11:13:22.002","1");

}

    @org.junit.jupiter.api.AfterAll
    static void deleteChangesUnitTests() throws SQLException {
        DB_handler.remove_row("referee_in_league","referee_id","1");
        DB_handler.remove_row("games","game_id","1");
        DB_handler.remove_row("referee","referee_id","1");
        DB_handler.remove_row("teams","team_id","1");
        DB_handler.remove_row("teams","team_id","2");
        DB_handler.remove_row("leagues","league_id","1");
        DB_handler.deleteFromSQLSequence();
        DB_handler.disconnect_DB();
    }


    /**
     * Tests for IsRefereeExist function
     */
    @org.junit.jupiter.api.Test
    void testIsRefereeExistTrue() throws SQLException {
        assertTrue(SC.isRefereeExist("1"));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeExistFalse() throws SQLException {
        assertFalse(SC.isRefereeExist("2"));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeExistNull() throws SQLException {
        assertFalse(SC.isRefereeExist(null));
    }

    /**
     * Tests for IsRefereeExistInLeague function
     */
    @org.junit.jupiter.api.Test
    void testIsRefereeExistInLeagueTrue() throws SQLException {
        assertTrue(SC.isRefereeExistInLeague("1","1"));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeExistInLeagueFalse1() throws SQLException {
        assertFalse(SC.isRefereeExistInLeague("1","2"));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeExistInLeagueFalse2() throws SQLException {
        assertFalse(SC.isRefereeExistInLeague("2","1"));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeExistInLeagueNull1() throws SQLException {
        assertFalse(SC.isRefereeExistInLeague(null,"1"));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeExistInLeagueNull2() throws SQLException {
        assertFalse(SC.isRefereeExistInLeague("1",null));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeExistInLeagueNull3() throws SQLException {
        assertFalse(SC.isRefereeExistInLeague(null,null));
    }

    /**
     * Tests for GetRefereeName function
     */
    @org.junit.jupiter.api.Test
    void testGetRefereeNameTrue() throws SQLException {
        assertEquals(SC.getRefereeName("1"),"or");
    }

    @org.junit.jupiter.api.Test
    void testGetRefereeNameNull() throws SQLException {
        assertNull(SC.getRefereeName("2"));
    }

    /**
     * Tests for IsLeagueExist function
     */
    @org.junit.jupiter.api.Test
    void testIsLeagueExistTrue() throws SQLException {
        assertTrue(SC.isLeagueExist("1"));
    }

    @org.junit.jupiter.api.Test
    void testIsLeagueExistFalse() throws SQLException {
        assertFalse(SC.isLeagueExist("2"));
    }

    @org.junit.jupiter.api.Test
    void testIsLeagueExistNull() throws SQLException {
        assertFalse(SC.isLeagueExist(null));
    }


    /**
     * Tests for GetLeagueParams function
     */
    @org.junit.jupiter.api.Test
    void testGetLeagueParamsTrue() throws SQLException {
        ArrayList<String> leaguePresent = new ArrayList<String>() {{
            add("2020");
            add("Champions");
            add("10");
        }};
        assertEquals(SC.getLeagueParams("1"),leaguePresent);
    }

    @org.junit.jupiter.api.Test
    void testGetLeagueParamsNull() throws SQLException {
        assertNull(SC.getLeagueParams("2"));
    }


    /**
     * Tests for isTeamExist function
     */
    @org.junit.jupiter.api.Test
    void testIsTeamExistTrue() throws SQLException {
        assertTrue(SC.isTeamExist("1"));
    }

    @org.junit.jupiter.api.Test
    void testIsTeamExistFalse() throws SQLException {
        assertFalse(SC.isTeamExist("3"));
    }

    @org.junit.jupiter.api.Test
    void testIsTeamExistNull() throws SQLException {
        assertFalse(SC.isTeamExist(null));
    }


    /**
     * Tests for isGameExistInLeague function
     */

    @org.junit.jupiter.api.Test
    void testIsGameExistInLeagueTrue() throws SQLException {
        assertTrue(SC.isGameExistInLeague("1","2","1"));
    }

    @org.junit.jupiter.api.Test
    void testIsGameExistInLeagueFalse() throws SQLException {
        assertFalse(SC.isGameExistInLeague("1","2","2"));
    }

    @org.junit.jupiter.api.Test
    void testIsGameExistInLeagueNull1() throws SQLException {
        assertFalse(SC.isGameExistInLeague("1","2","2"));
    }

    @org.junit.jupiter.api.Test
    void testIsGameExistInLeagueNull2() throws SQLException {
        assertFalse(SC.isGameExistInLeague("1",null,"2"));
    }

    @org.junit.jupiter.api.Test
    void testIsGameExistInLeagueNull3() throws SQLException {
        assertFalse(SC.isGameExistInLeague(null,"2","2"));
    }

    /**
     * Tests for IsRefereeAvailable function
     */
    @org.junit.jupiter.api.Test
    void testIsRefereeAvailableTrue() throws SQLException {
        assertTrue(SC.isRefereeAvailable("1","2022-10-02 11:13:22.002"));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeAvailableFalse1() throws SQLException {
        assertFalse(SC.isRefereeAvailable("1","2022-09-02 11:13:22.002"));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeAvailableTrue2() throws SQLException {
        assertTrue(SC.isRefereeAvailable("2","2022-09-02 11:13:22.002"));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeAvailableNull1() throws SQLException {
        assertTrue(SC.isRefereeAvailable(null,"2022-09-02 11:13:22.002"));
    }

    @org.junit.jupiter.api.Test
    void testIsRefereeAvailableNull2() throws SQLException {
        assertTrue(SC.isRefereeAvailable("1",null));
    }

    /**
     * Tests for IsTeamAvailable function
     */

    @org.junit.jupiter.api.Test
    void testIsTeamAvailableFalse() throws SQLException {
        assertFalse(SC.isTeamAvailable("1","2022-09-02 11:13:22.002"));
    }

    @org.junit.jupiter.api.Test
    void testIsTeamAvailableTrue() throws SQLException {
        assertTrue(SC.isTeamAvailable("1","2022-01-02 11:13:22.002"));
    }

    @org.junit.jupiter.api.Test
    void testIsTeamAvailableNull1() throws SQLException {
        assertTrue(SC.isTeamAvailable(null,"2022-09-02 11:13:22.002"));
    }

    @org.junit.jupiter.api.Test
    void testIsTeamAvailableNull2() throws SQLException {
        assertTrue(SC.isTeamAvailable("1",null));
    }



}
