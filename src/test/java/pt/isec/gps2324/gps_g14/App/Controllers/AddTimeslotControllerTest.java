package pt.isec.gps2324.gps_g14.App.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import pt.isec.gps2324.gps_g14.App.API.Timeslots;
import pt.isec.gps2324.gps_g14.App.API.Entities.Timeslot;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.API.Models.UserData;
import pt.isec.gps2324.gps_g14.App.Utils.DateUtils;

class AddTimeslotControllerTest {

    @Test
    void addTimeSlot() {
        Timeslots timeslots = new Timeslots();
        Calendar start = Calendar.getInstance();
        Calendar finish = Calendar.getInstance();
        LocalDate startDatePicker = LocalDate.now();
        LocalDate finishDatePicker = LocalDate.now();

        UserData userData = UserData.getInstance();
        User user = TestUtils.getTestUser();
        userData.setCurrentUser(user);

        Date startDate = DateUtils.toDate(startDatePicker);
        start.setTime(startDate);
        Date finishDate = DateUtils.toDate(finishDatePicker);
        finish.setTime(finishDate);
        start.set(Calendar.HOUR_OF_DAY, 18);
        start.set(Calendar.MINUTE, 50);
        finish.set(Calendar.HOUR_OF_DAY, 20);
        finish.set(Calendar.MINUTE, 30);

        Timeslot timeslot = new Timeslot(user, start.getTime(), finish.getTime());

        timeslots.create(timeslot);

        assertEquals(timeslot.getId(), timeslots.get(timeslot.getId()).getId());


    }
}