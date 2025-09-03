package pt.isec.gps2324.gps_g14.App.API.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Rating")
public class Rating {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh=true)
    private User referree;

    @DatabaseField(foreign = true, foreignAutoRefresh=true, unique= true)
    private Game game;

    @DatabaseField(canBeNull = false)
    private int rating;

    public Rating() {} // ORMLite needs a no-arg constructor
    public Rating(User referree, Game game, int rating) {
        this.referree = referree;
        this.game = game;
        this.rating = rating;
    }

    public User getReferree() {
        return referree;
    }
    public Game getGame() {
        return game;
    }
    public int getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
}
