package at.technikum.tourspring.controller;

import at.technikum.tourspring.model.TourLog;
import at.technikum.tourspring.service.TourLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/log")
public class TourLogRestController {

    @Autowired
    private TourLogService tourService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<TourLog> findAll() {
        return tourService.findAll();
    }

    @GetMapping(path = "/{logId}")
    @ResponseBody
    public Optional<TourLog> findById(@PathVariable(name = "logId") int id) {
        return tourService.findbyId(id);
    }

    @PostMapping(path = "/create")
    public TourLog createTour(TourLog log) {
        return tourService.createTourLog(log);
    }

    @DeleteMapping(path = "/delete")
    public void deleteTour(TourLog log) {
        tourService.deleteTourLog(log);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteTour(@PathVariable(name = "id") int id) {
        tourService.deleteTourLog(tourService.findbyId(id).get());
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTourD(@RequestBody TourLog log) {
        tourService.updateById(log);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
