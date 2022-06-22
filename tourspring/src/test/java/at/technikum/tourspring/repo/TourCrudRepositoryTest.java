package at.technikum.tourspring.repo;

import at.technikum.tourspring.model.Tour;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;

import java.util.Optional;


@SpringBootTest
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
public class TourCrudRepositoryTest {

    @Autowired
    private TourCrudRepository tourCrudRepository;


    @BeforeAll
    @Test
    public void addRepoTest() {
        Tour tour = Tour.builder().id(1).name("Test").description("test").fromDestination("Austria").toDestination("Vienna")
                .transport("Car").distance(10).estimatedTime("10").routeInformation("Ja").build();
        Tour tour1 = Tour.builder().id(2).name("HAllo").description("test").fromDestination("Austria").toDestination("Graz")
                .transport("Bike").distance(10).estimatedTime("10").routeInformation("Ja").build();

        Tour a = tourCrudRepository.save(tour);
        Tour aa = tourCrudRepository.save(tour1);

        Assertions.assertNotNull(tourCrudRepository.findById(tour.getId()));
    }

    @Test
    public void findTourTest() {
        Assertions.assertNotNull(tourCrudRepository.findById(1));
    }

    @Test
    public void removeTour() {
        tourCrudRepository.delete(tourCrudRepository.getById(1));
        Assertions.assertEquals(tourCrudRepository.findById(1), Optional.empty());
    }
}
