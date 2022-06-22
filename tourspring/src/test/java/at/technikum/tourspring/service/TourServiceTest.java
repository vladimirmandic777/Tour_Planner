package at.technikum.tourspring.service;

import at.technikum.tourspring.model.Tour;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TourServiceTest {

    @Autowired
    private TourService tourService;

    @BeforeAll
    @Test
    public void addRepoTest() {
        Tour tour = Tour.builder().id(1).name("Test").description("test").fromDestination("Austria").toDestination("Vienna")
                .transport("Car").distance(10).estimatedTime("10").routeInformation("Ja").build();
        Tour tour1 = Tour.builder().id(2).name("HAllo").description("test").fromDestination("Austria").toDestination("Graz")
                .transport("Bike").distance(10).estimatedTime("10").routeInformation("Ja").build();

        Tour a = tourService.createTour(tour);
        Tour aa = tourService.createTour(tour1);

        Assertions.assertNotNull(tourService.findbyId(tour.getId()));
    }

    @Test
    public void findTourTest() {
        Assertions.assertNotNull(tourService.findbyId(1));
    }

}
