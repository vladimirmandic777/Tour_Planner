package at.technikum.tourspring.repo;

import at.technikum.tourspring.model.Tour;
import at.technikum.tourspring.model.TourLog;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
public class TourLogCrudRepositoryTest {

    @Autowired
    private TourCrudRepository tourCrudRepository;

    @Autowired
    private TourLogCrudRepository logCrudRepository;

    private Tour tour = Tour.builder().id(55).name("Test").description("test").fromDestination("Austria").toDestination("Vienna")
            .transport("Car").distance(10).estimatedTime(10).routeInformation("Ja").build();


    @BeforeAll
    @Test
    public void addRepoTest() {
        tourCrudRepository.save(tour);

        Assertions.assertNotNull(tourCrudRepository.findById(tour.getId()));
    }

    @Test
    public void addTourLogToTour() {
        TourLog log = TourLog.builder().id(1).tour(tour).date(new Date()).comment("Log Test").difficulty(1).time(new Date())
                .rating(1).build();

        logCrudRepository.save(log);

        Assertions.assertNotNull(logCrudRepository.findById(log.getId()));
    }

}
