package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.ImportTour.ExportTour;
import at.technikum.tour_planner.ImportTour.ReportService;
import at.technikum.tour_planner.model.TourFx;

public class NavigationBarViewModel {
    private final ReportService reportService;

    private final ExportTour exportTour;
    private TourFx tourFx;


    public NavigationBarViewModel(ReportService reportService, ExportTour exportTour) {
        this.reportService = reportService;
        this.exportTour = exportTour;
    }
    public void generateReport() {
        reportService.generateReport();
    }

    public void generateTourReport(){reportService.generateTourReport(this.tourFx);}
    public void setTourModel(TourFx tourFx) {
        this.tourFx = tourFx;
    }

    public void generateExport() {
        exportTour.exportTour();
    }
}
