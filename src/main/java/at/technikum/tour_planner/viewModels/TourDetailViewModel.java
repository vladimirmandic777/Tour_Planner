package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.model.TourFx;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;

public class TourDetailViewModel {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private volatile boolean isInitValue = false;
    private TourFx tourFx;

    public TourDetailViewModel() {
        name.addListener((arg, oldVal, newVal) -> updateTourModel());
        from.addListener((arg, oldVal, newVal) -> updateTourModel());
    }

    public void setTourModel(TourFx tourFx) {
        isInitValue = true;
        if (tourFx == null) {
            name.set("");
            from.set("");
            to.set("");
            return;
        }
        this.tourFx = tourFx;
        name.setValue(tourFx.getName());
        from.setValue(tourFx.getFromDestination());
        to.setValue(tourFx.getToDestination());
        isInitValue = false;
    }


    public void updateTourModel() {
        if (!isInitValue)
            DAL.getInstance().tourDao().update(tourFx, Arrays.asList(tourFx.getId(), name.get(),from.get(), to.get()));
    }

    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty fromProperty() {
        return from;
    }
    public StringProperty toProperty() {
        return to;
    }



}
