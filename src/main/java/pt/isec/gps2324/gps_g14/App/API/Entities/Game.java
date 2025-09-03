package pt.isec.gps2324.gps_g14.App.API.Entities;

import java.util.Calendar;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import pt.isec.gps2324.gps_g14.App.Utils.Sports;

@DatabaseTable(tableName = "Game")
public class Game {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh=true)
    private User referree;

    @DatabaseField(canBeNull = false, dataType = DataType.ENUM_STRING)
    private Sports Modality;

    @DatabaseField(canBeNull = false)
    private int Division;

    @DatabaseField(canBeNull = false)
    private String HomeTeam;

    @DatabaseField(canBeNull = false)
    private String VisitorTeam;

    @DatabaseField(canBeNull = false)
    private String Location;

    @DatabaseField(canBeNull = false, dataType = DataType.DATE_STRING)
    private Date GameDate;

    @DatabaseField(canBeNull = false)
    private String GameDesc;

    @DatabaseField(foreign = true, foreignAutoRefresh=true)
    private Rating rating;


    public Game() {} // ORMLite needs a no-arg constructor


    public Game(Sports modality, int Division, String homeTeam, String visitorTeam, String location, Date gameDate, String gameDesc) {
        this.Modality = modality;
        this.Division = Division;
        this.HomeTeam = homeTeam;
        this.VisitorTeam = visitorTeam;
        this.Location = location;
        this.GameDate = gameDate;
        this.GameDesc = gameDesc;
    }

    public int getId() {return id;}

    public User getReferree() {
        return referree;
    }

    public void setReferree(User referree) {this.referree = referree;}

    public String getHomeTeam() {return HomeTeam;}

    public void setHomeTeam(String homeTeam) {HomeTeam = homeTeam;}

    public String getVisitorTeam() {return VisitorTeam;}

    public void setVisitorTeam(String visitorTeam) {VisitorTeam = visitorTeam;}

    public String getLocation() {return Location;}

    public void setLocation(String location) {Location = location;}

    public Date getGameDate() {return GameDate;}

    public void setGameDate(Date gameDate) {GameDate = gameDate;}

    public Sports getModality() {return Modality;}

    public void setModality(Sports modality) {Modality = modality;}

    public int getDivision() {return Division;}
    public void setDivision(int division) {Division = division;}

    public Date getEndTime() {
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(GameDate);
        endCal.add(Calendar.MINUTE, getDuration());
        return endCal.getTime();
    }

    public int getDuration() {
        return Modality.getDuration();
    }

    public void setGameDesc(String gameDesc) {GameDesc = gameDesc;}
    public String getGameDesc() {return GameDesc;}

    public Rating getRating() {return rating;}

    public boolean overlapsWith(Game game){
        if (game.getEndTime().before(this.GameDate))
            return false;
        if (game.GameDate.after(this.getEndTime()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Games:\n" +
                "id = " + id +
                ", referree = " + referree +
                ", HomeTeam ='" + HomeTeam + '\'' +
                ", VisitorTeam ='" + VisitorTeam + '\'' +
                ", Location ='" + Location + '\'' +
                ", GameDate = " + GameDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Game) {
            Game game = (Game) obj;
            return game.getId() == this.getId();
        }
        return false;
    }
}
