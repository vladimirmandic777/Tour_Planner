package at.technikum.tour_planner.dal.map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)

public class RouteObject {
    private String distance;
    private String formattedTime;
    private Map<String, Map<String, Double>> boundingBox;
    private String sessionId;


    public String getDistance() {
        return this.distance;
    }

    public String getFormattedTime() {
        return this.formattedTime;
    }

    public Map<String, Map<String, Double>> getBoundingBox() {
        return this.boundingBox;
    }


    public String getSessionId() {
        return this.sessionId;
    }

}
