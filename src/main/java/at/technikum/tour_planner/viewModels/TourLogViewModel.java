package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.dal.DALLOG;
import at.technikum.tour_planner.dal.Dao;
import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.util.List;

public class TourLogViewModel {

    @Getter
    private ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();

    private volatile boolean isInitValue = false;

    public TourLogViewModel() {

    }

    public void setTourModel(TourFx tourFx) {
        isInitValue = true;
        TourLog log = DALLOG.getInstance(tourFx).tourLogDao().get(tourFx.getId()).get();

        if (log == null){
            return;
        }

        tourLogs.add(log);
        isInitValue = false;
    }

    public void setTours(List<TourFx> tourItem) {
      //  tourLogs.clear();
        //tourLogs.addAll(tourItem);
    }

}
