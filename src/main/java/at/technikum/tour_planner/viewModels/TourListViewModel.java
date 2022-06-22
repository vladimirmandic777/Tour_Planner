package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.dal.Dao;
import at.technikum.tour_planner.listener.SelectionChangedListener;
import at.technikum.tour_planner.model.TourFx;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TourListViewModel {

    private final StringProperty tourName = new SimpleStringProperty("");
    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private final ObservableList<TourFx> data = FXCollections.observableArrayList();

    private final Dao<TourFx> mediaItemDao;

    public String getTourName() {
        return tourName.get();
    }

    public StringProperty tourNameProperty() {
        return tourName;
    }

    public TourListViewModel(Dao<TourFx> mediaItemDao) {
        this.mediaItemDao = mediaItemDao;
        setTours(DAL.getInstance().tourDao().getAll() );
    }

    public ObservableList<TourFx> getData(){
        return data;
    }

    public ChangeListener<TourFx> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listeners.add(listener);
    }

    public void removeSelectionChangedListener(SelectionChangedListener listener) {
        listeners.remove(listener);
    }


    private void notifyListeners(TourFx newValue) {
        for ( var listener : listeners ) {
            listener.changeSelection(newValue);
        }
    }

    public void setTours(List<TourFx> tourItem) {
        data.clear();
        data.addAll(tourItem);
    }

    public void addNewTour() {
        var tour = mediaItemDao.create();
        data.add(tour);
    }

    public void deleteTour(TourFx tour) {
        data.remove(tour);
    }
}
