package pt.isec.gps2324.gps_g14.App.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class ModifyMatchTest {

    @Test
    public void testHandleSaveButton() {
        // Create a sample game for testing
        Game initialGame = new Game();
        initialGame.setModality(Sports.FUTEBOL);
        initialGame.setDivision(2);
        initialGame.setGameDesc("Test Game");
        initialGame.setLocation("Stadium");
        initialGame.setHomeTeam("TeamA");
        initialGame.setVisitorTeam("TeamB");

        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.JANUARY, 1, 12, 30); // Set a specific date and time
        initialGame.setGameDate(cal.getTime());


        initialGame.setModality(Sports.BASQUET);
        initialGame.setDivision(1);
        initialGame.setGameDesc("Updated Description");
        initialGame.setLocation("Updated Location");
        initialGame.setHomeTeam("Updated TeamA");
        initialGame.setVisitorTeam("Updated TeamB");

        assertEquals(Sports.BASQUET, initialGame.getModality());
        assertEquals(1, initialGame.getDivision());
        assertEquals("Updated Description", initialGame.getGameDesc());
        assertEquals("Updated Location", initialGame.getLocation());
        assertEquals("Updated TeamA", initialGame.getHomeTeam());
        assertEquals("Updated TeamB", initialGame.getVisitorTeam());

    }
}
