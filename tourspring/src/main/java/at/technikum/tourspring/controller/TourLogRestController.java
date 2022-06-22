package at.technikum.tourspring.controller;

import at.technikum.tourspring.model.TourLog;
import at.technikum.tourspring.service.TourLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/log")
public class TourLogRestController {

    @Autowired
    private TourLogService logService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<TourLog> findAll() {
        return logService.findAll();
    }

    @GetMapping(path = "/{logId}")
    @ResponseBody
    public Optional<TourLog> findById(@PathVariable(name = "logId") int id) {
        return logService.findbyId(id);
    }

    @GetMapping(path = "tour/{tourId}")
    @ResponseBody
    public List<TourLog> findByTourId(@PathVariable(name = "tourId") int id) {
        return logService.findTourLogsByTourId(id);
    }

    @PostMapping(path = "/create")
    public TourLog createTour(TourLog log) {
        return logService.createTourLog(log);
    }

    @DeleteMapping(path = "/delete")
    public void deleteTour(TourLog log) {
        logService.deleteTourLog(log);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteTour(@PathVariable(name = "id") int id) {
        logService.deleteTourLog(logService.findbyId(id).get());
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTourD(@RequestBody TourLog log) {
        logService.updateById(log);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
