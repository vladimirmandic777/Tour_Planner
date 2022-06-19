package at.technikum.tourspring.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TourTest {

    @Test
    public void buildTour() {
        Tour tour = Tour.builder().id(1).name("Test").description("test").fromDestination("Austria").toDestination("Vienna")
                .transport("Car").distance(10).estimatedTime(10).routeInformation("Ja").build();
        Assertions.assertThat(tour.getId()).isNotNull();
    }
}
