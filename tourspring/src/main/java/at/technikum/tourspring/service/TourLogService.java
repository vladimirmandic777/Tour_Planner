package at.technikum.tourspring.service;
import at.technikum.tourspring.model.TourLog;
import at.technikum.tourspring.repo.TourLogCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourLogService {

    @Autowired
    public TourLogCrudRepository logCrudRepository;

    public Iterable<TourLog> findAll() {
        return logCrudRepository.findAll();
    }

    public Optional<TourLog> findbyId(int id) {
        return logCrudRepository.findById(id);
    }

    public TourLog createTourLog(TourLog TourLog) {
        return logCrudRepository.save(TourLog);
    }

    public void deleteTourLog(TourLog TourLog) {
        logCrudRepository.delete(TourLog);
    }

    public void updateById(TourLog TourLog) {
        logCrudRepository.saveAndFlush(TourLog);
    }

    public List<TourLog> findTourLogsByTourId(int id) {
        return logCrudRepository.findTourLogsByTourId(id);
    }
}
