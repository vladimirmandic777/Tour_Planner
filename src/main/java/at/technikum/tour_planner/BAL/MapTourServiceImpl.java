package at.technikum.tour_planner.BAL;

import at.technikum.tour_planner.dal.map.MapTourRepository;

public class MapTourServiceImpl implements MapTourService {

    private final MapTourRepository jokeRepository;

    public MapTourServiceImpl(MapTourRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public String queryMap() {
        return null;
    }
}
