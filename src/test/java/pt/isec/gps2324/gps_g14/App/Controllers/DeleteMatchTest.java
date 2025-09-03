package pt.isec.gps2324.gps_g14.App.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import pt.isec.gps2324.gps_g14.App.API.Games;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;
public class DeleteMatchTest{
    @Test
    public void  testHandleGameDeletion(){
        Games gamesAPI=new Games();
        int numGames=gamesAPI.getAllGames().size();
        Game initialGame=new Game();
        initialGame.setModality(Sports.FUTEBOL);
        initialGame.setDivision(2);
        initialGame.setGameDesc("Test Game");
        initialGame.setLocation("Stadium");
        initialGame.setHomeTeam("TeamA");
        initialGame.setVisitorTeam("TeamB");

        Calendar cal=Calendar.getInstance();
        cal.set(2023,Calendar.JANUARY,1,12,30); // Set a specific date and time
        initialGame.setGameDate(cal.getTime());

        // Assume that the deletion method is called here
        gamesAPI.delete(initialGame);

        assertEquals(numGames,gamesAPI.getAllGames().size());
    }
}
