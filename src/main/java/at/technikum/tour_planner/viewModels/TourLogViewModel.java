package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.dal.DALLOG;
import at.technikum.tour_planner.dal.DaoLog;
import at.technikum.tour_planner.listener.SearchListener;
import at.technikum.tour_planner.listener.SearchLogListener;
import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.model.TourLog;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TourLogViewModel {

    @Getter
    private ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();

    private TourLog log;

    private TourFx tourFx;
    private final DaoLog<TourLog> mediaItemDao;

    private final StringProperty searchString = new SimpleStringProperty("");

    private final BooleanBinding isSearchDisabledBinding = Bindings.createBooleanBinding(() -> searchString.get().isEmpty());

    private volatile boolean isInitValue = false;

    private List<SearchLogListener> listeners = new ArrayList<>();


    public TourLogViewModel(DaoLog<TourLog> mediaItemDao) {
        this.mediaItemDao = mediaItemDao;
        searchString.addListener((arg, oldVal, newVal) -> isSearchDisabledBinding.invalidate());
    }

    public void addLogSearchListener(SearchLogListener listener) {
        listeners.add(listener);
    }

    //TODO schöner machen
    public void setTourModel(TourFx tourFx) {
        isInitValue = true;
        List<TourLog> log = DALLOG.getInstance().tourLogDao().getLog(tourFx.getId());

        if (log.isEmpty()) {
            this.tourFx = tourFx;
            tourLogs.clear();
            return;
        }
        this.tourFx = tourFx;
        tourLogs.addAll(log);
        isInitValue = false;
    }

    public void setTourLogs(List<TourLog> tourItem) {
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

    public void updateLogModel(TourLog tourLog) {
        DALLOG.getInstance().tourLogDao().update
                (tourLog, Arrays.asList(tourLog.getId(), tourLog.getDate(),
                        tourLog.getComment(), tourLog.getDifficulty(), tourLog.getTime(), tourLog.getRating(), tourFx));
    }

    public TourLog getLog() {
        return log;
    }

    public StringProperty searchStringProperty() {
        return searchString;
    }

    public void doSearch() {
        for (var listener : listeners) {
            listener.searchLog(searchString.get());
        }
    }
}
