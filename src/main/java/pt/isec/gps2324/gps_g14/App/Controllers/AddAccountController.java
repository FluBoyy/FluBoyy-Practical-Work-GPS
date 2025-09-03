package pt.isec.gps2324.gps_g14.App.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pt.isec.gps2324.gps_g14.App.API.Entities.Rating;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.API.Ratings;
import pt.isec.gps2324.gps_g14.App.API.Users;
import pt.isec.gps2324.gps_g14.App.Utils.RatingsEnum;

public class AddAccountController {
    Users users;
    String username;
    String password;
    Ratings ratingsAPI;
    int level;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button addReferee;
    @FXML
    private Button cancel;

    @FXML
    private ComboBox<RatingsEnum> comboLevel;

    @FXML
    public void initialize() {
        users = new Users();
        ratingsAPI = new Ratings();
        comboLevel.setItems(FXCollections.observableArrayList(RatingsEnum.values()));
        addReferee.setOnAction(event -> addReferee());
        cancel.setOnAction(event -> {
            cancel.getScene().getWindow().hide();
        });
    }

    private void addReferee() {
        username = usernameField.getText();
        password = passwordField.getText();
        switch (comboLevel.getSelectionModel().getSelectedItem()) {
            case UM -> level = 1;
            case DOIS -> level = 2;
            case TRES -> level = 3;
            case QUATRO -> level = 4;
            case CINCO -> level = 5;
        }
        users.create(new User(username, password, level, "Coimbra"));

        addReferee.getScene().getWindow().hide();
    }


}
