package pt.isec.gps2324.gps_g14.App.API;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class Games {

    private final Db db;

    private final Dao<Game, Integer> dao;

    public Games() throws RuntimeException {
        try {
            db = Db.getInstance();
            dao = db.getDao(Game.class);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize games API.");
        }
    }

    /**
     * Refreshes a game from the database.
     * @param game The Game object to refresh.
     * @throws RuntimeException if the refresh operation fails.
     */
    public void refresh(Game game) throws RuntimeException {
        try {
            dao.refresh(game);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to refresh game.");
        }
    }
    
    public int create(Game game) throws RuntimeException {
        try {
            return dao.create(game);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create game.");
        }
    }

    public boolean deleteTableGames() {
        try {
            return dao.executeRaw("DROP TABLE IF EXISTS Game") > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete table games.");
        }
    }

    /**
     * Updates a Game in the database.
     * @param game The Game object to update.
     * @return The number of rows affected by the update operation.
     * @throws RuntimeException if the update operation fails.
     */
    public int update(Game game) throws RuntimeException {
        try {
            return dao.update(game);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update game.");
        }
    }

    public boolean deleteAllGames() {
        try {
            return dao.deleteBuilder().delete() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete games.");
        }
    }

    public boolean delete(Game game) {
        try {
            return dao.delete(game) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete game.");
        }
    }


    //Gets
    public List<Game> getAllGames() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve games.");
        }
    }

    public List<Game> getFutureGames() {
        try {
            return dao.query(dao.queryBuilder().where().ge("GameDate", new Date()).prepare());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve games.");
        }
    }

    public List<Game> getPastGames() {
        try {
            return dao.query(dao.queryBuilder().where().le("GameDate", new Date()).prepare());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve games.");
        }
    }

    public List<Game> getGameFromModalitys(Sports modality) {
        try {
            return dao.query(dao.queryBuilder().where().eq("Modality", modality.name()).prepare());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve games.");
        }
    }

    public Game getGameFromID(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve game.");
        }
    }

    public List<Game> getGameFromDate(Date date) {
        try {
            return dao.query(dao.queryBuilder().where().eq("GameDate", date).prepare());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve games.");
        }
    }

    public List<Game> getGameFromUser(User user) {
        try {
            return dao.query(dao.queryBuilder().where().eq("referree_id", user.getId()).prepare());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve games.");
        }
    } // para os payments

    public List<Game> getGameFromUserAndDate(User user, Date date) {
        try {
            return dao.query(dao.queryBuilder().where().eq("referree_id", user.getId()).and().eq("GameDate", date).prepare());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve games.");
        }
    }

    public List<Game> getGameFromModalityAndDate(Sports modality, Date date) {
        try {
            return dao.query(dao.queryBuilder().where().eq("Modality", modality.name()).and().eq("GameDate", date).prepare());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve games.");
        }
    }

}
