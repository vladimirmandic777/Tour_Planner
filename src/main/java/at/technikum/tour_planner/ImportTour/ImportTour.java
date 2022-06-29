package at.technikum.tour_planner.ImportTour;

import at.technikum.tour_planner.dal.Dao;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import at.technikum.tour_planner.model.TourFx;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class ImportTour implements ImportTourService {

    private final Dao<TourFx> mediaItemDao;

    public ImportTour(Dao<TourFx> tourDao) {
        this.mediaItemDao = tourDao;
    }

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(ImportTour.class);

    @Override
    public void importTour(File selectedFile) {
        try (Reader reader = new BufferedReader(new FileReader(selectedFile))) {
            CsvToBean csvReader = new CsvToBeanBuilder(reader)
                    .withType(TourFx.class)
                    .withSeparator(';')
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

            List<TourFx> results = csvReader.parse();
            results.forEach(mediaItemDao::addTour);

            logger.info("Tour import successful of:" + results.size() + " on:" + LocalDate.now());

        } catch (IOException e) {
            logger.error("Something went wrong while importing: " + e.getMessage());
        }

    }
}
