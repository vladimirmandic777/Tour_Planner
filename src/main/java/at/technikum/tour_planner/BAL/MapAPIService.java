package at.technikum.tour_planner.BAL;

import java.io.InputStream;

public interface MapAPIService {

    String queryDistance();
    String queryTime();
    InputStream queryMap();
}
