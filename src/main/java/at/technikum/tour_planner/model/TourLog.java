package at.technikum.tour_planner.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourLog {

    private Date date;

    private String comment;

    private int difficulty;

    private Date time;

    private int rating;

}
