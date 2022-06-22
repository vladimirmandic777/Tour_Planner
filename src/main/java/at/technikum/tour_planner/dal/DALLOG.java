package at.technikum.tour_planner.dal;

import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.model.TourLog;

public class DALLOG {

    private DaoLog<TourLog> logDao;

    private static TourFx tourFx;


    private DALLOG() {
        logDao = new TourLogDao();
    }

    //
    // Logs
    //
    public DaoLog<TourLog> tourLogDao() {
        return logDao;
    }


    //
    // Singleton-Pattern for DALLOG with early-binding
    //
    private static DALLOG instance = new DALLOG();

    public static DALLOG getInstance() {
        return new DALLOG();
    }
}
