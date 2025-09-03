package pt.isec.gps2324.gps_g14.App.API.Entities;


import org.mindrot.jbcrypt.BCrypt;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true)
    private Payment payment;

    @DatabaseField()
    private String location;

    @DatabaseField(canBeNull = false)
    private String username;

    @DatabaseField(canBeNull = false)
    private String password;

    @DatabaseField(canBeNull = false)
    private int level;

    @DatabaseField(canBeNull = false)
    private boolean isAdmin = false;

    @DatabaseField(canBeNull = false)
    private boolean isDisabled = false;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Timeslot> timeslots;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Game> games;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Rating> ratings;
    
    public User() {} // ORMLite needs a no-arg constructor
    public User(String username, String password, int Level, String location) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.isAdmin = false;
        this.level = Level;
        this.isDisabled = false;
        this.location = location;
    }

    private User(String username, String password, int level, boolean isAdmin) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.isAdmin = isAdmin;
        this.level = level;
        this.isDisabled = false;
    }

    public static User Admin(String username, String password) {
        User admin = new User(username, password, 0, true);
        return admin;
    }

    public Payment getPayment() {return payment;}

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }
    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isDisabled(){
        return isDisabled;
    }
    public void setDisabled(boolean disabled){
        isDisabled = disabled;
    }


    public ForeignCollection<Timeslot> getTimeslots() {
        return timeslots;
    }

    public ForeignCollection<Game> getGames() {
        return games;
    }

    public ForeignCollection<Rating> getRatings() {
        return ratings;
    }

    public double getRating(){
        if (this.getRatings() == null)
            return 0;

        return this.getRatings().stream().mapToDouble(r -> r.getRating()).average().orElse(0);
    }

    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return this.level;
    }

    public String getLocation(){return this.location;}

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", isAdmin=" + isAdmin + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof User))
            return false;
        User other = (User) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
