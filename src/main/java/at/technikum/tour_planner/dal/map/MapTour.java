package at.technikum.tour_planner.dal.map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapTour {

    private String setup;
    private String delivery;

    public String getSetup() {
        return setup;
    }

    public String getDelivery() {
        return delivery;
    }
}
