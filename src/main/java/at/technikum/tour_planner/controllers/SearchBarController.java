package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.NavigationBarViewModel;
import at.technikum.tour_planner.viewModels.SearchBarViewModel;

public class SearchBarController {
    private final SearchBarViewModel viewModel;

    public SearchBarController(SearchBarViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
