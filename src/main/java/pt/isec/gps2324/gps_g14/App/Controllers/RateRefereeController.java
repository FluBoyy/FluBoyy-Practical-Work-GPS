package pt.isec.gps2324.gps_g14.App.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pt.isec.gps2324.gps_g14.App.API.Entities.Game;
import pt.isec.gps2324.gps_g14.App.API.Entities.User;
import pt.isec.gps2324.gps_g14.App.API.Ratings;
import pt.isec.gps2324.gps_g14.App.Utils.RatingsEnum;
import pt.isec.gps2324.gps_g14.App.API.Entities.Rating;
import pt.isec.gps2324.gps_g14.App.Utils.Sports;

public class RateRefereeController {
    User referee;
    Game game;
    Ratings ratingsAPI;

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    @FXML
    private TextField refereeName;

    @FXML
    private ComboBox<RatingsEnum> comboRatings;

    public RateRefereeController(){
    }
    public void setReferee(User referee) {
        this.referee = referee;
        refereeName.setText(referee.getUsername());
    }
    public void setGame(Game game) {
        this.game = game;
    }


    @FXML
    private void initialize() {
        ratingsAPI = new Ratings();
        comboRatings.setItems(FXCollections.observableArrayList(RatingsEnum.values()));

        confirm.setOnAction((event) -> {
            switch (comboRatings.getSelectionModel().getSelectedItem()) {
                case UM -> ratingsAPI.create(new Rating(referee, game, 1));
                case DOIS -> ratingsAPI.create(new Rating(referee, game, 2));
                case TRES -> ratingsAPI.create(new Rating(referee, game, 3));
                case QUATRO -> ratingsAPI.create(new Rating(referee, game, 4));
                case CINCO -> ratingsAPI.create(new Rating(referee, game, 5));
            }

            confirm.getScene().getWindow().hide();
        });

        cancel.setOnAction(event -> {
            cancel.getScene().getWindow().hide();
        });
    }
}
