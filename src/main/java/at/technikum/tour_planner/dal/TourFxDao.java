package at.technikum.tour_planner.dal;

import at.technikum.tour_planner.BAL.MapAPIServiceImpl;
import at.technikum.tour_planner.controller.TourHttpClient;
import at.technikum.tour_planner.model.TourFx;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class TourFxDao implements Dao<TourFx>  {
    private List<TourFx> tourItemsList = new ArrayList<>();

    private TourHttpClient httpClient = new TourHttpClient();

    public TourFxDao() {
        tourItemsList.addAll(httpClient.getAll());
    }

    @Override
    public Optional<TourFx> get(int id) {
        return Optional.ofNullable(tourItemsList.get(id));
    }

    @Override
    public List<TourFx> getAll() {
        return tourItemsList;
    }

    @Override
    public TourFx create() {
        var tour = new TourFx(getNewID(), "New Tour","","","","",0,"0","",null);
        tourItemsList.add(tour);
        return tour;
    }

    private int getNewID() {
        int newId = 1;
        if (tourItemsList.size() >=1) {
            tourItemsList.sort(Comparator.comparing(TourFx::getId).reversed());
            newId = tourItemsList.get(0).getId()+1;
        }
        return newId;
    }

    @Override
    public void update(TourFx tourFx, List<?> params) {
        System.out.println(params);
        tourFx.setName(Objects.requireNonNull(params.get(1), "Name cannot be null").toString());
        tourFx.setDescription(Objects.requireNonNull(params.get(2), "description cannot be null").toString());
        tourFx.setFromDestination(Objects.requireNonNull(params.get(3), "setFromDestination cannot be null").toString());
        tourFx.setToDestination(Objects.requireNonNull(params.get(4), "toDestination cannot be null").toString());
        tourFx.setTransport(Objects.requireNonNull(params.get(5), "transport cannot be null").toString());
        tourFx.setDistance(Integer.parseInt(params.get(6).toString()));
        tourFx.setEstimatedTime(params.get(7).toString());
        tourFx.setRouteInformation(Objects.requireNonNull(params.get(8), "transport cannot be null").toString());
    }

    @Override
    public void update(TourFx tourFx) throws URISyntaxException, IOException {
        httpClient.update(tourFx);
    }

    @Override
    public void delete(TourFx tourFx) {
        httpClient.delete(tourFx);
    }
}
