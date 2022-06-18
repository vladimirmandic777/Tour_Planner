package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.viewModels.SearchBarViewModel;
import at.technikum.tour_planner.viewModels.TourDetailViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URISyntaxException;
import java.util.Arrays;

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

    private TourFx tourFx;

    public TourDetailController(TourDetailViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private static final ILoggerWrapper logger = LoggerFactory.getLogger();

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

    public void onUpdateButton(MouseEvent mouseEvent) {
        try {
            logger.info("Update Button clicked");
            DAL.getInstance().tourDao().update(viewModel.getTourFx());
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }
    }

    public void changeTitle(KeyEvent keyEvent) {
       // viewModel.updateTourModel();
    }

}
