package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TourLogViewModel {

    @Getter
    private ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();

    public TourLogViewModel() {
        this.tourLogs.add(new TourLog(new Date(), "Ph", 1, new Date(),6));
    }
}
