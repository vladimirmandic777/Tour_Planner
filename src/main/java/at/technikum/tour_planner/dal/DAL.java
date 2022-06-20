package at.technikum.tour_planner.dal;

import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.model.TourLog;

public class DAL {

    private Dao<TourFx> tourDao;
    private Dao<TourLog> tourLogDao;

    private DAL() {
        tourDao = new TourFxDao();
        tourLogDao = new TourLog();
    }

    //
    // Tours:
    //
    public Dao<TourFx> tourDao() {
        return tourDao;
    }


    //
    // Singleton-Pattern for DAL with early-binding
    //
    private static DAL instance = new DAL();

    public static DAL getInstance() {
        return instance;
    }

}