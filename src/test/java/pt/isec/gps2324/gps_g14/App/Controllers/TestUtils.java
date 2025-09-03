package pt.isec.gps2324.gps_g14.App.Controllers;

import java.util.UUID;

import pt.isec.gps2324.gps_g14.App.API.Users;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;

public class TestUtils {
    private static final Users usersApi = new Users();

    public static User getTestUser() {
        return getTestUser(1);
    }

    public static User getTestUser(int level) {
        String uuid = UUID.randomUUID().toString();
        User user = new User(uuid, "test", level, "Coimbra");
        usersApi.create(user);
        return user;
    }
    
}
