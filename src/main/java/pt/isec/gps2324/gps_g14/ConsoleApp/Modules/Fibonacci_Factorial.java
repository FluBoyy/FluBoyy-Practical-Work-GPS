package pt.isec.gps2324.gps_g14.ConsoleApp.Modules;

import pt.isec.gps2324.gps_g14.ConsoleApp.ConsoleAppModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci_Factorial extends ConsoleAppModule{

    public Fibonacci_Factorial(){
        super("Fibonnaci_Factorial");
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Scanner scanner = new Scanner(System.in);

    public List<Integer> fibonacciOperation(int iterations) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(1);

        for (int i = 2; i < iterations; i++) {
            int fibNumber = numbers.get(i - 2) + numbers.get(i - 1);
            numbers.add(fibNumber);
        }

        return numbers;
    }

    public int factorialOperation(int number){
        List<Integer> numbers = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        int result = 1;

        for(int i=number; i > 0; i--){
            numbers.add(i);
            if(i > 1)
                str.append(i + " x ");
            else
                str.append(i);
        }

        for(int i=number; i > 0; i--){
            result *= i;
        }

        System.out.println(str);

        return result;
    }


    /**
     * Renders the user UI
     * @return boolean - if false the program should end
     */
    public boolean menu(){
        try {
            String input = reader.readLine();
            switch (input.trim().toLowerCase()) {
                case "exit" -> {
                    return false;
                }
                case "fibonacci" -> {
                    System.out.println("Iterations: ");
                    String iterations = reader.readLine();
                    System.out.println("Resultado: " + fibonacciOperation(Integer.parseInt(iterations)) + "\n");
                    help();
                }
                case "factorial" -> {
                    System.out.println("Number: ");
                    String number = reader.readLine();
                    System.out.println("Resultado: " + factorialOperation(Integer.parseInt(number)) + "\n");
                    help();
                }
                case "help" -> this.help();
                //default -> System.out.println("Resultado: " + this.handleOperation(input.trim()));
            }
        } catch (IOException err) {
            System.err.println("Error in detecting operation");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return true;
    }

    public void _help() {
        System.out.println("Commands: help, exit");
        System.out.println("What operation do you want to use (fibonnaci / factorial): ");
    }
}