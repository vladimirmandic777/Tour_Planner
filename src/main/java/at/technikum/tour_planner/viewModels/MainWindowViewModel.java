package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.search.BL;

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
        this.searchBarViewModel.addSearchListener(searchString->searchTours(searchString));
    }

    private void searchTours(String searchString) {
        var tours = BL.getInstance().findMatchingTours(searchString);
        tourListViewModel.setTours(tours);
    }


    private void selectTour(TourFx tourFx) {
        tourDetailViewModel.setTourModel(tourFx);
    }
}
