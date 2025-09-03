package pt.isec.gps2324.gps_g14.App.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.isec.gps2324.gps_g14.App.API.ApiUtils;
import pt.isec.gps2324.gps_g14.App.API.Games;
import pt.isec.gps2324.gps_g14.App.API.Payments;
import pt.isec.gps2324.gps_g14.App.API.Prices;
import pt.isec.gps2324.gps_g14.App.API.Timeslots;
import pt.isec.gps2324.gps_g14.App.API.Users;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.Payment;
import pt.isec.gps2324.gps_g14.App.API.Entities.Timeslot;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.API.Models.UserData;
import pt.isec.gps2324.gps_g14.App.Utils.DateUtils;

public class PanelAdminController {

    private Users userAPI;
    private Games gamesAPI;
    private UserData userData;
    private Payments paymentsAPI;
    private Prices pricesAPI;

    private boolean showFutureGames = false;
    private Game previousSelectedGame;

    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;

    @FXML
    public Button btnPayments;

    @FXML
    private Button logoutButton;

    @FXML
    private Button btnAssign;
    @FXML
    private Button btnDeassign;

    @FXML
    private TableView<User> refereesTable;
    @FXML
    private TableColumn<User, String> refereeNome;
    @FXML
    private TableColumn<User, Integer> refereeNivel;
    @FXML
    private TableColumn<User ,String> refereeLoc;

    @FXML
    private TableView<Game> matchesTable;

    @FXML
    private TableColumn<Game, Integer> matchID;
    @FXML
    private TableColumn<Game, String> matchHomeTeam;
    @FXML
    private TableColumn<Game, String> matchVisitorTeam;
    @FXML
    private TableColumn<Game, String> mathcLocation;
    @FXML
    private TableColumn<Game, String> matchDesc;
    @FXML
    private TableColumn<Game, Date> matchInit;
    @FXML
    private TableColumn<Game ,String> matchDuration;
    @FXML
    private TableColumn<Game, String> matchReferee;
    @FXML
    private Label nomeAdmin;
    @FXML
    private Button btnMatchAdd;
    @FXML
    private Button btnMatchRemove;
    @FXML
    private Button btnMatchModify;

    @FXML
    private Button removeReferee;
    @FXML
    private Button finishMatchs;

    @FXML
    private Button addReferee;

    @FXML
    private Button disableReferee;

    @FXML
    private Button rate;


