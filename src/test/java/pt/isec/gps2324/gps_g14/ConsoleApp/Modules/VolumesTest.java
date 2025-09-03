package pt.isec.gps2324.gps_g14.ConsoleApp.Modules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class VolumesTest {
    Volumes vol = new Volumes();

    @Test
    void calcularVolumeCilindroInt() {
        assertEquals(785.3982,vol.calcularVolumeCilindro(5, 10),0.0001); // Aproximadamente 785.39816
        assertEquals(874.97,vol.calcularVolumeCilindro(5.2F, 10.3F),0.01);
        assertEquals(65.38,vol.calcularVolumeCilindro(2.2F, 4.3F),0.01);
    }

    @Test
    void calcularVolumeConeInt() {
        assertEquals(56.54867,vol.calcularVolumeCone(3, 6),0.00001); // Aproximadamente 56.54867
        assertEquals(291.66,vol.calcularVolumeCone(5.2F, 10.3F),0.01);
        assertEquals(21.79,vol.calcularVolumeCone(2.2F, 4.3F),0.01);
    }

    @Test
    void calcularVolumeCilindroFloat(){
        assertEquals(14.137167,vol.calcularVolumeCilindro(1.5F,2), 0.000001);
    }

    @Test
    void calcularVolumeConeFloat(){
        assertEquals(302.55948, vol.calcularVolumeCone(5.9F, 8.3F), 0.00001);
    }
}
