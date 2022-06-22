package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import at.technikum.tour_planner.viewModels.TourListViewModel;;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class TourListController{
    private final TourListViewModel viewModel;

    @FXML
    public ListView<at.technikum.tour_planner.model.TourFx> listView;

    public TourListController(TourListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(TourListController.class);

    @FXML
    void initialize() {
        listView.setItems(viewModel.getData());
        listView.getSelectionModel().selectedItemProperty().addListener(viewModel.getChangeListener());
    }

    public void onButtonRemove(ActionEvent actionEvent) {
        logger.info("Delete Button clicked");
        DAL.getInstance().tourDao().delete(listView.getSelectionModel().getSelectedItem());
        viewModel.deleteTour(listView.getSelectionModel().getSelectedItem());
    }

    public void onButtonAdd(ActionEvent actionEvent) {
        logger.info("Added a new Tour");
        viewModel.addNewTour();
        listView.getSelectionModel().selectLast();
    }
}
