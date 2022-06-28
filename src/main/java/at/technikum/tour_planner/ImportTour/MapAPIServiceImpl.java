package at.technikum.tour_planner.ImportTour;

import at.technikum.tour_planner.dal.map.MapRouteRepository;

import java.io.IOException;
import java.io.InputStream;

public class MapAPIServiceImpl implements MapAPIService {

    private final MapRouteRepository mapRouteRepository;


    public MapAPIServiceImpl(String from, String to) {
        try {
            this.mapRouteRepository = new MapRouteRepository(from, to);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String queryDistance() {
        try {
            return this.mapRouteRepository.getDistance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String queryTime() {
        try {
            return this.mapRouteRepository.getTime();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public InputStream queryMap(){
        return this.mapRouteRepository.getMap();
    }
}
