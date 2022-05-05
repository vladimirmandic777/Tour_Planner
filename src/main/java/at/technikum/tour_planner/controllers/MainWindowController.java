package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.MainWindowViewModel;
import javafx.fxml.FXML;

public class MainWindowController {
    @FXML private NavigationBarController navigationBarController;
    @FXML private SearchBarController searchBarController;
    @FXML private TourDetailController tourDetailController;
    @FXML private TourListController tourListController;
    @FXML private TourLogController tourLogController;

    @FXML private final MainWindowViewModel mainViewModel;

    public MainWindowController(MainWindowViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public MainWindowViewModel getMainViewModel() {
        return mainViewModel;
    }

    @FXML void initialize() {
    }




}