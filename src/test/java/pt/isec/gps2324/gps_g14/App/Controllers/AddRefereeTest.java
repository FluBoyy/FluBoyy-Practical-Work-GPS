package pt.isec.gps2324.gps_g14.App.Controllers;

import org.junit.jupiter.api.Test;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.API.Users;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddRefereeTest {
    @Test
    void addReferee() {
        Users users = new Users();
        User referee = new User("testAdd", "test", 1, "Coimbra");

        users.create(referee);

        assertEquals(referee.getId(), users.get(referee.getId()).getId());
    }
}
