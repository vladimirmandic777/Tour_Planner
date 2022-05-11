package at.technikum.tour_planner.search;

import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.model.TourFx;

import java.util.List;
import java.util.stream.Collectors;

public class BL {
    public List<TourFx> findMatchingTours(String searchText) {
        var tours = DAL.getInstance().tourDao().getAll();
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
    private static BL instance = new BL();

    public static BL getInstance() { return instance; }
}
