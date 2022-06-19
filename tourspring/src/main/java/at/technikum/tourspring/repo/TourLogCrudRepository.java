package at.technikum.tourspring.repo;

import at.technikum.tourspring.model.TourLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLogCrudRepository extends JpaRepository<TourLog, Integer> {
}
