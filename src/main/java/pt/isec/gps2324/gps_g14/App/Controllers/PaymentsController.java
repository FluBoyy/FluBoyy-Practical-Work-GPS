package pt.isec.gps2324.gps_g14.App.Controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.Payment;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.API.Games;
import pt.isec.gps2324.gps_g14.App.API.Payments;
import pt.isec.gps2324.gps_g14.App.API.Users;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PaymentsController {

    private Games gameApi;
    private Users userApi;
    private Payments payApi;

    @FXML
    public TableView<Payment> paymentTable;
    @FXML
    public TableColumn<Payment, Integer> matchID;
    @FXML
    public TableColumn<Payment, Date> matchDate;
    @FXML
    public TableColumn<Payment, String> matchHomeTeam;
    @FXML
    public TableColumn<Payment, String> matchVisitorTeam;
    @FXML
    public TableColumn<Payment, Integer> matchDivision;
    @FXML
    public TableColumn<Payment, String> matchLocation;
    @FXML
    public TableColumn<Payment, Double> payMeal;
    @FXML
    public TableColumn<Payment, Double> payDisp;
    @FXML
    public TableColumn<Payment, Double> payBols;
    @FXML
    public TableColumn<Payment, Double> totalAmount;

    @FXML
    private TextField payTotal;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnPay;
    @FXML
    public Button btnCancel;

    private User user;

    List<Payment> payments;

    @FXML
    private void initialize() {
        gameApi = new Games();
        userApi = new Users();
        payApi = new Payments();

        btnPay.setOnAction(event -> handlePayButton(event));
        btnCancel.setOnAction(event -> handleCancelButton(event));

        matchID.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGameID()));
        matchDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGameDate()));
        matchHomeTeam.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGameHomeTeam()));
        matchVisitorTeam.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGameVisitorTeam()));
        matchDivision.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGameDivision()));
        matchLocation.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGameLocation()));
        payMeal.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPriceMeal()));
        payDisp.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPriceDisp()));
        payBols.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGamePrice()));
        totalAmount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalAmount()));

    }

    private void closeStage(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void handleCancelButton(ActionEvent e) {closeStage(e);}

    private void handlePayButton(ActionEvent e) {

        double total = 0;

        for (Payment p : payments) {
            total += p.getTotalAmount();
            p.setPaid(true);
            p.setPaymentDate(new Date(Calendar.getInstance().getTime().getTime()));
            p.setPaymentDesc("Paid by " + user.getUsername() + " on " + p.getPaymentDate() + ".");
            payApi.update(p);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Information");
        alert.setHeaderText(null);
        alert.setContentText("Total payment: " + total + "€");
        alert.showAndWait();

        payTotal.setText(total + "€");

        closeStage(e);
    }

    public void setUser(User selectedReferee) {
        this.user = selectedReferee;
        txtName.setText(String.valueOf(user.getUsername()));

        payments = payApi.getPaymentFromUser(user);

        payments.removeIf(Payment::isPaid);

        paymentTable.getItems().setAll(payments);
    }
}
