package pt.isec.gps2324.gps_g14.App.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.Rating;
import pt.isec.gps2324.gps_g14.App.API.Entities.Timeslot;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;

public class ApiUtils {
    private static final Users usersAPI = new Users();
    private static final Games gamesAPI = new Games();
    private static final Timeslots timeslotsAPI = new Timeslots();
    private static final Ratings ratingsAPI = new Ratings();

    public static User assignRefereeToGame(Game game) {
        if (game.getReferree() != null)
            return null;
        List<Timeslot> timeslots = timeslotsAPI.get(game);
        if (timeslots.size() == 0)
            return null;

        List<User> users = timeslots.stream().map((t) -> t.getUser()).toList();
        
        User referree = getBetterUserForAssignment(game, users);
        game.setReferree(referree);
        gamesAPI.update(game);
        return referree;
    }

    public static Map<User, Integer> assignRefereeToGames(List<Game> games) {
        Map<User, Integer> assignments = new HashMap<>();
        for (Game game : games) {
            User referree = assignRefereeToGame(game);
            if (referree != null)
                assignments.put(referree, assignments.getOrDefault(referree, 0) + 1);
        }
        return assignments;
    }

    public static User getBetterUserForAssignment(Game game, List<User> users) {
        List<User> filteredUsers = users.stream().filter((u) -> {
            if (u.isDisabled())
                return false;
            if (u.getLevel() == 3)
                return true;
            if (u.getLevel() == 2 && game.getDivision() >= 2)
                return true;
            if (u.getLevel() == 1 && game.getDivision() == 3)
                return true;
            return false;
        }).toList();
        
        filteredUsers = filteredUsers.stream().filter((u) -> {
            if (u.getGames() == null)
                return true;
            boolean hasCoincidingGames = u.getGames().stream().anyMatch((g) -> {
                if (g.getId() == game.getId())
                    return false;
                return g.overlapsWith(game);
            });
            return !hasCoincidingGames;
        }).toList();

        if (filteredUsers.size() == 0)
            return null;

        ArrayList<User> users2 = new ArrayList<>(filteredUsers);
        //sort users by level and rating
        users2.sort((u1, u2) -> {
            if (u1.getLevel() != u2.getLevel())
                return u2.getLevel() - u1.getLevel();
            if (u1.getRating() < u2.getRating())
                return 1;
            if (u1.getRating() > u2.getRating())
                return -1;
            return 0;
        });
        return users2.get(0);
    }
    
    /**
     * Rate a game from 0 to 5
     * @param game Game to rate
     * @param rating Rating from 0 to 5
     * @throws RuntimeException if game has not ended yet, if rating is not between 0 and 5, if game has no referree
     */
    public static void rateGame(Game game, int rating) throws RuntimeException
    {
        gamesAPI.refresh(game);
        
        if (game.getEndTime().after(new java.util.Date()))
            throw new RuntimeException("Game has not ended yet");
        
        if (rating < 0 || rating > 5)
            throw new RuntimeException("Rating must be between 0 and 5");
        
        if (game.getReferree() == null)
            throw new RuntimeException("Game has no referree");
        
        if (game.getRating() != null){
            Rating r = game.getRating();
            r.setRating(rating);
            ratingsAPI.update(r);
        }
        else {
            Rating r = new Rating(game.getReferree(), game, rating);
            ratingsAPI.create(r);
        }       
    }
    
}
