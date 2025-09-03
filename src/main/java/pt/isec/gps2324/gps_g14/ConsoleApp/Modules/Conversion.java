package pt.isec.gps2324.gps_g14.ConsoleApp.Modules;

import pt.isec.gps2324.gps_g14.ConsoleApp.ConsoleAppModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Conversion extends ConsoleAppModule {

    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public Conversion() {super("Conversion");}

    @Override
    public boolean menu() {
        try {
            String input = reader.readLine();
            switch (input.trim().toLowerCase()) {
                case "exit" -> {
                    return false;
                }
                case "help" -> this.help();
                case "b to h" ->{

                    System.out.print("Number ->");
                    String value = reader.readLine();

                    System.out.println("Binary -> " + value + "\nHexadecimal -> " + this.B_TO_H(value));

                }
                case "h to b" ->{

                    System.out.print("Number ->");
                    String value = reader.readLine();

                    this.H_TO_B(value);

                    System.out.println("Hexadecimal -> " + value + "\nBinary -> " + this.H_TO_B(value));
                }
            }
        } catch (IOException err) {
            System.err.println("Error in detecting operation");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return true;
    }

    @Override
    public void _help() {
        System.out.println("Commands: help, exit");
        System.out.println("Available functions:  binary to hexa(b to h), hexa to binary(h to b)");
    }

    public String H_TO_B(String value) {

        int hexValue = Integer.parseInt(value, 16);

        return Integer.toBinaryString(hexValue);
    }

    public String B_TO_H (String value){

        int binaryValue = Integer.parseInt(value, 2);

       return Integer.toHexString(binaryValue).toUpperCase();
    }
}
