package pt.isec.gps2324.gps_g14.App.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.isec.gps2324.gps_g14.App.API.Users;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.API.Models.UserData;

import java.io.IOException;

public class FormLoginController {
    Users users;
    String username;
    String password;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        users = new Users();
        loginButton.setOnAction(event -> handleLoginButtonAction());
    }

    @FXML
    private void handleLoginButtonAction() {

        username = usernameField.getText();
        password = passwordField.getText();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);


        User user = login(users, username,password);
        if (user == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login failed");
            alert.setHeaderText("Login failed");
            alert.setContentText("The username or password you entered is incorrect.");
            alert.show();
            return;
        }

        UserData.getInstance().setCurrentUser(user);
        loadDashboardUI();
    }

    public static User login(Users users, String name, String password){
        User user = users.get(name);
        if (user == null || !user.checkPassword(password)){
            return null;
        }

        return user;
    }

    private void loadDashboardUI() {
        try {
            FXMLLoader loader;
            if(!users.get(username).isAdmin())
                loader = new FXMLLoader(getClass().getResource("/fxml/referee_panel.fxml"));
            else
                loader = new FXMLLoader(getClass().getResource("/fxml/admin_panel.fxml"));

            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setTitle("Referee Management Application");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}