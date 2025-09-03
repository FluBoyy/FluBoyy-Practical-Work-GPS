package pt.isec.gps2324.gps_g14.App.Controllers;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

import pt.isec.gps2324.gps_g14.App.API.Payments;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class RefereeDeassignmentTest {

    PanelAdminController panel = new PanelAdminController();
    Payments paymentsAPI = new Payments();

    @Test
    public void deassignRefereeToGame() {
        Game game = new Game(Sports.FUTEBOL, 1, "test", "test", "test", new Date(), "test");
        game.setReferree(new User("teste","teste", 1, "Coimbra"));

        if(game.getReferree() != null){
            panel.deassignRefereeToGame(paymentsAPI, game);

            assertNull(game.getReferree());
        }

    }
}
