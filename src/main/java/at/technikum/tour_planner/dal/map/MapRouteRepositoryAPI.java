package at.technikum.tour_planner.dal.map;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface MapRouteRepositoryAPI {

    //String queryMap();
    Map<String, Map<String, Double>> getBoundingBox() throws IOException;
    String getDistance() throws IOException;
    String getTime() throws IOException;

    String getSessionId() throws IOException;
    CompletableFuture<InputStream> getMap();
}
