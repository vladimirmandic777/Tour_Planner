package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.NavigationBarViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class NavigationBarController {
    private final NavigationBarViewModel viewModel;

    public NavigationBarController(NavigationBarViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void onFileQuitcloseWindow(ActionEvent actionEvent) {
        Platform.exit();
    }
}
