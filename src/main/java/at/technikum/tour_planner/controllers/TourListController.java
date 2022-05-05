package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.viewModels.TourDetailViewModel;
import at.technikum.tour_planner.viewModels.TourListViewModel;

public class TourListController {
    private final TourListViewModel viewModel;

    public TourListController(TourListViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
