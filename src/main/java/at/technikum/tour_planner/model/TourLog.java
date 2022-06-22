package at.technikum.tour_planner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourLog {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "date")
    private Date date;

    @JsonProperty(value = "comment")
    private String comment;

    @JsonProperty(value = "difficulty")
    private int difficulty;

    @JsonProperty(value = "time")
    private Date time;

    @JsonProperty(value = "rating")
    private int rating;

    @JsonProperty(value = "idFx")
    private int tourFx;

}
