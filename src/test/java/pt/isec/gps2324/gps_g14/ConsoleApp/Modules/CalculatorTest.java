package pt.isec.gps2324.gps_g14.ConsoleApp.Modules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    void add() throws Exception {
        assertEquals(2.0, calc.handleOperation("1+1"));
        assertEquals(2.3, calc.handleOperation("1.1+1.2"), 0.1);
        assertEquals(2.1, calc.handleOperation("1.1+1"), 0.1);
        assertEquals(2.1, calc.handleOperation("1+1.1"), 0.1);
    }

    @Test
    void subtract() throws Exception {
        assertEquals(0.0,calc.handleOperation("1-1"));
        assertEquals(0.9,calc.handleOperation("2.1-1.2"),0.1);
        assertEquals(1.1,calc.handleOperation("2.1-1"),0.1);
        assertEquals(0.9,calc.handleOperation("2-1.1"),0.1);
    }

    @Test
    void division() throws Exception {
        assertEquals(2.5,calc.handleOperation("5/2"));
        assertEquals(2.31, calc.handleOperation("5.1/2.2"), 0.1);
        assertEquals(1, calc.handleOperation("3.4/3.4"), 0.1);
        assertEquals(1.73, calc.handleOperation("9.0/5.2"), 0.01);
        assertEquals(3.33,calc.handleOperation("10/3"),0.01);
    }

    @Test
    void multiplication() throws Exception {
        assertEquals(1.0,calc.handleOperation("1*1"));
        assertEquals(11.22, calc.handleOperation("5.1*2.2"), 0.1);
        assertEquals(11.56, calc.handleOperation("3.4*3.4"), 0.1);
        assertEquals(46.8, calc.handleOperation("9.0*5.2"), 0.1);
    }

}