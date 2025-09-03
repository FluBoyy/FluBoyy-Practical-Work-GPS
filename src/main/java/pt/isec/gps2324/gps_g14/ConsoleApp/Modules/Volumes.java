package pt.isec.gps2324.gps_g14.ConsoleApp.Modules;

import pt.isec.gps2324.gps_g14.ConsoleApp.ConsoleAppModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Volumes extends ConsoleAppModule {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Volumes(){
        super("Volumes");
    }

    public float handleVolume(String input) throws Exception {
        float result = 0;
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(input);

        float radius = 0;
        float height = 0;
        int count = 0;
        while (matcher.find()) {
            float number = Float.parseFloat(matcher.group());
            if (count == 0) {
                radius = number;
            } else if (count == 1) {
                height = number;
            } else {
                throw new Exception("Too many numbers provided.");
            }
            count++;
        }

        String shape = input.replaceAll("\\d+\\.?\\d*", "").trim().toLowerCase();

        switch (shape) {
            case "cilindro" -> result = calcularVolumeCilindro(radius, height);
            case "cone" -> result = calcularVolumeCone(radius, height);
            default -> throw new Exception("Shape not recognized: " + shape);
        }

        return result;
    }

    public float calcularVolumeCilindro(float raio, float altura) {
        if (raio < 0 || altura < 0) {
            throw new IllegalArgumentException("Raio e altura devem ser valores não negativos.");
        }
        float volume = (float) (Math.PI * Math.pow(raio, 2) * altura);
        return volume;
    }

    public float calcularVolumeCone(float raio, float altura) {
        if (raio < 0 || altura < 0) {
            throw new IllegalArgumentException("Raio e altura devem ser valores não negativos.");
        }
        float volume = (float) ((1.0 / 3.0) * Math.PI * Math.pow(raio, 2) * altura);
        return volume;
    }

    public void _help() {
        System.out.println("Commands: help, exit");
        System.out.println("Available shapes: cilindro, cone");
        System.out.println("Enter a shape (cilindro or cone) and then its dimensions");
    }

    public boolean menu() {
        try {
            System.out.println("Escolha uma forma para calcular o volume (cilindro ou cone) ou 'exit' para sair:");
            String input = reader.readLine().trim().toLowerCase();

            switch (input) {
                case "exit":
                    return false;
                case "cilindro":
                case "cone":
                    System.out.println("Digite as dimensões (raio e altura) separadas por espaço:");
                    String dimensions = reader.readLine().trim();
                    float volume = handleVolume(input + " " + dimensions);
                    System.out.println("Resultado: " + volume);
                    break;
                default:
                    System.err.println("Comando não reconhecido. Use 'cilindro', 'cone' ou 'exit'.");
            }
        } catch (IOException err) {
            System.err.println("Erro ao ler a entrada.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return true;
    }
}