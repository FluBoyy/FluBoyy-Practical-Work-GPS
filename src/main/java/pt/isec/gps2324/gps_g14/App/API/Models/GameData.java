package pt.isec.gps2324.gps_g14.App.API.Models;

public class GameData {

    private static GameData instance;

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

}
