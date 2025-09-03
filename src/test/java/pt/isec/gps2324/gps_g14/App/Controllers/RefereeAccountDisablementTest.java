package pt.isec.gps2324.gps_g14.App.Controllers;

import org.junit.jupiter.api.Test;
import pt.isec.gps2324.gps_g14.App.API.Users;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;

import static org.junit.jupiter.api.Assertions.*;

public class RefereeAccountDisablementTest {

    @Test
    public void testGetReferees() {
        User user = new User("João","joao1234",3,"Coimbra");
        Users users = new Users();
        users.create(user);
        user.setDisabled(true);
        users.update(user);
        assertTrue(users.get(user.getId()).isDisabled());
    }
}