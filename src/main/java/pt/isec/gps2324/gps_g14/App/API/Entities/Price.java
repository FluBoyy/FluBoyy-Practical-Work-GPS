package pt.isec.gps2324.gps_g14.App.API.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Price")
public class Price {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String from;

    @DatabaseField(canBeNull = false)
    private String to;

    @DatabaseField(canBeNull = false)
    private double priceDesloc;

    @DatabaseField(canBeNull = false)
    private double priceMeal;

    @DatabaseField(canBeNull = false)
    private double gamePrize;

    public Price() {} // ORMLite needs a no-arg constructor

    public Price(String from, String to, double priceDesloc, double priceMeal, double gamePrize) {
        this.from = from;
        this.to = to;
        this.priceDesloc = priceDesloc;
        this.priceMeal = priceMeal;
        this.gamePrize = gamePrize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getPriceDesloc() {
        return priceDesloc;
    }

    public void setPriceDesloc(double priceDesloc) {
        this.priceDesloc = priceDesloc;
    }

    public double getPriceMeal() {
        return priceMeal;
    }

    public void setPriceMeal(double priceMeal) {
        this.priceMeal = priceMeal;
    }

    public double getGamePrize() {
        return gamePrize;
    }

    public void setGamePrize(double gamePrize) {
        this.gamePrize = gamePrize;
    }
}
