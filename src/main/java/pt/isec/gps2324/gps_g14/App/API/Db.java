package pt.isec.gps2324.gps_g14.App.API;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import pt.isec.gps2324.gps_g14.App.API.Entities.*;

import java.sql.SQLException;
import java.util.HashMap;

public class Db {
    static final String databaseUrl = "jdbc:sqlite:mydatabase.db";
    private static Db instance;
    private final ConnectionSource connectionSource;
    private final HashMap<Class<?>, Dao<?, ?>> daos = new HashMap<>();

    public static Db getInstance() throws SQLException {
        if (instance == null) {
            instance = new Db();
        }
        return instance;
    }

    private Db() throws SQLException {
        connectionSource = new JdbcConnectionSource(databaseUrl);
        TableUtils.createTableIfNotExists(connectionSource, User.class);
        TableUtils.createTableIfNotExists(connectionSource, Timeslot.class);
        TableUtils.createTableIfNotExists(connectionSource, Game.class);
        TableUtils.createTableIfNotExists(connectionSource, Rating.class);
        TableUtils.createTableIfNotExists(connectionSource, Payment.class);
        TableUtils.createTableIfNotExists(connectionSource, Price.class);
    }

    public <D extends Dao<T, ?>, T> D getDao(Class<T> dataClass) throws SQLException {
        D dao = (D) daos.get(dataClass);
        if (dao != null)
            return dao;
        dao = DaoManager.createDao(connectionSource, dataClass);
        daos.put(dataClass, dao);
        return dao;
    }
}
