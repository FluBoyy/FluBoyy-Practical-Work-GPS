package pt.isec.gps2324.gps_g14.App.API.Entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.List;

@DatabaseTable(tableName = "Payment")
public class Payment {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true)
    private User user;

    @DatabaseField(canBeNull = false)
    private int gameID;

    @DatabaseField(canBeNull = false, dataType = DataType.DATE_STRING)
    private Date gameDate;

    @DatabaseField(canBeNull = false)
    private String gameHomeTeam;

    @DatabaseField(canBeNull = false)
    private String gameVisitorTeam;

    @DatabaseField(canBeNull = false)
    private int gameDivision;

    @DatabaseField(canBeNull = false)
    private String gameLocation;

    @DatabaseField(canBeNull = false)
    private Double gamePrice;

    @DatabaseField(canBeNull = false)
    private double priceMeal;

    @DatabaseField(canBeNull = false)
    private double priceDisp;

    @DatabaseField(canBeNull = false)
    private double totalAmount;

    @DatabaseField(canBeNull = false)
    private boolean paid;

    @DatabaseField(dataType = DataType.DATE_STRING)
    private Date paymentDate;

    @DatabaseField()
    private String paymentDesc;

    public Payment() {} // ORMLite needs a no-arg constructor

    public Payment(User user,int gameID, Date gameDate, String gameHomeTeam, String gameVisitorTeam, int gameDivision, String gameLocation, Double gamePrice, double priceMeal, double priceDisp, double totalAmount) {
        this.user = user;
        this.gameID = gameID;
        this.gameDate = gameDate;
        this.gameHomeTeam = gameHomeTeam;
        this.gameVisitorTeam = gameVisitorTeam;
        this.gameDivision = gameDivision;
        this.gameLocation = gameLocation;
        this.gamePrice = gamePrice;
        this.priceMeal = priceMeal;
        this.priceDisp = priceDisp;
        this.totalAmount = totalAmount;
        this.paid = false;
    }

    public double getPriceDisp() {return priceDisp;}

    public void setPriceDisp(double priceDisp) {this.priceDisp = priceDisp;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentDesc() {
        return paymentDesc;
    }

    public void setPaymentDesc(String paymentDesc) {
        this.paymentDesc = paymentDesc;
    }

    public double getTotalAmount() {return totalAmount;}

    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

    public Double getGamePrice() {return gamePrice;}

    public void setGamePrice(Double gamePrice) {this.gamePrice = gamePrice;}

    public double getPriceMeal() {return priceMeal;}

    public void setPriceMeal(double priceMeal) {this.priceMeal = priceMeal;}

    public int getGameID() {return gameID;}

    public void setGameID(int gameID) {this.gameID = gameID;}

    public Date getGameDate() {return gameDate;}

    public void setGameDate(Date gameDate) {this.gameDate = gameDate;}

    public String getGameHomeTeam() {return gameHomeTeam;}

    public void setGameHomeTeam(String gameHomeTeam) {this.gameHomeTeam = gameHomeTeam;}

    public String getGameVisitorTeam() {return gameVisitorTeam;}

    public void setGameVisitorTeam(String gameVisitorTeam) {this.gameVisitorTeam = gameVisitorTeam;}

    public int getGameDivision() {return gameDivision;}

    public void setGameDivision(int gameDivision) {this.gameDivision = gameDivision;}

    public String getGameLocation() {return gameLocation;}

    public void setGameLocation(String gameLocation) {this.gameLocation = gameLocation;}

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", user=" + user +
                ", gameID=" + gameID +
                ", gameDate=" + gameDate +
                ", gameHomeTeam='" + gameHomeTeam + '\'' +
                ", gameVisitorTeam='" + gameVisitorTeam + '\'' +
                ", gameDivision=" + gameDivision +
                ", gameLocation='" + gameLocation + '\'' +
                ", gamePrice=" + gamePrice +
                ", priceMeal=" + priceMeal +
                ", priceDisp=" + priceDisp +
                ", totalAmount=" + totalAmount +
                ", paid=" + paid +
                ", paymentDate=" + paymentDate +
                ", paymentDesc='" + paymentDesc + '\'' +
                '}';
    }
}
