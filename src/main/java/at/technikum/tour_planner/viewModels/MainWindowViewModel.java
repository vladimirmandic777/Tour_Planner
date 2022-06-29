package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.ImportTour.SearchLogService;
import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.ImportTour.SearchService;

public class MainWindowViewModel {
    private NavigationBarViewModel navigationBarViewModel;
    private SearchBarViewModel searchBarViewModel;
    private TourListViewModel tourListViewModel;
    private TourDetailViewModel tourDetailViewModel;
    private TourLogViewModel tourLogViewModel;


    public MainWindowViewModel(NavigationBarViewModel navigationBarViewModel, SearchBarViewModel searchBarViewModel, TourListViewModel tourListViewModel, TourDetailViewModel tourDetailViewModel, TourLogViewModel tourLogViewModel) {
        this.navigationBarViewModel = navigationBarViewModel;
        this.searchBarViewModel = searchBarViewModel;
        this.tourListViewModel = tourListViewModel;
        this.tourDetailViewModel = tourDetailViewModel;
        this.tourLogViewModel = tourLogViewModel;


        this.tourListViewModel.addSelectionChangedListener(this::selectTour);
        this.searchBarViewModel.addSearchListener(this::searchTours);
        this.tourLogViewModel.addLogSearchListener(this::searchLog);
    }

    private void searchTours(String searchString) {
        var tours = SearchService.getInstance().findMatchingTours(searchString);
        tourListViewModel.setTours(tours);
    }

    private void searchLog(String searchString) {
        var tours = SearchLogService.getInstance().findMatchingLog(searchString, tourDetailViewModel.getTourFx().getId());
        tourLogViewModel.setTourLogs(tours);
    }

    private void selectTour(TourFx tourFx) {
        tourDetailViewModel.setTourModel(tourFx);
        tourLogViewModel.setTourModel(tourFx);
        navigationBarViewModel.setTourModel(tourFx);
    }
}
