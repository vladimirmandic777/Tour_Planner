package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.TourListViewModel;
import at.technikum.tour_planner.viewModels.TourLogViewModel;

public class TourLogController {
    private final TourLogViewModel viewModel;

    public TourLogController(TourLogViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
