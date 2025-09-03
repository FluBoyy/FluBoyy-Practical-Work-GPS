package pt.isec.gps2324.gps_g14.App.API;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import pt.isec.gps2324.gps_g14.App.API.Entities.Rating;

public class Ratings {
    private final Db db;
    private final Dao<Rating, Integer> dao;
    
    public Ratings() throws RuntimeException {
        try {
            db = Db.getInstance();
            dao = db.getDao(Rating.class);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize rating API.");
        }
    }

    /**
     * Refreshes a rating from the database.
     * @param rating the rating to be refreshed
     * @throws RuntimeException if the refresh operation fails
     */
    public void refresh(Rating rating) throws RuntimeException {
        try {
            dao.refresh(rating);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to refresh rating.");
        }
    }

    /**
     * Creates a new rating in the database.
     * @param rating the rating to be created
     * @return number of rows affected by the create operation (should be 1)
     * @throws RuntimeException if the create operation fails
     */
    public int create(Rating rating) throws RuntimeException {
        try {
            return dao.create(rating);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create rating.");
        }
    }

    /**
     * Retrieves a rating from the database with the given ID.
     * @param id the ID of the rating to retrieve
     * @return the rating with the given ID, or null if not found
     * @throws RuntimeException if the retrieve operation fails
     */
    public Rating get(int id) throws RuntimeException {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve rating.");
        }
    }

    /**
     * Updates a rating in the database.
     * @param rating the rating to be updated
     * @return number of rows affected by the update operation (should be 1)
     * @throws RuntimeException if the update operation fails
     */
    public int update(Rating rating) throws RuntimeException {
        try {
            return dao.update(rating);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update rating.");
        }
    }

}
