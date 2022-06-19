package at.technikum.tour_planner.BAL;

import at.technikum.tour_planner.dal.map.MapRouteRepository;

public class MapTourServiceImpl implements MapTourService {

    private final MapRouteRepository jokeRepository;

    public MapTourServiceImpl(MapRouteRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public String queryMap() {
        return null;
    }
}
