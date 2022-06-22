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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
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
    public Label tourDistance;
    @FXML
    public Label tourDuration;
    @FXML
    public Button updateButton;
    @FXML
    public ImageView mapImageView;
    @FXML
    public TextArea description;


    private TourFx tourFx;

    public TourDetailController(TourDetailViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(TourDetailController.class);

    @FXML
    void initialize() {
        nameTextField.textProperty().bindBidirectional(viewModel.nameProperty());
        tourName.textProperty().bindBidirectional(viewModel.nameProperty());
        tourFrom.textProperty().bindBidirectional(viewModel.fromProperty());
        tourTo.textProperty().bindBidirectional(viewModel.toProperty());
        tourTransport.textProperty().bindBidirectional(viewModel.transportProperty());
        tourDistance.textProperty().bindBidirectional(viewModel.distanceProperty());
        tourDuration.textProperty().bindBidirectional(viewModel.durationProperty());
        description.textProperty().bindBidirectional(viewModel.descriptionProperty());
        mapImageView.imageProperty().bindBidirectional(viewModel.mapURLProperty());
        // load the image
        //if(viewModel.getTourFx() != null) {
            /*var src = String.valueOf(viewModel.mapURLProperty());
            System.out.println("src: " + src);
            Image image = new Image(src);
            mapImageView.setImage(image);*/
        //}

    }

    public void onUpdateButton(MouseEvent mouseEvent) throws URISyntaxException {

        try {
            logger.info("Update Button clicked");
            DAL.getInstance().tourDao().update(viewModel.getTourFx());
            viewModel.setTourModel(viewModel.getTourFx());
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewModel.refreshTour();
    }

    public void changeTitle(KeyEvent keyEvent) {
       // viewModel.updateTourModel();
    }

}
