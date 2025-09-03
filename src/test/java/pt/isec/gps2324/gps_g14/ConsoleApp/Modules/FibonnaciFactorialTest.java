package pt.isec.gps2324.gps_g14.ConsoleApp.Modules;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FibonnaciFactorialTest {
    Fibonacci_Factorial fibonacci_factorial = new Fibonacci_Factorial();

    @Test
    void fibonacci() {
        List<Integer> expected = Arrays.asList(1, 1, 2, 3, 5);
        assertEquals(expected, fibonacci_factorial.fibonacciOperation(5));
    }

    @Test
    void factorial(){
        assertEquals(3628800, fibonacci_factorial.factorialOperation(10));
    }

}