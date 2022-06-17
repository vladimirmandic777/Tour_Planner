package at.technikum.tour_planner.BAL;

import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.dal.Dao;
import at.technikum.tour_planner.model.TourFx;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    public Dao<TourFx> tourFxDao;

    public SearchService(Dao<TourFx> tourFxDao) {
        this.tourFxDao = tourFxDao;
    }

    public void setTourFxDao(Dao<TourFx> tourFxDao) {
        this.tourFxDao = tourFxDao;
    }

    public List<TourFx> findMatchingTours(String searchText) {
        var tours = tourFxDao.getAll();
        if (searchText==null || searchText.isEmpty()) {
            return tours;
        }
        return tours.stream()
                .filter(t->t.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    //
    // Singleton-Pattern for BL with early-binding
    //
    // TO-DO
    private static SearchService instance = new SearchService(DAL.getInstance().tourDao());

    public static SearchService getInstance() { return instance; }
}
