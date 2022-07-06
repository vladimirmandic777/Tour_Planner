package at.technikum.tour_planner.ImportTour;

import java.io.InputStream;

public interface MapAPIService {

    String queryDistance();

    String queryTime();

    InputStream queryMap();
}
