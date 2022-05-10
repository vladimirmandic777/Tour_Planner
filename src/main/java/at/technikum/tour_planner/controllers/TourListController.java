package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.TourListViewModel;;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class TourListController{
    private final TourListViewModel viewModel;

    @FXML
    public ListView<at.technikum.tour_planner.model.TourFx> listView;

    public TourListController(TourListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    void initialize() {
        listView.setItems(viewModel.getData());
        listView.getSelectionModel().selectedItemProperty().addListener(viewModel.getChangeListener());
    }
}
