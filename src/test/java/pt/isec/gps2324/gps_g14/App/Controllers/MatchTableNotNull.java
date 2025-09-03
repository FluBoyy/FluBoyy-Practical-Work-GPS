package pt.isec.gps2324.gps_g14.App.Controllers;

import org.junit.jupiter.api.Test;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Games;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;


public class MatchTableNotNull {
    @Test
    void seeFutureMatches() throws SQLException {


        Games games = new Games();

        List<Game> gameList = games.getFutureGames();

        assertNotNull(gameList, "Upcoming matches list should not be null");

        assertTrue(gameList instanceof List<Game>, "Upcoming matches should be a list of a future matches");

    }
}
