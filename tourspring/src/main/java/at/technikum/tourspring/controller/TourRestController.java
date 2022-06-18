package at.technikum.tourspring.controller;

import at.technikum.tourspring.model.Tour;
import at.technikum.tourspring.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tour")
public class TourRestController {

    @Autowired
    private TourService tourService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Tour> findAll() {
        return tourService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public Optional<Tour> findById(@PathVariable(name = "id") int id) {
        return tourService.findbyId(id);
    }

    @PostMapping(path = "/create")
    public Tour createTour(Tour tour) {
        return tourService.createTour(tour);
    }

    @DeleteMapping(path = "/delete")
    public void deleteTour(Tour tour) {
        tourService.deleteTour(tour);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteTour(@PathVariable(name = "id") int id) {
        tourService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTourD(@RequestBody Tour tour) {
        tourService.updateById(tour);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
