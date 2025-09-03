package pt.isec.gps2324.gps_g14.App;

import java.util.Date;

import pt.isec.gps2324.gps_g14.App.API.*;
import pt.isec.gps2324.gps_g14.App.API.Entities.Payment;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.Price;
import pt.isec.gps2324.gps_g14.App.API.Entities.Timeslot;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.Utils.DateUtils;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class PopulateDB {
    private static final Games gamesAPI = new Games();
    private static final Timeslots timeslotsAPI = new Timeslots();
    private static final Users usersAPI = new Users();
    private static final Prices pricesAPI = new Prices();
    private static final Payments paymentsAPI = new Payments();

    public static void main(String[] args) {
        //addUsers();
        //addGames();
        //addPrices();
        addPayments();
    }

    private static void addUsers() {
        if (!usersAPI.exists("admin"))
            usersAPI.create(User.Admin("admin", "admin"));

        if (!usersAPI.exists("andre"))
            usersAPI.create(new User("andre", "andre", 3, "Coimbra"));
        if(!usersAPI.exists("joao"))
            usersAPI.create(new User("joao", "joao", 3, "Coimbra"));
        if(!usersAPI.exists("leonardo"))
            usersAPI.create(new User("leonardo", "leonardo", 2, "Coimbra"));

        //timeslots para o andre
        Date startS1 = DateUtils.getDate(2023, 12, 23, 0, 0, 0);
        Date endS1 = DateUtils.getDate(2023, 12, 23, 0, 0, 0);
        timeslotsAPI.create(new Timeslot(usersAPI.get("andre"), startS1, endS1));

        Date startM1 = DateUtils.getDate(2023, 12, 24, 0, 0, 0);
        Date endM1 = DateUtils.getDate(2023, 12, 24, 0, 0, 0);
        timeslotsAPI.create(new Timeslot(usersAPI.get("andre"), startM1, endM1));

        //timeslots para o joao
        Date startS2 = DateUtils.getDate(2023, 12, 23, 14, 30, 0);
        Date endS2 = DateUtils.getDate(2023, 12, 23, 0, 0, 0);
        timeslotsAPI.create(new Timeslot(usersAPI.get("joao"), startS2,endS2 ));

        Date startM2 = DateUtils.getDate(2023, 12, 24, 0, 0, 0);
        Date endM2 = DateUtils.getDate(2023, 12, 24, 0, 0, 0);
        timeslotsAPI.create(new Timeslot(usersAPI.get("joao"), startM2, endM2));

        //timeslots para o leonardo
        Date startS3 = DateUtils.getDate(2023, 12, 23, 0, 0, 0);
        Date endS3 = DateUtils.getDate(2023, 12, 23, 0, 0, 0);
        timeslotsAPI.create(new Timeslot(usersAPI.get("leonardo"), startS3, endS3));

        Date startM3 = DateUtils.getDate(2023, 12, 24, 9, 0, 0);
        Date endM3 = DateUtils.getDate(2023, 12, 24, 15, 0, 0);
        timeslotsAPI.create(new Timeslot(usersAPI.get("leonardo"), startM3, endM3));
    }

    private static void addGames() {
        // Voleibol Games passados
        gamesAPI.create(new Game(Sports.VOLEIBOL, 1, "AA Coimbra", "Ginasio FC", "Coimbra",
                DateUtils.getDate(2023, 12, 16, 21, 45, 0), "Taça de Portugal Femininos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 1, "A Ruinas VC", "Figueira VC 'A' ", "Figueira da Foz",
                DateUtils.getDate(2023, 12, 16, 11, 30, 0), "Taça de Portugal Masculinos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 3, "GD Sourense", "Figueira VC 'B' ", "Braga",
                DateUtils.getDate(2023, 12, 16, 15, 0, 0), "Campeonato Nacional Sen. Masculinos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 2, "AA Coimbra 'B'", "SC Caldas", "Guarda",
                DateUtils.getDate(2023, 12, 16, 17, 0, 0), "Campeonato Nacional"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 3, "AV Gandara Mar", "SO Marinhense", "Viseu",
                DateUtils.getDate(2023, 12, 16, 17, 0, 0), "Campeonato reginal Inf. Masculinos"));

        gamesAPI.create(new Game(Sports.VOLEIBOL, 3, "RC Santarem", "SC Caldas", "Braganca",
                DateUtils.getDate(2023, 12, 17, 15, 30, 0), "Campeonato reginal Inf. Masculinos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 2, "Lousa VC", "CD Pataiense", "Aveiro",
                DateUtils.getDate(2023, 12, 17, 11, 0, 0), "Campeonato reginal Inf. Femeninos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 1, "AV Gandara Mar", "AA Coimbra", "Vila Real",
                DateUtils.getDate(2023, 12, 17, 16, 0, 0), "Campeonato reginal Inf. Femeninos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 2, "AA Coimbra", "CD Pataiense", "Porto",
                DateUtils.getDate(2023, 12, 17, 11, 0, 0), "Campeonato reginal Sen. Femeninos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 1, "Sporting", "Benfica", "Lisboa",
                DateUtils.getDate(2023, 12, 17, 17, 30, 0), "Campeonato reginal Sen. Masculinos"));

        // Voleibol Games futuros
        gamesAPI.create(new Game(Sports.VOLEIBOL, 1, "AA Coimbra", "Figueira VC", "Coimbra",
                DateUtils.getDate(2023, 12, 23, 21, 45, 0), "Campeonato Nacional Sen. Masculinos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 3, "AV Gandara Mar", "Figueira VC", "Guarda",
                DateUtils.getDate(2023, 12, 23, 18, 30, 0), "Campeonato Regional Jun.Masculinos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 3, "Sporting", "Porto", "Lisboa",
                DateUtils.getDate(2023, 12, 23, 17, 0, 0), "Campeonato Nacional Sen. Masculinos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 2, "RC Santarem", "Lousa VC", "Leiria",
                DateUtils.getDate(2023, 12, 23, 11, 30, 0), "Campeonato Nacional Inf.Femeninos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 2, "Figueira VC 'B'", "GD Sourense", "Aveiro",
                DateUtils.getDate(2023, 12, 23, 12, 0, 0), "Campeonato Nacional Ini.Femeninos"));

        gamesAPI.create(new Game(Sports.VOLEIBOL, 3, "A Ruinas VC", "GD Sourense", "Aveiro",
                DateUtils.getDate(2023, 12, 24, 15, 30, 0), "Campeonato Nacional Ini.Femeninos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 2, "AA Coimbra", "GD Sourense", "Coimbra",
                DateUtils.getDate(2023, 12, 24, 11, 0, 0), "Campeonato Nacional Inf.Masculinos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 3, "Porto", "Sporting", "Porto",
                DateUtils.getDate(2023, 12, 24, 16, 45, 0), "Campeonato Regional Jun.Masculinos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 2, "Lousa VC", "CD Pataiense", "Guarda",
                DateUtils.getDate(2023, 12, 24, 16, 0, 0), "Campeonato Nacional Jun.Femeninos"));
        gamesAPI.create(new Game(Sports.VOLEIBOL, 2, "Sporting", "AA Coimbra", "Lisboa",
                DateUtils.getDate(2023, 12, 24, 18, 30, 0), "Campeonato Nacional Sen.Femeninos"));



        /*// Basquet Games
        gamesAPI.create(new Game(Sports.BASQUET, 1, "Porto", "Benfica", "Porto",
                DateUtils.getDate(2023, 10, 30, 18, 0, 0), "Campeonato reginal Inf. Femeninos"));
        gamesAPI.create(new Game(Sports.BASQUET, 2, "Sporting", "Braga", "Lisboa",
                DateUtils.getDate(2023, 10, 29, 18, 0, 0), "Campeonato reginal Inf. Femeninos"));
        gamesAPI.create(new Game(Sports.BASQUET, 2, "Academica", "Guimarães", "Coimbra",
                DateUtils.getDate(2023, 11, 12, 17, 30, 0), "Campeonato reginal Inf. Masculinos"));
        gamesAPI.create(new Game(Sports.BASQUET, 1, "Leixoes", "Estoril", "Leiria",
                DateUtils.getDate(2023, 11, 12, 11, 45, 0), "Campeonato reginal Inf. Masculinos"));

        // Futebol Games

        gamesAPI.create(new Game(Sports.FUTEBOL, 1, "Porto", "Braga", "Porto",
                DateUtils.getDate(2023, 10, 28, 20, 0, 0), "Taça de Portugal"));
        gamesAPI.create(new Game(Sports.FUTEBOL, 2, "Guimarães", "Nacional", "Guimarães",
                DateUtils.getDate(2023, 10, 27, 15, 30, 0), "Taça da Liga"));
        gamesAPI.create(new Game(Sports.FUTEBOL, 2, "Benfica", "Sporting", "Lisboa",
                DateUtils.getDate(2023, 11, 12, 20, 30, 0), "Champions League"));
        gamesAPI.create(new Game(Sports.FUTEBOL, 3, "Paços de Ferreira", "Academica", "Guarda",
                DateUtils.getDate(2023, 11, 12, 15, 0, 0), "Campeonato reginal"));
*/
    }

    private static void addPrices() {

        pricesAPI.create(new Price("Coimbra", "Coimbra", 1, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Lisboa", 26.3, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Porto", 23.5, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Guimaraes", 11.5, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Braga", 9.4, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Leiria", 3.4, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Figueira da Foz", 3.5, 5.8, 15));
        pricesAPI.create(new Price("Coimbra", "Aveiro", 4.6, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Viseu", 6.8, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Guarda", 7.2, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Castelo Branco", 7.2, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Vila Real", 16.4, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Braganca", 13.4, 3.5, 15));
        pricesAPI.create(new Price("Coimbra", "Viana do Castelo", 10, 3.5, 15));
    }

    private static void addPayments(){

        /*paymentsAPI.create(new Payment(usersAPI.get("Manuel"), gamesAPI.getGameFromID(1).getId(), gamesAPI.getGameFromID(1).getGameDate(), gamesAPI.getGameFromID(1).getHomeTeam(), gamesAPI.getGameFromID(1).getVisitorTeam(), gamesAPI.getGameFromID(1).getDivision(), gamesAPI.getGameFromID(1).getLocation(), pricesAPI.getGamePrize(), pricesAPI.getPriceMeal(), pricesAPI.getPriceDesloc(usersAPI.get("Manuel").getLocation(), gamesAPI.getGameFromID(1).getLocation()), pricesAPI.getGamePrize() + pricesAPI.getPriceMeal() + pricesAPI.getPriceDesloc(usersAPI.get("Manuel").getLocation(), gamesAPI.getGameFromID(1).getLocation())));
        paymentsAPI.create(new Payment(usersAPI.get("Joaquim"), gamesAPI.getGameFromID(1).getId(), gamesAPI.getGameFromID(1).getGameDate(), gamesAPI.getGameFromID(1).getHomeTeam(), gamesAPI.getGameFromID(1).getVisitorTeam(), gamesAPI.getGameFromID(1).getDivision(), gamesAPI.getGameFromID(1).getLocation(),pricesAPI.getGamePrize(), pricesAPI.getPriceMeal(), pricesAPI.getPriceDesloc(usersAPI.get("Joaquim").getLocation(), gamesAPI.getGameFromID(1).getLocation()), pricesAPI.getGamePrize() + pricesAPI.getPriceMeal() + pricesAPI.getPriceDesloc(usersAPI.get("Joaquim").getLocation(), gamesAPI.getGameFromID(1).getLocation())));
        paymentsAPI.create(new Payment(usersAPI.get("Joaquim"), gamesAPI.getGameFromID(3).getId(), gamesAPI.getGameFromID(3).getGameDate(), gamesAPI.getGameFromID(3).getHomeTeam(), gamesAPI.getGameFromID(3).getVisitorTeam(), gamesAPI.getGameFromID(3).getDivision(), gamesAPI.getGameFromID(3).getLocation(),pricesAPI.getGamePrize(), pricesAPI.getPriceMeal(), pricesAPI.getPriceDesloc(usersAPI.get("Joaquim").getLocation(), gamesAPI.getGameFromID(3).getLocation()), pricesAPI.getGamePrize() + pricesAPI.getPriceMeal() + pricesAPI.getPriceDesloc(usersAPI.get("Joaquim").getLocation(), gamesAPI.getGameFromID(3).getLocation())));
        paymentsAPI.create(new Payment(usersAPI.get("Joaquim"), gamesAPI.getGameFromID(6).getId(), gamesAPI.getGameFromID(6).getGameDate(), gamesAPI.getGameFromID(6).getHomeTeam(), gamesAPI.getGameFromID(6).getVisitorTeam(), gamesAPI.getGameFromID(6).getDivision(), gamesAPI.getGameFromID(6).getLocation(),pricesAPI.getGamePrize(), pricesAPI.getPriceMeal(), pricesAPI.getPriceDesloc(usersAPI.get("Joaquim").getLocation(), gamesAPI.getGameFromID(6).getLocation()), pricesAPI.getGamePrize() + pricesAPI.getPriceMeal() + pricesAPI.getPriceDesloc(usersAPI.get("Joaquim").getLocation(), gamesAPI.getGameFromID(6).getLocation())));
        */

        for (User user : usersAPI.getReferees())
            for (Game game : gamesAPI.getGameFromUser(user))
                paymentsAPI.create(new Payment(user, game.getId(), game.getGameDate(), game.getHomeTeam(), game.getVisitorTeam(), game.getDivision(), game.getLocation(), pricesAPI.getGamePrize(), pricesAPI.getPriceMeal(), pricesAPI.getPriceDesloc(user.getLocation(), game.getLocation()), pricesAPI.getGamePrize() + pricesAPI.getPriceMeal() + pricesAPI.getPriceDesloc(user.getLocation(), game.getLocation())));

    }

}
