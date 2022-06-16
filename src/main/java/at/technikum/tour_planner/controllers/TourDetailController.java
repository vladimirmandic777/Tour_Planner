package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.SearchBarViewModel;
import at.technikum.tour_planner.viewModels.TourDetailViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TourDetailController {
    private final TourDetailViewModel viewModel;

    @FXML
    public TextField nameTextField;
    @FXML
    public TextField tourName;
    @FXML
    public TextField tourFrom;
    @FXML
    public TextField tourTo;
    @FXML
    public TextField tourTransport;
    @FXML
    public TextField tourDistance;
    @FXML
    public TextField tourDuration;
    @FXML
    public Button updateButton;

    public TourDetailController(TourDetailViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @FXML
    void initialize() {
        nameTextField.textProperty().bindBidirectional(viewModel.nameProperty());
        tourName.textProperty().bindBidirectional(viewModel.nameProperty());
        tourFrom.textProperty().bindBidirectional(viewModel.fromProperty());
        tourTo.textProperty().bindBidirectional(viewModel.toProperty());
        tourTransport.textProperty().bindBidirectional(viewModel.transportProperty());
        tourDistance.textProperty().bindBidirectional(viewModel.distanceProperty());
        tourDuration.textProperty().bindBidirectional(viewModel.durationProperty());
    }

    public void onUpdateButton(ActionEvent actionEvent) {
        System.out.println("Update Button clicked");

    }

    public void changeTitle(KeyEvent keyEvent) {
       // viewModel.updateTourModel();
    }
}
