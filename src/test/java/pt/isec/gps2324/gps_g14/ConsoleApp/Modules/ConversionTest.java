package pt.isec.gps2324.gps_g14.ConsoleApp.Modules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversionTest {
    Conversion conversion = new Conversion();

    @Test
    void B_H() throws Exception {
        assertEquals("3", conversion.B_TO_H("0011"));
        assertEquals("1E2", conversion.B_TO_H("111100010"));
        assertEquals("33", conversion.B_TO_H("00110011"));
    }

    @Test
    void H_B() throws Exception {
        assertEquals("1111001100", conversion.H_TO_B("3CC"));
        assertEquals("11111111", conversion.H_TO_B("FF"));
        assertEquals("1000101", conversion.H_TO_B("45"));
    }



}