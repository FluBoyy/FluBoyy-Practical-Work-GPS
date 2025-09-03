package pt.isec.gps2324.gps_g14.App.Controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.j256.ormlite.dao.ForeignCollection;

import pt.isec.gps2324.gps_g14.App.API.Games;
import pt.isec.gps2324.gps_g14.App.API.Timeslots;
import pt.isec.gps2324.gps_g14.App.API.Users;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.Timeslot;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class RefereePanelControllerTest {
    Games gamesAPI = new Games();
    Users userAPI = new Users();
    Timeslots timeslotApi = new Timeslots();

    @Test
    public void fetchGames() {
        Game test = new Game(Sports.FUTEBOL, 1, "test", "test", "test", new Date(), "test");
        gamesAPI.create(test);
        List<Game> idk = gamesAPI.getAllGames();
        assertTrue(idk.contains(test));
    }

    @Test
    public void fetchTimeslots() {
        User user = TestUtils.getTestUser();
        Timeslot test = new Timeslot(user, new Date(), new Date());
        timeslotApi.create(test);
        userAPI.refresh(user);
        ForeignCollection<Timeslot> timeslots = user.getTimeslots();
        assertTrue(timeslots.contains(test));
    }
}
