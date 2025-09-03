package pt.isec.gps2324.gps_g14.App.API;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import pt.isec.gps2324.gps_g14.App.API.Entities.User;

public class Users {
    private final Db db;
    private final Dao<User, Integer> dao;
    
    public Users() throws RuntimeException {
        try {
            db = Db.getInstance();
            dao = db.getDao(User.class);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize users API.");
        }
    }

    /**
     * Refreshes a user from the database.
     * @param user the user to be refreshed
     * @throws RuntimeException if the refresh operation fails
     */
    public void refresh(User user) throws RuntimeException {
        try {
            dao.refresh(user);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to refresh user.");
        }
    }

    /**
     * Checks if a user with the given username exists in the database.
     *
     * @param username the username to check
     * @return true if a user with the given username exists, false otherwise
     * @throws RuntimeException if there was an error checking if the user exists
     */
    public boolean exists(String username) {
        try {
            return dao.countOf(dao.queryBuilder().setCountOf(true).where().eq("username", username).prepare()) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check if user exists.");
        }
    }

    /**
     * Creates a new user in the database.
     * @param user the user to be created
     * @return number of rows affected by the create operation (should be 1)
     * @throws RuntimeException if the user creation fails
     */
    public int create(User user) {
        try {
            return dao.create(user);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create user.");
        }
    }

    /**
     * Retrieves a User object from the database with the given username.
     *
     * @param username the username of the user to retrieve
     * @return the User object with the given username, or null if not found
     * @throws RuntimeException if there is an error retrieving the user from the database
     */
    public User get(String username) {
        try {
            return dao.queryForFirst(dao.queryBuilder().where().eq("username", username).prepare());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get user.");
        }
    }

    /**
     * Retrieves a User object from the database with the given ID.
     *
     * @param id the ID of the user to retrieve
     * @return the User object with the given ID, or null if not found
     * @throws RuntimeException if there is an error retrieving the user from the database
     */
    public User get(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get user.");
        }
    }

    public List<User> getReferees() {
        try {
            return dao.query(dao.queryBuilder().where().eq("isAdmin", false).prepare());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get users.");
        }
    }

    /**
     * Updates a user in the database.
     * @param user The user to be updated.
     * @return The number of rows affected by the update operation.
     * @throws RuntimeException if the update operation fails.
     */
    public int update(User user) {
        try {
            return dao.update(user);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update user.");
        }
    }

    public int delete(User user) {
        try {
            return dao.delete(user);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user.");
        }
    }

    public void disable(User disableReferee) {
        disableReferee.setDisabled(true);
        update(disableReferee);
    }

    public void enable(User disable) {
        disable.setDisabled(false);
        update(disable);
    }
}
