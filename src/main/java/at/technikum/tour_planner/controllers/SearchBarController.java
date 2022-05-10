package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.NavigationBarViewModel;
import at.technikum.tour_planner.viewModels.SearchBarViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchBarController {
    private final SearchBarViewModel viewModel;
    @FXML
    public TextField searchTextField;

    public SearchBarController(SearchBarViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void onSearchButton(ActionEvent actionEvent) {
        System.out.println("Searching...");
    }

    public void onClearButton(ActionEvent actionEvent) {
        System.out.println("Clearing...");
        //clear textfield with fx id "searchTextField"
        this.searchTextField.setText("");

    }
}
