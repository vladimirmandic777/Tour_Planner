package at.technikum.tour_planner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

public class TourFx {

    @Getter
    @Setter
    @JsonProperty(value = "id")
    private int id;

    @Getter
    @Setter
    @JsonProperty(value = "name")
    private String name;

    @Getter
    @Setter
    @JsonProperty(value = "description")
    private String description;

    @Getter
    @Setter
    @JsonProperty(value = "fromDestination")
    private String fromDestination;

    @Getter
    @Setter
    @JsonProperty(value = "toDestination")
    private String toDestination;

    @Getter
    @Setter
    @JsonProperty(value = "transport")
    private String transport;

    @Getter
    @Setter
    @JsonProperty(value = "distance")
    private int distance;

    @Getter
    @Setter
    @JsonProperty(value = "estimatedTime")
    private int estimatedTime;

    @Getter
    @Setter
    @JsonProperty(value = "routeInformation")
    private String routeInformation;

    public TourFx() {
    }
}

