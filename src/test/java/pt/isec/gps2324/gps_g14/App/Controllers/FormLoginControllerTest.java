package pt.isec.gps2324.gps_g14.App.Controllers;

import org.junit.jupiter.api.Test;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.API.Users;

import static org.junit.jupiter.api.Assertions.*;

class FormLoginControllerTest {
    @Test
    void login() {
        Users users = new Users();
        User user = TestUtils.getTestUser();
        User successfulLogin = FormLoginController.login(users, user.getUsername(), "test");
        assertNotNull(successfulLogin, "Login should be successful");
        assertEquals(user.getId(), successfulLogin.getId(), "User should be the same");
        User unsuccessfulLogin = FormLoginController.login(users, user.getUsername(), "wrong");
        assertNull(unsuccessfulLogin, "Login should be unsuccessful");
    }


}