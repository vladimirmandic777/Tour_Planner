package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.SearchBarViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.apache.log4j.Logger;

public class SearchBarController {
    private final SearchBarViewModel viewModel;


    private static final Logger logger = Logger.getLogger(SearchBarController.class);

    @FXML
    public Button searchButton;

    @FXML
    public TextField searchTextField;

    @FXML
    void initialize() {
        searchTextField.textProperty().bindBidirectional(viewModel.searchStringProperty());
    }

    public SearchBarController(SearchBarViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void onSearchKey(KeyEvent actionEvent) {
        viewModel.doSearch();
        logger.info("Searching...");
    }

    public void onClearButton(ActionEvent actionEvent) {
        logger.info("Clearing...");
        this.searchTextField.setText("");
        viewModel.doSearch();
    }
}
