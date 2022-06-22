package at.technikum.tour_planner.dal;

import at.technikum.tour_planner.controller.TourHttpClient;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.model.TourLog;

import java.net.URISyntaxException;
import java.util.*;

public class TourLogDao  implements Dao<TourLog> {

    private List<TourLog> tourLogList = new ArrayList<>();

    private TourHttpClient httpClient = new TourHttpClient();


    public TourLogDao(TourFx tourFx) {
        tourLogList.addAll(httpClient.getLog(tourFx.getId()));
    }

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(TourFxDao.class);


    @Override
    public Optional<TourLog> get(int id) {
        return Optional.ofNullable(tourLogList.get(id));
    }

    @Override
    public List<TourLog> getAll() {
        return tourLogList;
    }

    @Override
    public TourLog create() {
        var tourLog = new TourLog(getNewID(),new Date(), "", 0, new Date(), 0);
        tourLogList.add(tourLog);
        return tourLog;
    }

    private int getNewID() {
        int newId = 1;
        if (!tourLogList.isEmpty()) {
               tourLogList.sort(Comparator.comparing(TourLog::getId).reversed());
               newId = tourLogList.get(0).getId()+1;
        }
        return newId;
    }

    @Override
    public void update(TourLog tourLog, List<?> params) {

        //TODO mach ma schon
//        logger.info(params.toString());
//        tourLog.setDate(Objects.requireNonNull(params.get(1), "Name cannot be null").toString());
//        tourLog.setComment(Objects.requireNonNull(params.get(2), "description cannot be null").toString());
//        tourLog.setDifficulty(Objects.requireNonNull(params.get(3), "setFromDestination cannot be null").toString());
//        tourLog.setTime(Objects.requireNonNull(params.get(5), "transport cannot be null").toString());
    }

    //TODO mach ma schon
    @Override
    public void update(TourLog tourFx) {
//        try {
//            httpClient.update(tourFx);
//        } catch (URISyntaxException e) {
//            logger.error(e.getMessage());
//        }
//    }


    }

    @Override
    public void delete(TourLog tourLog) {

    }
}
