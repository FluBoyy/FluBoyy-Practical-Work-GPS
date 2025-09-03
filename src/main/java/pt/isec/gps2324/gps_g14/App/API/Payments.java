package pt.isec.gps2324.gps_g14.App.API;

import com.j256.ormlite.dao.Dao;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.Payment;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;

import java.sql.SQLException;
import java.util.List;

public class Payments {

    private final Db db;

    private final Dao<Payment, Integer> dao;

    public Payments() throws RuntimeException {
        try {
            db = Db.getInstance();
            dao = db.getDao(Payment.class);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize games API.");
        }
    }

    /**
     * Refreshes a Payment from the database.
     * @param pay The Game object to refresh.
     * @throws RuntimeException if the refresh operation fails.
     */
    public void refresh(Payment pay) throws RuntimeException {
        try {
            dao.refresh(pay);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to refresh game.");
        }
    }

    public int create(Payment pay) throws RuntimeException {
        try {
            return dao.create(pay);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create Payment.");
        }
    }

    public boolean deleteTablePayments() {
        try {
            return dao.executeRaw("DROP TABLE IF EXISTS Payment") > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete table Payment.");
        }
    }

    public void delete(Payment pay) throws RuntimeException {
        try {
            dao.delete(pay);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete game.");
        }
    }

    public int update(Payment pay) throws RuntimeException {
        try {
            return dao.update(pay);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update game.");
        }
    }

    // gets payments
    public Payment getPaymentFromID(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve payment.");
        }
    }
    public List<Payment> getPaymentFromUser(User user) {
        try {
            return dao.queryForEq("user_id", user.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve the payment.");
        }
    }
    public List<Payment> getAllPayments() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve the payment.");
        }
    }

    public void setPaymentPaid(Payment pay) {
        try {
            pay.setPaid(true);
            dao.update(pay);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update payment.");
        }
    }


}
