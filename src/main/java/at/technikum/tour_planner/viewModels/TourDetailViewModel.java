package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.BAL.MapTourService;
import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import at.technikum.tour_planner.model.TourFx;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;

public class TourDetailViewModel {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty transport = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private volatile boolean isInitValue = false;
    private TourFx tourFx;

    //  private final MapTourService mapTourService;

   // public TourDetailViewModel(MapTourService mapTourService) {
    public TourDetailViewModel() {
       // this.mapTourService = mapTourService;
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
        transport.setValue(tourFx.getTransport());
        distance.setValue(String.valueOf(tourFx.getDistance()));
        duration.setValue(String.valueOf(tourFx.getEstimatedTime()));
        isInitValue = false;
    }


    public void updateTourModel() {
        if (!isInitValue)
            DAL.getInstance().tourDao().update
                    (tourFx, Arrays.asList(tourFx.getId(), name.get(),
                            "description", from.get(), to.get(), transport.get(),
                            distance.get(), duration.get(), "route info"));
       // refreshTour();
    }

    public void refreshTour() {
        if (!isInitValue)
            DAL.getInstance().tourDao().getAll();
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
    public StringProperty transportProperty() {
        return transport;
    }
    public StringProperty distanceProperty() {
        return distance;
    }
    public StringProperty durationProperty() {
        return duration;
    }

    public TourFx getTourFx() {
        return tourFx;
    }

}
