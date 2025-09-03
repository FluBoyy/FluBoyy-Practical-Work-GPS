package pt.isec.gps2324.gps_g14.ConsoleApp;

import pt.isec.gps2324.gps_g14.ConsoleApp.Modules.Calculator;
import pt.isec.gps2324.gps_g14.ConsoleApp.Modules.Fibonacci_Factorial;
import pt.isec.gps2324.gps_g14.ConsoleApp.Modules.Volumes;
import pt.isec.gps2324.gps_g14.ConsoleApp.Modules.OperationsDates;
import pt.isec.gps2324.gps_g14.ConsoleApp.Modules.Conversion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ConsoleApp extends ConsoleAppModule{
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    static final HashMap<String, ConsoleAppModule> modules = new HashMap<>();
    public ConsoleApp(){
        super("Main");
        modules.put("calculator", new Calculator());
        modules.put("fibonacci_factorial", new Fibonacci_Factorial());
        modules.put("volumes", new Volumes());
        modules.put("operation_date", new OperationsDates());
        modules.put("conversion", new Conversion());
    }

    public void _help() {
        System.out.println("Commands: help, exit");
        System.out.println("Modules: " + String.join(", ", modules.keySet()));
    }

    public boolean menu() {
        try {
            String input = reader.readLine();
            String trimmed = input.trim().toLowerCase();
            switch (trimmed) {
                case "exit" -> {
                    return false;
                }
                case "help" -> this.help();
                default -> {
                    ConsoleAppModule module = modules.get(trimmed);
                    if (module != null) {
                        this.startModule(module);
                        break;
                    }
                    throw new Exception("Invalid command");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return true;
    }

    public void startModule(ConsoleAppModule module) {
        module.help();
        while (true){
            if (!module.menu()) break;
        }
        this.help();
    }


    public static void main(String[] args) {
        ConsoleApp consoleApp = new ConsoleApp();
        consoleApp.help();
        while (true){
            if (!consoleApp.menu()) break;
        }
    }
}
