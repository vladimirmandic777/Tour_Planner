package at.technikum.tour_planner.ImportTour;

import at.technikum.tour_planner.dal.Dao;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import at.technikum.tour_planner.model.TourFx;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExportTour implements ExportTourService {

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(ExportTour.class);

    private final Dao<TourFx> mediaItemDao;

    public static final String TARGET_PDF = "target/res/csv/";

    public ExportTour(Dao<TourFx> tourFxDao) {
        this.mediaItemDao = tourFxDao;
    }

    @Override
    public void exportTour() {
        List<String[]> csvData = createCsvDataSimple();
        try (ICSVWriter writer = new CSVWriterBuilder(new FileWriter(TARGET_PDF + "TourExport" + LocalDate.now() + ".csv")).withSeparator(';').build()) {
            writer.writeAll(csvData);
            logger.info("Tour export successful of:" + csvData.size() + " on:" + LocalDate.now());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private List<String[]> createCsvDataSimple() {
        String[] header = {"id", "name", "description", "fromDestination", "toDestination", "transport", "distance", "estimatedTime", "routeInformation"};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        mediaItemDao.getAll().forEach(mediaItem -> {
            String[] data = {String.valueOf(mediaItem.getId()), mediaItem.getName(),
                    mediaItem.getDescription(), mediaItem.getFromDestination(),
                    mediaItem.getToDestination(), mediaItem.getTransport(), String.valueOf(mediaItem.getDistance()),
                    String.valueOf(mediaItem.getEstimatedTime()), mediaItem.getRouteInformation()};
            list.add(data);
        });

        return list;
    }
}
