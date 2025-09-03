package pt.isec.gps2324.gps_g14.App.API.Models;

import pt.isec.gps2324.gps_g14.App.API.Entities.User;

public class UserData {
    private static UserData instance;

    private User currentUser;

    private UserData() {
    }

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
