package at.technikum.tour_planner.main;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Data;
public class Tour {
    @Getter
    @Setter
    private String id;
    private String name;
    private String description;
    private String from;
    private String to;
    private String transport;
    private int distance;
    private int estimatedTime;
    private String routeInformation;

    public Tour(String name, String description, String from, String to, String transport, int distance, int estimatedTime, String routeInformation) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.transport = transport;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.routeInformation = routeInformation;
    }


}
