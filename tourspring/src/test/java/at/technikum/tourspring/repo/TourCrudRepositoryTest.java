package at.technikum.tourspring.repo;

import at.technikum.tourspring.model.Tour;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;


@SpringBootTest
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
public class TourCrudRepositoryTest {

    @Autowired
    private TourCrudRepository tourCrudRepository;

    @Test
    public void addRepoTest()
    {
        Tour tour = Tour.builder().id(1).name("Test").description("test").fromDestination("Austria").toDestination("Vienna")
                .transport("Car").distance(10).estimatedTime(10).routeInformation("Ja").build();

        Tour a = tourCrudRepository.save(tour);

        Assertions.assertNotNull(tourCrudRepository.findById(tour.getId()));
    }
}
