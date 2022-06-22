package at.technikum.tourspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "log")
@Entity
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class TourLog implements Serializable {

    @Id
    @Column(name = "logId")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_id", nullable = false)
    @JsonIgnore
    private Tour tour;

    @Column(name = "date")
    private Date date;

    @Column(name = "comment")
    private String comment;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "time")
    private Date time; // oder eher als String?

    @Column(name = "rating")
    private int rating;

    @Column(name = "idFx", nullable=true)
    private Integer idFx;

}


/*
popularity (derived from number of logs)
o child-friendliness (derived from recorded difficulty values, total times and distance)

 */