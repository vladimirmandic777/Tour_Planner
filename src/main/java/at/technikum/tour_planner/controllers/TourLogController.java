package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.dal.DALLOG;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import at.technikum.tour_planner.model.TourLog;
import at.technikum.tour_planner.viewModels.TourListViewModel;
import at.technikum.tour_planner.viewModels.TourLogViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import retrofit2.http.FieldMap;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class TourLogController {


    @FXML
    public TableView<TourLog> logTable;

    private final TourLogViewModel viewModel;

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(TourListController.class);

    @FXML
    private TableColumn<TourLog, String> commentCol;
    private TableColumn<TourLog, Date> dateCol, timeCol;
    private TableColumn<TourLog, Integer> difficultyCol, ratingCol;

    public TourLogController(TourLogViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    public void initialize() {
        commentCol = new TableColumn<TourLog, String>("Comment");
        dateCol = new TableColumn<TourLog, Date>("Date");
        difficultyCol = new TableColumn<TourLog, Integer>("Difficulty");
        timeCol = new TableColumn<TourLog, Date>("Time");
        ratingCol = new TableColumn<TourLog, Integer>("Rating");

        logTable.getColumns().addAll(commentCol, dateCol, difficultyCol, timeCol, ratingCol);
        logTable.setEditable(true);

        commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        difficultyCol.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));

        logTable.setItems(viewModel.getTourLogs());

        editableCols();
    }

    private void editableCols() {
        commentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        commentCol.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setComment(e.getNewValue()));

        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        dateCol.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setDate(e.getNewValue()));

        difficultyCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        difficultyCol.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setDifficulty(e.getNewValue()));

        timeCol.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        timeCol.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setTime(e.getNewValue()));

        ratingCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ratingCol.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setRating(e.getNewValue()));

    }


    public void onButtonRemove(ActionEvent actionEvent) {
        logger.info("Delete Button clicked");
        DALLOG.getInstance().tourLogDao().delete(logTable.getSelectionModel().getSelectedItem());
        viewModel.deleteTourLog(logTable.getSelectionModel().getSelectedItem());
    }

    public void onButtonAdd(ActionEvent actionEvent) {
        logger.info("Added a new Tour log");
        viewModel.addNewLog();
        logTable.getSelectionModel().selectLast();
    }

    public void onUpdateButton(MouseEvent mouseEvent) {
        try {
            logger.info("Update Button clicked");
            viewModel.updateLogModel(logTable.getSelectionModel().getSelectedItem());
            DALLOG.getInstance().tourLogDao().update(logTable.getSelectionModel().getSelectedItem());
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }
    }
}
