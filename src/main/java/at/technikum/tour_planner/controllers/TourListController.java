package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.viewModels.TourListViewModel;;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import lombok.extern.log4j.Log4j2;


@Log4j2
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

    public void onButtonRemove(ActionEvent actionEvent) {
        log.info("Deleted tour with the id  {} ",  listView.getSelectionModel().getSelectedItem().getId());
        DAL.getInstance().tourDao().delete(listView.getSelectionModel().getSelectedItem());
        viewModel.deleteTour(listView.getSelectionModel().getSelectedItem());
    }

    public void onButtonAdd(ActionEvent actionEvent) {
        log.info("Added a new Tour");
        viewModel.addNewTour();
        listView.getSelectionModel().selectLast();
    }
}
