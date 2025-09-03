package pt.isec.gps2324.gps_g14.App.API.Entities;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "timeslots")
public class Timeslot {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh=true)
    private User user;

    @DatabaseField(canBeNull = false, dataType = DataType.DATE_STRING)
    private Date startTime;

    @DatabaseField(canBeNull = false, dataType = DataType.DATE_STRING)
    private Date endTime;

    public Timeslot() {} // ORMLite needs a no-arg constructor
    public Timeslot(User user, Date startTime, Date endTime) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Timeslot [id=" + id + ", user=" + user + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Timeslot)) return false;
        Timeslot other = (Timeslot) obj;
        return this.id == other.id;
    }
    
}
