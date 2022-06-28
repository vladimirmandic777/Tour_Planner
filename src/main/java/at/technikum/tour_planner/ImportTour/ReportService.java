package at.technikum.tour_planner.ImportTour;

import at.technikum.tour_planner.model.TourFx;

public interface ReportService {
    void generateReport();
    void generateTourReport(TourFx tourfx);

}
