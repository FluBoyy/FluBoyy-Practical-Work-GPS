package pt.isec.gps2324.gps_g14.App.Controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.j256.ormlite.dao.ForeignCollection;

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
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.isec.gps2324.gps_g14.App.API.Games;
import pt.isec.gps2324.gps_g14.App.API.Timeslots;
import pt.isec.gps2324.gps_g14.App.API.Users;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.Timeslot;
import pt.isec.gps2324.gps_g14.App.API.Models.UserData;
import pt.isec.gps2324.gps_g14.App.Utils.DateUtils;

public class PanelRefereeController {
    Timeslots timeslotApi;
    Users userAPI;
    UserData userData;

    Games gamesAPI;

    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;

    @FXML
    private Button logoutButton;
    @FXML
    private TableView<Timeslot> tabelaTimeSlots; // Update the TableView to store Timeslot objects
    @FXML
    private TableColumn<Timeslot, Date> tempoInicio; // Update TableColumn to store Timeslot's start time
    @FXML
    private TableColumn<Timeslot, String> duracao; // Update TableColumn to store Timeslot's duration
    @FXML
    private TableColumn<Timeslot, Integer> id;

    @FXML
    private TableView<Game> matchesTable;
    @FXML
    private TableColumn<Game, String> matchDesc;
    @FXML
    private TableColumn<Game, Date> matchInit;
    @FXML
    private TableColumn<Game, String> matchDuration;
    @FXML
    private TableColumn<Game, String> matchReferee;
    @FXML
    private Label nomeReferee;
    @FXML
    private Label ratingReferee;

    private Timer ratingUpdateTimer;

    @FXML
    private void initialize() {
        addButton.setOnAction(event -> handleAddButton());
        removeButton.setOnAction(event -> handleRemoveButton());
        logoutButton.setOnAction(event -> handleLogoutButton());
        userData = UserData.getInstance();
        ratingUpdateTimer = new Timer();
        ratingUpdateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ratingReferee.setText("Rating: " + userData.getCurrentUser().getRating());
            }
        }, 0, 60 * 1000);

        userAPI = new Users();
        // Assuming you have already initialized the timeslots object
        timeslotApi = new Timeslots();
        gamesAPI = new Games();

        // Test create timeslot
        // userData.getCurrentUser().addTimeSlot(new
        // Timeslot(userData.getCurrentUser(),new Date(2023, Calendar.NOVEMBER,31,
        // 22,0,0 ), new Date(2023, Calendar.NOVEMBER,31, 23, 59, 59)));

        // Configure the columns to display the appropriate data
        tempoInicio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartTime()));
        duracao.setCellValueFactory(cellData -> new SimpleObjectProperty<>(
                DateUtils.getDuration(cellData.getValue().getStartTime(), cellData.getValue().getEndTime())));
        id.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));

        matchDesc.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGameDesc()));
        matchInit.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGameDate()));
        matchDuration.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(DateUtils.getDuration(cellData.getValue().getDuration())));
        matchReferee.setCellValueFactory(cellData -> new SimpleObjectProperty<>(
                cellData.getValue().getReferree() != null ? cellData.getValue().getReferree().getUsername() : "N/A"));

        // If you want to format the Date or Long values, you can use a cell factory to
        // format the data.
        tempoInicio.setCellFactory(column -> {
            return new TableCell<Timeslot, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        // Customize the formatting as needed
                        setText(formatDate(item));
                    }
                }
            };
        });

        // Set up other TableView configurations or event handlers as needed

        // load data
        updateTimeslots();
        updateGames();
        nomeReferee.setText("Olá " + userData.getCurrentUser().getUsername());
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Customize the date format pattern
        return dateFormat.format(date);
    }

    private void updateTimeslots() {
        // Assuming you have already initialized the timeslots object
        ForeignCollection<Timeslot> timeslots = userData.getCurrentUser().getTimeslots();
        // Set the items in the TableView
        tabelaTimeSlots.getItems().setAll(timeslots);
    }

    private void handleAddButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_timeslot.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 220);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Timeslot");
            stage.setScene(scene);
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    updateTimeslots();
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeTimeslot(Timeslot timeslot) {
        try {
            timeslotApi.remove(timeslot.getId());
            updateTimeslots();
        } catch (RuntimeException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Failed to remove timeslot");
            alert.setHeaderText("Failed to remove timeslot");
            alert.show();
        }
    }

    private void handleLogoutButton() {
        try {
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

    private void handleRemoveButton() {
        Timeslot selectedTimeslot = tabelaTimeSlots.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to remove this timeslot?",
                ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> removeTimeslot(selectedTimeslot));
    }

    private void updateGames() {
        matchesTable.getItems().setAll(gamesAPI.getFutureGames());
    }
}