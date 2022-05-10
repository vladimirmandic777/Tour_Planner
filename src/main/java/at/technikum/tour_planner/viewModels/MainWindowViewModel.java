package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.model.TourFx;

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
    }


    private void selectTour(TourFx tourFx) {
        tourDetailViewModel.setTourModel(tourFx);
    }
}
