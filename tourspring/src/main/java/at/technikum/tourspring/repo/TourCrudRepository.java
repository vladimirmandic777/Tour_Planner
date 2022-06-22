package at.technikum.tourspring.repo;

import at.technikum.tourspring.model.Tour;
import at.technikum.tourspring.model.TourLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourCrudRepository extends JpaRepository<Tour, Integer> {

    @Query("SELECT AVG(e.difficulty) FROM TourLog e inner join Tour b  on e.tour.id = b.id  WHERE b.id = ?1")
    List<TourLog> test(int id);

    @Query("SELECT count(e.id) FROM TourLog e inner join Tour b  on e.tour.id = b.id  WHERE b.id = ?1")
    List<TourLog> countRating(int id);

    // Count >= 20 --> Raiting: 5
    // Count >= 15 --> Raiting: 4
    // Count >= 10 --> Raiting: 3
    // Count >= 5 --> Raiting: 2
    // Count >= 0 --> Raiting: 1
}
