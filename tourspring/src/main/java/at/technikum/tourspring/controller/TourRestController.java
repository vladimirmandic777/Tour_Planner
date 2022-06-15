package at.technikum.tourspring.controller;

import at.technikum.tourspring.model.Tour;
import at.technikum.tourspring.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Tour createPlayer(Tour tour) {
        return tourService.createTour(tour);
    }

    @DeleteMapping(path = "/delete")
    public void deletePlayer(Tour tour) {
        tourService.deleteTour(tour);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deletePlayer(@PathVariable(name = "id") int id) {
        tourService.deleteById(id);
    }


    @PutMapping(path = "/update/{tour}")
    public void updatePlayer(@PathVariable(name = "tour") Tour tour) {
        tourService.updateById(tour);
    }

}
