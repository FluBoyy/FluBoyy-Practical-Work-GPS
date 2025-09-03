package pt.isec.gps2324.gps_g14.App.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String getDuration(Date d1, Date d2) {
        ArrayList<String> list = new ArrayList<>();
        long difference_In_Time = d2.getTime() - d1.getTime();
        long difference_In_Seconds = (difference_In_Time / 1000) % 60;
        long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
        long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60));

        if (difference_In_Hours > 0)
            list.add(difference_In_Hours + " hours");
        if (difference_In_Minutes > 0)
            list.add(difference_In_Minutes + " minutes");
        if (difference_In_Seconds > 0)
            list.add(difference_In_Seconds + " seconds");
        
        return String.join(", ", list);
    }

    public static String getDuration(int minutes) {
        Calendar cal = Calendar.getInstance();
        Date d1 = cal.getTime();
        cal.add(Calendar.MINUTE, minutes);
        Date d2 = cal.getTime();
        return getDuration(d1, d2);
    }

    public static Date getDate(int year, int month, int day, int hour, int minute, int second){

        Calendar cal = Calendar.getInstance();

        cal.set(year, month, day, hour, minute, second);

        return cal.getTime();
    }

    public static void main(String[] args) {
        Date d1 = new Date(1698795060000L);
        Date d2 = new Date(1698795230000L);
        System.out.println(getDuration(d1, d2));
    }

    public static Date toDate(LocalDate localDate) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate toLocalDate(Calendar cal) {
        return LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId()).toLocalDate();
    }
}
