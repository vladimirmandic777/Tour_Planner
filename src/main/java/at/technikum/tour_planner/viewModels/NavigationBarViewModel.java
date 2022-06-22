package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.BAL.ReportService;
import at.technikum.tour_planner.model.TourFx;

public class NavigationBarViewModel {
    private final ReportService reportService;
    private TourFx tourFx;


    public NavigationBarViewModel(ReportService reportService) {
        this.reportService = reportService;
    }
    public void generateReport() {
        reportService.generateReport();
    }

    public void generateTourReport(){reportService.generateTourReport(this.tourFx);}
    public void setTourModel(TourFx tourFx) {
        this.tourFx = tourFx;
    }
}
