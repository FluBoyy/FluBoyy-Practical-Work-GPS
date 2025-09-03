package pt.isec.gps2324.gps_g14.App.Controllers;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import pt.isec.gps2324.gps_g14.App.API.Timeslots;
import pt.isec.gps2324.gps_g14.App.API.Entities.Timeslot;
import pt.isec.gps2324.gps_g14.App.API.Models.UserData;
import pt.isec.gps2324.gps_g14.App.Utils.DateUtils;

public class AddTimeslotController {
    UserData userData;
    Timeslots timeslotApi;

    @FXML
    private Button add;

    @FXML
    private Button cancell;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Spinner<Integer> startHourSpinner;

    @FXML
    private Spinner<Integer> startMinuteSpinner;

    @FXML
    private DatePicker finishDatePicker;

    @FXML
    private Spinner<Integer> finishHourSpinner;

    @FXML
    private Spinner<Integer> finishMinuteSpinner;

    @FXML
    private void initialize(){
        add.setOnAction(e -> addTimeSlot(e));
        cancell.setOnAction(e -> cancell(e));
        userData = UserData.getInstance();
        timeslotApi = new Timeslots();
        Calendar now = Calendar.getInstance();
        startDatePicker.setValue(LocalDate.now());
        startHourSpinner.getValueFactory().setValue(now.get(Calendar.HOUR_OF_DAY));
        startMinuteSpinner.getValueFactory().setValue(now.get(Calendar.MINUTE));
        now.add(Calendar.HOUR_OF_DAY, 1);
        finishDatePicker.setValue(LocalDate.now());
        finishHourSpinner.getValueFactory().setValue(now.get(Calendar.HOUR_OF_DAY) % 24);
        finishMinuteSpinner.getValueFactory().setValue(now.get(Calendar.MINUTE));
    }

    private void closeStage(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void cancell(ActionEvent e) {
        closeStage(e);
    }

    public void addTimeSlot(ActionEvent e){
        Calendar start = Calendar.getInstance();
        Calendar finish = Calendar.getInstance();
        Date startDate = DateUtils.toDate(startDatePicker.getValue());
        start.setTime(startDate);
        Date finishDate = DateUtils.toDate(finishDatePicker.getValue());
        finish.setTime(finishDate);
        start.set(Calendar.HOUR_OF_DAY, startHourSpinner.getValue());
        start.set(Calendar.MINUTE, startMinuteSpinner.getValue());
        finish.set(Calendar.HOUR_OF_DAY, finishHourSpinner.getValue());
        finish.set(Calendar.MINUTE, finishMinuteSpinner.getValue());

        try {
            timeslotApi.create(new Timeslot(userData.getCurrentUser(), start.getTime(), finish.getTime()));
            closeStage(e);
        } catch (RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to create timeslot.", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
