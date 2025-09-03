package pt.isec.gps2324.gps_g14.App.API;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.Timeslot;

public class Timeslots {
    private final Db db;
    private final Dao<Timeslot, Integer> dao;
    
    public Timeslots() throws RuntimeException {
        try {
            db = Db.getInstance();
            dao = db.getDao(Timeslot.class);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize timeslot API.");
        }
    }

    /**
     * Refreshes a timeslot from the database.
     * @param timeslot the timeslot to be refreshed
     * @throws RuntimeException if the refresh operation fails
     */
    public void refresh(Timeslot timeslot) throws RuntimeException {
        try {
            dao.refresh(timeslot);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to refresh timeslot.");
        }
    }

    /**
     * Creates a new timeslot in the database.
     * @param timeslot the timeslot to be created
     * @return number of rows affected by the create operation (should be 1)
     */
    public int create(Timeslot timeslot) throws RuntimeException {
        try {
            return dao.create(timeslot);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create timeslot.");
        }
    }

    /**
     * Retrieves a timeslot from the database with the given ID.
     * @param id the ID of the timeslot to retrieve
     * @return the timeslot with the given ID, or null if not found
     */
    public Timeslot get(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve timeslot.");
        }
    }

    /**
     * Retrieves all timeslots that start after the given start time and end before the given end time.
     * @param startTime the start time
     * @param endTime the end time
     * @return a list of timeslots that start after the given start time and end before the given end time
     */
    public List<Timeslot> get(Date startTime, Date endTime) {
        try {
            return dao.query(dao.queryBuilder().where().le("startTime", startTime).and().ge("endTime", endTime).prepare());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve timeslots.");
        }
    }

    /**
     * Delete a timeslot from the database with the given ID.
     * @param id the ID of the timeslot to delete
     */
    public void remove(int id) {
        try {
            if (dao.deleteById(id) != 1)
                throw new RuntimeException("Failed to remove timeslot.");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove timeslot.");
        }
    }

    /**
     * Retrieves all timeslots that are available for the given game.
     * @param game the game to search for
     */
    public List<Timeslot> get(Game game) {
        try {
            return get(game.getGameDate(), game.getEndTime());
        }  catch (RuntimeException e) {
            throw new RuntimeException("Failed to retrieve timeslots.");
        }
    }
}
