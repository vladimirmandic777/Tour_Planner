package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.SearchBarViewModel;
import at.technikum.tour_planner.viewModels.TourDetailViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TourDetailController {
    private final TourDetailViewModel viewModel;

    @FXML
    public TextField nameTextField;

    public TourDetailController(TourDetailViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @FXML
    void initialize() {
        nameTextField.textProperty().bindBidirectional(viewModel.nameProperty());
    }
}
