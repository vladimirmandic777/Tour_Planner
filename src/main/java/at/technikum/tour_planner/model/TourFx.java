package at.technikum.tour_planner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourFx implements Serializable {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "fromDestination")
    private String fromDestination;

    @JsonProperty(value = "toDestination")
    private String toDestination;

    @JsonProperty(value = "transport")
    private String transport;

    @JsonProperty(value = "distance")
    private int distance;

    @JsonProperty(value = "estimatedTime")
    private int estimatedTime;

    @JsonProperty(value = "routeInformation")
    private String routeInformation;

    @Override
    public String toString() {
        return name;
    }

}

