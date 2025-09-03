package pt.isec.gps2324.gps_g14.App.Controllers;

import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.isec.gps2324.gps_g14.App.API.Games;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.Utils.DateUtils;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class AddMatchController {

    Games games;

    @FXML
    private ComboBox<Sports> cmbSports;
    @FXML
    private Spinner<Integer> spinDivision;
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
    private Spinner<Integer> HourSpinner;
    @FXML
    private Spinner<Integer> MinuteSpinner;
    @FXML
    private Button btnSave, btnCancel;

    @FXML
    protected void initialize() {
        games = new Games();
        cmbSports.setItems(FXCollections.observableArrayList(Sports.values()));
        btnSave.setOnAction(e -> AddMatch(e));
        btnCancel.setOnAction(e -> cancel(e));
    }

    private void cancel(ActionEvent e) {
        closeStage(e);
    }

    @FXML
    private void AddMatch(ActionEvent e) {
        Calendar time = Calendar.getInstance();
        Sports sport = cmbSports.getValue();
        int division = spinDivision.getValue();
        String description = txtDescription.getText();
        String location = txtLocation.getText();
        String teamHome = txtTeamHome.getText();
        String teamVisitor = txtTeamVisitor.getText();
        Date date =  DateUtils.toDate(dpTime.getValue());
        time.setTime(date);
        time.set(Calendar.HOUR_OF_DAY, HourSpinner.getValue());
        time.set(Calendar.MINUTE, MinuteSpinner.getValue());

        try{
            games.create(new Game(sport, division, teamHome, teamVisitor, location, time.getTime(), description));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "success to create a Match.", ButtonType.OK);
            alert.setHeaderText("success");
            alert.showAndWait();
            clearFields();
        }catch (RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to create a Match.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void closeStage(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void clearFields() {
        // Limpe os campos do formulário após adicionar o jogo (se necessário)
        cmbSports.setValue(null);
        spinDivision.getValueFactory().setValue(1); // Ou o valor padrão desejado
        txtDescription.clear();
        txtLocation.clear();
        txtTeamHome.clear();
        txtTeamVisitor.clear();
        dpTime.getEditor().clear();
        HourSpinner.getValueFactory().setValue(0); // Ou o valor padrão desejado
        MinuteSpinner.getValueFactory().setValue(0); // Ou o valor padrão desejado
    }
}
