package pt.isec.gps2324.gps_g14.ConsoleApp.Modules;import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class OperationsDatesTest {
    OperationsDates date = new OperationsDates();

    @Test
    void date() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(sdf.parse("22/05/2021"), OperationsDates.AddDaysToDate(sdf.parse("20/05/2021"),2));
        assertEquals(2,OperationsDates.DiffDates(sdf.parse("20/05/2021"),sdf.parse("22/05/2021")));
    }
}
