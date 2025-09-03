package pt.isec.gps2324.gps_g14.App.Controllers;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import pt.isec.gps2324.gps_g14.App.API.Games;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.Utils.DateUtils;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class MatchesTest {

    @Test
    void seeAllMatches() throws SQLException {

        Games games = new Games();

        games.create(new Game(Sports.ANDEBOL, 1, "TESTEBRAGA", "TESTELISBON", "LISBON", DateUtils.getDate(2023, 10, 10, 21, 45,0), "PARA TESTE"));
        games.create(new Game(Sports.BASQUET, 2, "TESTEALCARRAZ", "TESTEPRISON", "PRISON", DateUtils.getDate(2023, 10, 10, 21, 45,0), "PARA TESTE"));
        games.create(new Game(Sports.FUTEBOL, 3, "TESTEPORTO", "TESTESPORTING", "COIMBRA", DateUtils.getDate(2023, 10, 10, 21, 45,0), "PARA TESTE"));

        List<Game> gameList = games.getAllGames();

        assertNotNull(gameList, "Upcoming matches list should not be null");

        assertTrue(gameList instanceof List<Game>, "Upcoming matches should be a list");

    }
}
