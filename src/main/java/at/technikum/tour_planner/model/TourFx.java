package at.technikum.tour_planner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourFx implements Serializable {

    @CsvBindByPosition(position = 0)
    @JsonProperty(value = "id")
    private int id;

    @CsvBindByPosition(position = 1)
    @JsonProperty(value = "name")
    private String name;

    @CsvBindByPosition(position = 2)
    @JsonProperty(value = "description")
    private String description;

    @CsvBindByPosition(position = 3)
    @JsonProperty(value = "fromDestination")
    private String fromDestination;

    @CsvBindByPosition(position = 4)
    @JsonProperty(value = "toDestination")
    private String toDestination;

    @CsvBindByPosition(position = 5)
    @JsonProperty(value = "transport")
    private String transport;

    @CsvBindByPosition(position = 6)
    @JsonProperty(value = "distance")
    private int distance;

    @CsvBindByPosition(position = 7)
    @JsonProperty(value = "estimatedTime")
    private String estimatedTime;

    @CsvBindByPosition(position = 8)
    @JsonProperty(value = "routeInformation")
    private String routeInformation;

    @JsonProperty(value = "logs")
    private List tour;

    @Override
    public String toString() {
        return name;
    }
}

