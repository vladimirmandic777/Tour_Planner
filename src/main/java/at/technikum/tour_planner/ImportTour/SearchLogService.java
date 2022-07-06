package at.technikum.tour_planner.ImportTour;

import at.technikum.tour_planner.dal.DALLOG;
import at.technikum.tour_planner.dal.DaoLog;
import at.technikum.tour_planner.model.TourLog;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class SearchLogService {

    public DaoLog<TourLog> tourLogDao;

    public SearchLogService(DaoLog<TourLog> tourLogDao) {
        this.tourLogDao = tourLogDao;
    }

    public List<TourLog> findMatchingLog(String searchText, int id) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        var tours = tourLogDao.getLog(id);
        if (searchText == null || searchText.isEmpty()) {
            return tours;
        }
        return tours.stream()
                .filter(t -> t.getComment().toLowerCase().contains(searchText.toLowerCase())
                        || format.format(t.getDate()).toLowerCase().contains(searchText.toLowerCase())
                        || format.format(t.getTime()).toLowerCase().contains(searchText.toLowerCase())
                        || String.valueOf(t.getDifficulty()).toLowerCase().contains(searchText.toLowerCase())
                        || String.valueOf(t.getRating()).toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    //
    // Singleton-Pattern for BL with early-binding
    //
    // TO-DO
    private static SearchLogService instance = new SearchLogService(DALLOG.getInstance().tourLogDao());

    public static SearchLogService getInstance() {
        return instance;
    }
}
