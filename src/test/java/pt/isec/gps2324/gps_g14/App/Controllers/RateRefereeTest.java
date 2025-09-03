package pt.isec.gps2324.gps_g14.App.Controllers;

import org.junit.jupiter.api.Test;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.Rating;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.API.Ratings;
import pt.isec.gps2324.gps_g14.App.API.Users;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RateRefereeTest {

    @Test
    void RateReferee(){
        Ratings ratingsAPI = new Ratings();
        User referee = new User("testeRate", "testeRate", 1, "Coimbra");
        Game game = new Game(Sports.FUTEBOL, 1, "homeTeam", "visitorTeam", "Coimbra", new Date(), "desc");

        game.setReferree(referee);

        Rating rating = new Rating(referee, game, 1);

        ratingsAPI.create(rating);

        assertEquals(rating.getRating(), ratingsAPI.get(rating.getId()).getRating());
    }
}
