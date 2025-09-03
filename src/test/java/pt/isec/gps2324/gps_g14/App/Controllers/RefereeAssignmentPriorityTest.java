package pt.isec.gps2324.gps_g14.App.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import pt.isec.gps2324.gps_g14.App.API.ApiUtils;
import pt.isec.gps2324.gps_g14.App.API.Games;
import pt.isec.gps2324.gps_g14.App.API.Ratings;
import pt.isec.gps2324.gps_g14.App.API.Users;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.Rating;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class RefereeAssignmentPriorityTest {

    private static final Games gamesAPI = new Games();
    private static final Ratings ratingsAPI = new Ratings();
    private static final Users usersApi = new Users();

    @Test
    public void levelPriority() {
        User level1User = TestUtils.getTestUser(1);
        User level2User = TestUtils.getTestUser(2);
        User level3User = TestUtils.getTestUser(3);
        List<User> users = new ArrayList<>();
        users.add(level1User);
        users.add(level2User);
        users.add(level3User);

        Calendar cal = Calendar.getInstance();
        Date gameDate = cal.getTime();
        Game game = new Game(Sports.FUTEBOL, 3, "test", "test", "test", gameDate, "test");
        gamesAPI.create(game);

        assertEquals(level3User.getId(), ApiUtils.getBetterUserForAssignment(game, users).getId());
        users.remove(level3User);
        assertEquals(level2User.getId(), ApiUtils.getBetterUserForAssignment(game, users).getId());
        users.remove(level2User);
        assertEquals(level1User.getId(), ApiUtils.getBetterUserForAssignment(game, users).getId());
    }

    @Test
    public void ratingPriority() {
        User _4ratingUser = TestUtils.getTestUser(3);
        User _5ratingUser = TestUtils.getTestUser(3);
        User _0ratingUser = TestUtils.getTestUser(3);
        List<User> users = new ArrayList<>();
        users.add(_4ratingUser);
        users.add(_5ratingUser);
        users.add(_0ratingUser);

        Calendar cal = Calendar.getInstance();
        Date gameDate = cal.getTime();
        Game game = new Game(Sports.FUTEBOL, 1, "test", "test", "test", gameDate, "test");
        gamesAPI.create(game);

        Game ratingGame = new Game(Sports.FUTEBOL, 3, "test", "test", "test", gameDate, "test");
        Game ratingGame2 = new Game(Sports.FUTEBOL, 3, "test", "test", "test", gameDate, "test");
        gamesAPI.create(ratingGame);
        gamesAPI.create(ratingGame2);
        ratingsAPI.create(new Rating(_4ratingUser, ratingGame, 4));
        ratingsAPI.create(new Rating(_5ratingUser, ratingGame2, 5));

        usersApi.refresh(_4ratingUser);
        usersApi.refresh(_5ratingUser);

        assertEquals(_5ratingUser.getId(), ApiUtils.getBetterUserForAssignment(game, users).getId());
        users.remove(_5ratingUser);
        assertEquals(_4ratingUser.getId(), ApiUtils.getBetterUserForAssignment(game, users).getId());
        users.remove(_4ratingUser);
        assertEquals(_0ratingUser.getId(), ApiUtils.getBetterUserForAssignment(game, users).getId());
    }
}