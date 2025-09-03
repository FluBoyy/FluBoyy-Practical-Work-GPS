package pt.isec.gps2324.gps_g14.App.Controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import pt.isec.gps2324.gps_g14.App.API.ApiUtils;
import pt.isec.gps2324.gps_g14.App.API.Games;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class RatingTest {
    private static final Games gamesAPI = new Games();

    @Test
    public void matchHasntFinished(){

        User testUser = TestUtils.getTestUser(1);

        Calendar cal = Calendar.getInstance();
        Date gameDate = cal.getTime();
        Game game = new Game(Sports.FUTEBOL, 1, "test", "test", "test", gameDate, "test");
        game.setReferree(testUser);
        gamesAPI.create(game);

        assertThrows(RuntimeException.class, () -> {
            ApiUtils.rateGame(game, 0);
        }, "Game has not ended yet");
    }

    @Test
    public void refereeNotAssigned(){
        Calendar cal = Calendar.getInstance();
        Date gameDate = cal.getTime();
        Game game = new Game(Sports.FUTEBOL, 1, "test", "test", "test", gameDate, "test");
        gamesAPI.create(game);

        assertThrows(RuntimeException.class, () -> {
            ApiUtils.rateGame(game, 0);
        });
    }

    @Test
    public void ratingOutOfBounds(){
        User testUser = TestUtils.getTestUser(1);

        Calendar cal = Calendar.getInstance();
        Date gameDate = cal.getTime();
        Game game = new Game(Sports.FUTEBOL, 1, "test", "test", "test", gameDate, "test");
        game.setReferree(testUser);
        gamesAPI.create(game);

        assertThrows(RuntimeException.class, () -> {
            ApiUtils.rateGame(game, 6);
        });
        assertThrows(RuntimeException.class, () -> {
            ApiUtils.rateGame(game, -1);
        });
    }

    @Test
    public void rating(){
        User testUser = TestUtils.getTestUser(1);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date gameDate = cal.getTime();
        Game game = new Game(Sports.FUTEBOL, 1, "test", "test", "test", gameDate, "test");
        game.setReferree(testUser);
        gamesAPI.create(game);

        assertDoesNotThrow(() -> {
            ApiUtils.rateGame(game, 5);
        });
    }
}
