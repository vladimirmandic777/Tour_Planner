package at.technikum.tour_planner.dal;

import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.model.TourLog;

public class DALLOG {

    private Dao<TourLog> logDao;

    private static TourFx tourFx;


    private DALLOG(TourFx tourFx) {
        logDao = new TourLogDao(tourFx);
    }

    //
    // Logs
    //
    public Dao<TourLog> tourLogDao() {
        return logDao;
    }


    //
    // Singleton-Pattern for DALLOG with early-binding
    //
    private static DALLOG instance = new DALLOG(tourFx);

    public static DALLOG getInstance(TourFx tourFx) {
        return new DALLOG(tourFx);
    }
}
