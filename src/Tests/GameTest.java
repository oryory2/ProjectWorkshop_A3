package Tests;


import Domain.Game;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GameTest
{
    private Game game;

    @org.junit.jupiter.api.BeforeEach
    void setUp()
    {
        this.game = new Game("1", "Barcelona", "Hapoel Kfar Sava", "Champions League", LocalDateTime.now(), null, "Camp Nou");
    }

    @org.junit.jupiter.api.Test
    void testSetScore()
    {
        assertTrue(game.setScore(2, 0));
        assertFalse(game.setScore(-2, 2));
    }

    @org.junit.jupiter.api.Test
    void testSetTeam1()
    {
        assertTrue(game.setTeam1("Hapoel Beer Sheva"));
        assertFalse(game.setTeam1(null));
        assertFalse(game.setTeam1("Hapoel Kfar Sava"));
    }

    @org.junit.jupiter.api.Test
    void testSetTeam2()
    {
        assertTrue(game.setTeam2("Hapoel Beer Sheva"));
        assertFalse(game.setTeam2(null));
        assertFalse(game.setTeam2("Barcelona"));
    }

    @org.junit.jupiter.api.Test
    void testSetGameId()
    {
        assertTrue(game.setGameId("324"));
        assertFalse(game.setGameId(null));
    }

    @org.junit.jupiter.api.Test
    void testSetLeagueId()
    {
        assertTrue(game.setLeagueId("234"));
        assertFalse(game.setLeagueId(null));
    }

    @org.junit.jupiter.api.Test
    void testSetField()
    {
        assertTrue(game.setField("Bloomfield"));
        assertFalse(game.setField(null));
    }
}