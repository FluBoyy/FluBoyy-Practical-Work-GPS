package pt.isec.gps2324.gps_g14.App.API;


import com.j256.ormlite.dao.Dao;
import pt.isec.gps2324.gps_g14.App.API.Entities.Price;

import java.sql.SQLException;

public class Prices {

    private final Db db;

    private final Dao<Price, Integer> dao;

    public Prices() throws RuntimeException {
        try {
            db = Db.getInstance();
            dao = db.getDao(Price.class);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize games API.");
        }
    }

    /**
     * Refreshes a Payment from the database.
     * @param price The Game object to refresh.
     * @throws RuntimeException if the refresh operation fails.
     */
    public void refresh(Price price) throws RuntimeException {
        try {
            dao.refresh(price);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to refresh game.");
        }
    }

    public int create(Price price) throws RuntimeException {
        try {
            return dao.create(price);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create Price.");
        }
    }

    public boolean deleteTablePayments() {
        try {
            return dao.executeRaw("DROP TABLE IF EXISTS Price") > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete table Payment.");
        }
    }

    public int update(Price price) throws RuntimeException {
        try {
            return dao.update(price);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update price.");
        }
    }

    public double getPriceMeal() throws RuntimeException {
        try {
            return dao.queryBuilder().queryForFirst().getPriceMeal();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get price meal.");
        }
    }

    public double getPriceDesloc(String from, String to) throws RuntimeException {
        try {
            return dao.queryBuilder().where().eq("from", from).and().eq("to", to).queryForFirst().getPriceDesloc();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get price desloc.");
        }
    }

    public double getGamePrize() throws RuntimeException {
        try {
            return dao.queryBuilder().queryForFirst().getGamePrize();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get game prize.");
        }
    }

}
