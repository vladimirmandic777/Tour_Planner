package at.technikum.tourspring.service;


import at.technikum.tourspring.model.Tour;
import at.technikum.tourspring.repo.TourCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class TourService {

    @Autowired
    public TourCrudRepository tourCrudRepository;

    public Iterable<Tour> findAll() {
        return tourCrudRepository.findAll();
    }

    public Optional<Tour> findbyId(int id) {
        return tourCrudRepository.findById(id);
    }

    public Tour createTour(Tour Tour) {
        return tourCrudRepository.save(Tour);
    }

    public void deleteTour(Tour Tour) {
        tourCrudRepository.delete(Tour);
    }

    public void deleteById(int id) {
        tourCrudRepository.deleteById(id);
    }

    public void updateById(Tour tour) { tourCrudRepository.saveAndFlush(tour);}

}
