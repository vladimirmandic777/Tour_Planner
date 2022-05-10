package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.model.TourFx;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;

public class TourDetailViewModel {

    private final StringProperty name = new SimpleStringProperty();
    private volatile boolean isInitValue = false;
    private TourFx tourFx;

    public TourDetailViewModel() {
        name.addListener((arg, oldVal, newVal) -> updateTourModel());
    }

    public void setTourModel(TourFx tourFx) {
        isInitValue = true;
        if (tourFx == null) {
            name.set("");
            return;
        }
        this.tourFx = tourFx;
        name.setValue(tourFx.getName());
        isInitValue = false;
    }


    private void updateTourModel() {
        if (!isInitValue)
            DAL.getInstance().tourDao().update(tourFx, Arrays.asList(tourFx.getId(), name.get()));
    }

    public StringProperty nameProperty() {
        return name;
    }

}
