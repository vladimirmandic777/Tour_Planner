package at.technikum.tour_planner.dal;

import at.technikum.tour_planner.controller.LogHttpClient;
import at.technikum.tour_planner.controller.TourHttpClient;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import at.technikum.tour_planner.model.TourFx;
import at.technikum.tour_planner.model.TourLog;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TourLogDao implements DaoLog<TourLog> {

    private List<TourLog> tourLogList = new ArrayList<>();

    private LogHttpClient httpClient = new LogHttpClient();


    public TourLogDao() {
        tourLogList.addAll(getAll());
    }

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(TourFxDao.class);


    @Override
    public List<TourLog> getAll() {
        return httpClient.getAll();
    }

    @Override
    public List<TourLog> getLog(int id) {
        return httpClient.getLog(id);
    }

    @Override
    public TourLog create() {
        var tourLog = new TourLog(getNewID(), new Date(), "", 0, new Date(), 0, 0);
        tourLogList.add(tourLog);
        return tourLog;
    }

    private int getNewID() {
        int newId = 1;
        if (!tourLogList.isEmpty()) {
            tourLogList.sort(Comparator.comparing(TourLog::getId).reversed());
            newId = tourLogList.get(0).getId() + 1;
        }
        return newId;
    }

    @Override
    public void update(TourLog tourLog, List<?> params) {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        try {
            logger.info(params.toString());
            tourLog.setDate(sdf.parse(Objects.requireNonNull(params.get(1).toString())));
            tourLog.setComment(Objects.requireNonNull(params.get(2), "description cannot be null").toString());
            tourLog.setDifficulty(Integer.parseInt(Objects.requireNonNull(params.get(3).toString())));
            tourLog.setTime(sdf.parse(Objects.requireNonNull(params.get(4).toString())));
            tourLog.setRating(Integer.parseInt(Objects.requireNonNull(params.get(5).toString())));
            tourLog.setTourFx(Objects.requireNonNull((TourFx) params.get(6)).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TourLog tourFx) {
        httpClient.update(tourFx);
    }


    @Override
    public void delete(TourLog tourLog) {
        httpClient.delete(tourLog);
    }
}
