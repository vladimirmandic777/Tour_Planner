package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.model.TourLog;
import at.technikum.tour_planner.viewModels.TourListViewModel;
import at.technikum.tour_planner.viewModels.TourLogViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import retrofit2.http.FieldMap;

import java.util.Date;

public class TourLogController {


    @FXML
    public TableView<TourLog> logTable;

    private final TourLogViewModel viewModel;

    public TourLogController(TourLogViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    public void initialize() {
        var commentCol = new TableColumn<TourLog, String>("Comment");
        var dateCol = new TableColumn<TourLog, Date>("Date");
        var difficultyCol = new TableColumn<TourLog, Integer>("Difficulty");
        var timeCol = new TableColumn<TourLog, Date>("Time");
        var ratingCol = new TableColumn<TourLog, Integer>("Rating");

        logTable.getColumns().addAll(commentCol, dateCol, difficultyCol, timeCol, ratingCol);

        commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        difficultyCol.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));

        logTable.setItems(viewModel.getTourLogs());
    }
}
