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

    public void onGenerateReportClicked(ActionEvent actionEvent) {
        viewModel.generateReport();
    }

    public void onGenerateTourReportClicked(ActionEvent actionEvent) {
        viewModel.generateTourReport();
    }

    public void onExportTour(ActionEvent actionEvent) {
        viewModel.generateExport();
    }

    public void onImportTour(ActionEvent actionEvent) {
        viewModel.chooseFile();
    }
}
