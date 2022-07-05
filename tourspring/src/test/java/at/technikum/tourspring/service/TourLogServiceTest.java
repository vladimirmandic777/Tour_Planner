package at.technikum.tourspring.service;

import at.technikum.tourspring.model.Tour;
import at.technikum.tourspring.model.TourLog;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TourLogServiceTest {

    @Autowired
    private TourLogService logService;

    @BeforeAll
    @Test
    public void addLogToRepoTest() {
        Tour tour = Tour.builder().id(1).name("Test").description("test").fromDestination("Austria").toDestination("Vienna")
                .transport("Car").distance(10).estimatedTime("10").routeInformation("Ja").build();

        TourLog log = TourLog.builder().id(1).tour(tour).date(new Date()).comment("Log Test").difficulty(1).time(new Date())
                .rating(1).build();

        logService.createTourLog(log);

        Assertions.assertNotNull(logService.findbyId(log.getId()));
    }

    @Test
    public void findLogTest() {
        Assertions.assertNotNull(logService.findbyId(1));
    }


}
