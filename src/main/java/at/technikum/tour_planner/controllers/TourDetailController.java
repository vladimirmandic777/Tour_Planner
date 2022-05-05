package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.SearchBarViewModel;
import at.technikum.tour_planner.viewModels.TourDetailViewModel;

public class TourDetailController {
    private final TourDetailViewModel viewModel;

    public TourDetailController(TourDetailViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