    @FXML
    private void initialize() {
        rate.setVisible(false);

        btnMatchAdd.setOnAction(event -> handleAddMatchButton());
        btnMatchRemove.setOnAction(event -> handleRemoveMatchButton());
        removeReferee.setOnAction(event->handleRemoveRefereeButton());
        finishMatchs.setOnAction(event -> handleFinishMatchsButton());
        userData = UserData.getInstance();
        btnAssign.setOnAction(e -> handleAssignButton());
        btnDeassign.setOnAction(e -> handleDeassignButton());
        logoutButton.setOnAction(event -> handleLogoutButtonAction());
        disableReferee.setOnAction(event -> handleDisableRefereeButton());

        btnPayments.setOnAction(event -> handlePaymentsButton());

        matchesTable.setOnMouseClicked(event -> updateReferees());
        refereesTable.setOnMouseClicked(event -> selectedRefereeUpdate());

        btnMatchModify.setOnAction(event -> handleMatchModifyButton());

        addReferee.setOnAction(event -> addReferee());

        rate.setOnAction(event -> rateReferee());

        userAPI = new Users();
        gamesAPI = new Games();
        paymentsAPI = new Payments();
        pricesAPI = new Prices();


        // Configure the columns to display the appropriate data
        refereeNome.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUsername()));
        refereeNivel.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLevel()));
        refereeLoc.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLocation()));


        matchID.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        matchHomeTeam.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHomeTeam()));
        matchVisitorTeam.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getVisitorTeam()));
        mathcLocation.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLocation()));
        matchDesc.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGameDesc()));
        matchInit.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGameDate()));
        matchDuration.setCellValueFactory(cellData -> new SimpleObjectProperty<>(DateUtils.getDuration(cellData.getValue().getDuration())));
        matchReferee.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getReferree() != null ? cellData.getValue().getReferree().getUsername() : "N/A"));

        refereeNome.setCellFactory(column -> {
            return new TableCell<User, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem());
                    setGraphic(null);

                    TableRow<User> currentRow = getTableRow();

                    if (!isEmpty()) {
                        if (currentRow.getItem() != null) {
                            if (currentRow.getItem().isDisabled()) {
                                setTextFill(Color.RED);
                            } else {
                                setTextFill(Color.BLACK);
                            }
                        }
                    }
                }
            };
        });

        // load data
        updateReferees();
        updateGames();
        nomeAdmin.setText("Olá " + userData.getCurrentUser().getUsername());
    }

    private void rateReferee() {
        Game selectedGame = matchesTable.getSelectionModel().getSelectedItem();
        if(selectedGame == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Select a game to rate a referee");
            alert.setHeaderText("Select a game to rate a referee");
            alert.show();
            return;
        }

        User referee = selectedGame.getReferree();
        if(referee == null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Select a game with a referee");
            alert.setHeaderText("Select a game with a referee");
            alert.show();
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rate_referee.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 250, 100);
            Stage stage = new Stage();
            RateRefereeController controller = fxmlLoader.getController();
            controller.setReferee(referee);
            controller.setGame(selectedGame);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Rate referee");
            stage.setScene(scene);
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    updateReferees();
                }
            });
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void selectedRefereeUpdate() {
        User referee = refereesTable.getSelectionModel().getSelectedItem();
        if(referee != null){
            if (referee.isDisabled())
                disableReferee.setText("Enable");
            else
                disableReferee.setText("Disable");
        }
    }

    private void handleDisableRefereeButton() {
        User disable = refereesTable.getSelectionModel().getSelectedItem();

        if(disable == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Select a referee to disable");
            alert.setHeaderText("Select a referee to disable");
            alert.show();
            return;
        }

        if (!disable.isDisabled()){
            Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to disable this referee?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Disable referee");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.YES)
                    .ifPresent(response -> {
                        userAPI.disable(disable);
                        updateReferees();
                    });
        }
        else {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to enable this referee?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Enable referee");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.YES)
                    .ifPresent(response -> {
                        userAPI.enable(disable);
                        updateReferees();
                    });
        }
    }

    private void handleFinishMatchsButton() {
        if (showFutureGames){
            matchesTable.getItems().setAll(gamesAPI.getFutureGames());
            finishMatchs.setText("Finished");
            rate.setVisible(false);
        } else {
            matchesTable.getItems().setAll(gamesAPI.getPastGames());
            finishMatchs.setText("Future");
            rate.setVisible(true);
        }
        showFutureGames = !showFutureGames;
    }

    private void handleRemoveRefereeButton() {
        User deleteReferee = refereesTable.getSelectionModel().getSelectedItem();
        if(deleteReferee == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Select a referee to delete");
            alert.setHeaderText("Select a referee to delete");
            alert.show();
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to remove this referee?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete referee");
        alert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> {
                    userAPI.delete(deleteReferee);
                    updateReferees();
                });
    }

    private void handleRemoveMatchButton() {
        Game deleteGame = matchesTable.getSelectionModel().getSelectedItem();
        if(deleteGame == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Select a match to delete");
            alert.setHeaderText("Select a match to delete");
            alert.show();
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to remove this match?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete match");
        alert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> {
                    gamesAPI.delete(deleteGame);
                    updateGames();
                });
    }

    private void handleAddMatchButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_match.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 250, 300);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add match");
            stage.setScene(scene);
            /*stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    updateGames();
                }
            });*/
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleMatchModifyButton() {
        Game selectedGame = matchesTable.getSelectionModel().getSelectedItem();
        if(selectedGame == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Select a match to modify");
            alert.setHeaderText("Select a match to modify");
            alert.show();
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/modify_match.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 250, 300);
            Stage stage = new Stage();
            FormMatchModifyController controller = fxmlLoader.getController();
            controller.setGame(selectedGame);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modify match");
            stage.setScene(scene);
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    updateGames();
                }
            });
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handlePaymentsButton(){
        User selectedReferee = refereesTable.getSelectionModel().getSelectedItem();
        if(selectedReferee == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Select a referee see payment");
            alert.setHeaderText("Select a referee to see how much he is going to be paid ");
            alert.show();
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/payment_form.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 250, 300);
            Stage stage = new Stage();
            PaymentsController controller = fxmlLoader.getController();
            controller.setUser(selectedReferee);
            stage.setTitle("Payments");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // TESTAR APENAS COM 1 USER (COLOCAR GET N USERS NO USERS-API)
    private void updateReferees() {
        List<User> allReferees = userAPI.getReferees();
        List<User> filteredReferees = new ArrayList<>();
        Game selectedGame = matchesTable.getSelectionModel().getSelectedItem();

        if(selectedGame != null) {
            if(selectedGame == previousSelectedGame){
                refereesTable.getItems().setAll(allReferees);
                matchesTable.getSelectionModel().clearSelection();
                previousSelectedGame = null;
                return;
            }
            for (User r : allReferees) {
                Timeslots timeslotsAPI = new Timeslots();
                List<Timeslot> timeslots = timeslotsAPI.get(selectedGame);

                for (Timeslot timeslot : timeslots)
                    if (timeslot.getUser().getId() == r.getId() && !r.isDisabled())
                        filteredReferees.add(r);

            }
            previousSelectedGame = selectedGame;

            refereesTable.getItems().setAll(filteredReferees);
            return;
        }
        for (User r : allReferees)
            if (r.isDisabled())
                filteredReferees.add(r);

        refereesTable.getItems().setAll(allReferees);

    }

    private void updateGames() {
        matchesTable.getItems().setAll(gamesAPI.getFutureGames());
    }

    private void handleAssignButton(){
        Game selectedGame = matchesTable.getSelectionModel().getSelectedItem();


        if(selectedGame == null) {
            List<Game> games = gamesAPI.getFutureGames();
            Map<User, Integer> assignments = ApiUtils.assignRefereeToGames(games);
            if (assignments.size() == 0) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("No referee available");
                alert.setHeaderText("No referee available");
                alert.show();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Referees assigned");
                Integer gamesAssigned = assignments.values().stream().reduce(0, Integer::sum);
                alert.setHeaderText(gamesAssigned +" Referees assigned");
                alert.show();
            }
            updateGames();
            createPayments(games);
            return;
        }

        if (selectedGame.getReferree() != null)
            return;
        
        User assignedReferee = ApiUtils.assignRefereeToGame(selectedGame);

        if (assignedReferee == null){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No referee available");
            alert.setHeaderText("No referee available");
            alert.show();
            return;
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Referee assigned");
            alert.setHeaderText("Referee " + assignedReferee.getUsername() + " assigned");
            alert.show();
        }

        createPayment(selectedGame);
        updateGames();
    }

    private void createPayment(Game selectedGame){
        gamesAPI.refresh(selectedGame);
        if (selectedGame.getReferree() == null)
            return;
        paymentsAPI.create(new Payment(selectedGame.getReferree(), selectedGame.getId(), selectedGame.getGameDate(), selectedGame.getHomeTeam(), selectedGame.getVisitorTeam(), selectedGame.getDivision(), selectedGame.getLocation(), pricesAPI.getGamePrize(), pricesAPI.getPriceMeal(), pricesAPI.getPriceDesloc(selectedGame.getReferree().getLocation(), selectedGame.getLocation()), pricesAPI.getGamePrize() + pricesAPI.getPriceMeal() + pricesAPI.getPriceDesloc(selectedGame.getReferree().getLocation(), selectedGame.getLocation())));
    }

    private void createPayments(List<Game> selectedGames){
        for (Game g : selectedGames)
            createPayment(g);
    }

    private void handleDeassignButton(){
        Game selectedGame = matchesTable.getSelectionModel().getSelectedItem();

        if(selectedGame != null){
            if (selectedGame.getReferree() == null)
                return;

            deassignRefereeToGame(paymentsAPI, selectedGame);
            gamesAPI.update(selectedGame);

            paymentsAPI.delete(paymentsAPI.getPaymentFromID(selectedGame.getId()));

            updateGames();
        }
    }

    public void deassignRefereeToGame(Payments paymentsAPI, Game game) {
        game.setReferree(null);

        paymentsAPI.delete(paymentsAPI.getPaymentFromID(game.getId()));
    }

    private void handleLogoutButtonAction(){
        try{
            UserData.getInstance().setCurrentUser(null);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login_form.fxml"));
            Scene scene = new Scene(loader.load(), 320, 99);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setTitle("Login Page");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addReferee(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_account.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 250, 120);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add referee");
            stage.setScene(scene);
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    updateReferees();
                }
            });
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}