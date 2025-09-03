package pt.isec.gps2324.gps_g14.ConsoleApp.Modules;
import pt.isec.gps2324.gps_g14.ConsoleApp.ConsoleAppModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class OperationsDates extends ConsoleAppModule {
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public OperationsDates() {
        super("operationdate");
    }

    private Date firstDate = null;

    private void reset() {
        firstDate = null;
    }

    public static Date AddDaysToDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static long DiffDates(Date date1, Date date2) {
        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

    public void handleOperation(String input) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (firstDate == null){
            try {
                Date data = sdf.parse(input);
                firstDate = data;
            } catch (ParseException e) {
                System.err.println("Erro ao processar a data inserida.");
                return;
            }
        } else {
            //parse input for date or days and do acording operation
            try {
                int dias = Integer.parseInt(input);
                Date resDate = AddDaysToDate(firstDate, dias);
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("Data resultante: " + outputFormat.format(resDate));
                this.reset();
            } catch (NumberFormatException e) {
                try {
                    Date data = sdf.parse(input);
                    long diffDays = DiffDates(firstDate, data);
                    System.out.println(diffDays);
                    this.reset();
                } catch (ParseException err) {
                    System.err.println("Erro ao processar a data inserida.");
                    return;
                }
            }
        }
    }

    public boolean menu(){
        try {
            String input = reader.readLine();
            switch (input.trim().toLowerCase()) {
                case "exit" -> {
                    return false;
                }
                case "help" -> this.help();
                default -> {
                    this.handleOperation(input.trim());
                    this._help();
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
        if (firstDate == null) {
            System.out.println("Insira uma data no formato dd/MM/yyyy:");
        } else {
            System.out.println("Insira outra data ou dias a adicionar á anterior:");
        }
    }
}
