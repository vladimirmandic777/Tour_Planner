package at.technikum.tourspring.repo;

import at.technikum.tourspring.model.TourLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TourLogCrudRepository extends JpaRepository<TourLog, Integer> {
    List<TourLog> findTourLogsByTourId(int id);
}
