package pt.isec.gps2324.gps_g14.App.Controllers;

import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pt.isec.gps2324.gps_g14.App.API.Games;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.Utils.DateUtils;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class FormMatchModifyController {
    @FXML
    private TextField txtId;

    @FXML
    private ComboBox<Sports> cmbSports;
    
    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtLocation;
    
    @FXML
    private TextField txtTeamHome;
    
    @FXML
    private TextField txtTeamVisitor;

    @FXML
    private DatePicker dpTime;

    @FXML
    private Spinner<Integer> spinDivision;

    @FXML
    private Spinner<Integer> HourSpinner;

    @FXML
    private Spinner<Integer> MinuteSpinner;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    private Game game;
    private static final Games gamesAPI = new Games();

    public void setGame(Game game){
        this.game = game;
        txtId.setText(String.valueOf(game.getId()));
        cmbSports.setValue(game.getModality());
        spinDivision.getValueFactory().setValue(game.getDivision());
        txtDescription.setText(game.getGameDesc());
        txtLocation.setText(game.getLocation());
        txtTeamHome.setText(game.getHomeTeam());
        txtTeamVisitor.setText(game.getVisitorTeam());
        Calendar cal = Calendar.getInstance();
        cal.setTime(game.getGameDate());
        HourSpinner.getValueFactory().setValue(cal.get(Calendar.HOUR_OF_DAY));
        MinuteSpinner.getValueFactory().setValue(cal.get(Calendar.MINUTE));
        dpTime.setValue(DateUtils.toLocalDate(cal));
    }

    @FXML
    private void initialize(){
        cmbSports.setItems(FXCollections.observableArrayList(Sports.values()));
        btnCancel.setOnAction(e -> handleCancelButton(e));
        btnSave.setOnAction(e -> handleSaveButton(e));
    }

    
    private void closeStage(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void handleCancelButton(ActionEvent e) {
        closeStage(e);
    }

    private void handleSaveButton(ActionEvent e) {

        Calendar cal = Calendar.getInstance();
        Date date = DateUtils.toDate(dpTime.getValue());
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, HourSpinner.getValue());
        cal.set(Calendar.MINUTE, MinuteSpinner.getValue());

        this.game.setDivision(spinDivision.getValue());
        this.game.setGameDesc(txtDescription.getText());
        this.game.setGameDate(cal.getTime());
        this.game.setHomeTeam(txtTeamHome.getText());
        this.game.setLocation(txtLocation.getText());
        this.game.setModality(cmbSports.getValue());
        this.game.setVisitorTeam(txtTeamVisitor.getText());

        try {
            gamesAPI.update(this.game);
            closeStage(e);
        } catch (RuntimeException exception) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to update game.");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }
}
