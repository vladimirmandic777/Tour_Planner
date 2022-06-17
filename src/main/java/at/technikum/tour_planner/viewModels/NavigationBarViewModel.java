package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.BAL.ReportService;

public class NavigationBarViewModel {
    private final ReportService reportService;


    public NavigationBarViewModel(ReportService reportService) {
        this.reportService = reportService;
    }
    public void generateReport() {
        reportService.generateReport();
    }

}
