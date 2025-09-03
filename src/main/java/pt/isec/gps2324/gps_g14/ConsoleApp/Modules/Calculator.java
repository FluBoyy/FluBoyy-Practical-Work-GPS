package pt.isec.gps2324.gps_g14.ConsoleApp.Modules;

import pt.isec.gps2324.gps_g14.ConsoleApp.ConsoleAppModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator extends ConsoleAppModule {
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public Calculator(){
        super("Calculator");
    }

    public float handleOperation(String input) throws Exception {
        float result;
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(input);

        List<Float> numbers = new ArrayList<>();
        while (matcher.find()) {
            float number = Float.parseFloat(matcher.group());
            numbers.add(number);
        }
        String operator = input.replaceAll("\\d+(\\.\\d+)?", "").trim();

        if (numbers.size() != 2)
            throw new Exception("Invalid operation");

        switch (operator) {
            case "+" -> result = numbers.get(0) + numbers.get(1);
            case "*" -> result = numbers.get(0) * numbers.get(1);
            case "-" -> result = numbers.get(0) - numbers.get(1);
            case "/" -> {
                if (numbers.get(1) != 0) {
                    result = (float) numbers.get(0) / numbers.get(1);
                } else {
                    throw new Exception("Cant divide by 0");
                }
            }
            case "^" -> {
                result = numbers.get(0);
                for (int i = 1; i < numbers.get(1); i++)
                    result *= numbers.get(0);
            }
            default -> throw new Exception("Invalid operator: " + operator);
        }

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
                case "help" -> this.help();
                default -> System.out.println("Resultado: " + this.handleOperation(input.trim()));
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
        System.out.println("Available operators: +, -, *, /, ^");
        System.out.println("Enter a operation (1+1):");
    }
}
