package at.technikum.tour_planner.ImportTour;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

public interface MapAPIService {

    String queryDistance();
    String queryTime();
    CompletableFuture<InputStream> queryMap();
}
