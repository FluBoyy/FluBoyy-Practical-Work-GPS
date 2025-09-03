package pt.isec.gps2324.gps_g14.App.Controllers;

import org.junit.jupiter.api.Test;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.API.Users;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteRefereeTest {

    @Test
    void DeleteReferee(){
        User referee = new User("testeDelete", "testeDelete", 1, "Coimbra");
        Users usersAPI = new Users();
        usersAPI.create(referee);
        usersAPI.delete(referee);
        assertFalse(usersAPI.exists(referee.getUsername()));
    }
}
