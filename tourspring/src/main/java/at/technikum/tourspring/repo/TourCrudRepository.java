package at.technikum.tourspring.repo;

import at.technikum.tourspring.model.Tour;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TourCrudRepository extends JpaRepository<Tour, Integer> {
}
