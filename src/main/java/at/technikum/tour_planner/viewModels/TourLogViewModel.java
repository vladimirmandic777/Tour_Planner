package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.dal.DALLOG;
import at.technikum.tour_planner.dal.Dao;
import at.technikum.tour_planner.dal.DaoLog;
import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class TourLogViewModel {

    @Getter
    private ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();


    private final DaoLog<TourLog> mediaItemDao;

    private volatile boolean isInitValue = false;

    public TourLogViewModel(DaoLog<TourLog> mediaItemDao) {
        this.mediaItemDao = mediaItemDao;
    }

    //TODO schöner machen
    public void setTourModel(TourFx tourFx) {
        isInitValue = true;
        List<TourLog> log = DALLOG.getInstance().tourLogDao().getLog(tourFx.getId());

        if (log.isEmpty()) {
            tourLogs.clear();
            return;
        }

        tourLogs.addAll(log);
        isInitValue = false;
    }

    public void setTours(List<TourLog> tourItem) {
        tourLogs.clear();
        tourLogs.addAll(tourItem);
    }

    public void deleteTourLog(TourLog log) {
        tourLogs.remove(log);
    }

    public void addNewLog() {
        var log = mediaItemDao.create();
        tourLogs.add(log);
    }

    public void updateTourModel() {
        if (!isInitValue)
            DAL.getInstance().tourDao().update
                    (tourFx, Arrays.asList(tourFx.getId(), name.get(),
                            "description", from.get(), to.get(), transport.get(),
                            distance.get(), duration.get(), "route info"));

        DALLOG.getInstance().tourLogDao().update(tourLogs, Arrays.asList());
    }
}
