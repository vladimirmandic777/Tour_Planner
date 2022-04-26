package at.technikum.tour_planner.main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TourController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private Tour[] tourList = new Tour[5];



}