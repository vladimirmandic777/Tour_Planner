package at.technikum.tour_planner.BAL;

import at.technikum.tour_planner.model.TourFx;

public interface ReportService {
    void generateReport();
    void generateTourReport(TourFx tourfx);

}
