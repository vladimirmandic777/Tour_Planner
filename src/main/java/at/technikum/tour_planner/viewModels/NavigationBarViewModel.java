package at.technikum.tour_planner.viewModels;

import at.technikum.tour_planner.ImportTour.ExportTour;
import at.technikum.tour_planner.ImportTour.ImportTour;
import at.technikum.tour_planner.ImportTour.ReportService;
import at.technikum.tour_planner.model.TourFx;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class NavigationBarViewModel {
    private final ReportService reportService;

    private final ExportTour exportTour;

    private final ImportTour importTour;

    private TourFx tourFx;


    public NavigationBarViewModel(ReportService reportService, ExportTour exportTour, ImportTour importTour) {
        this.reportService = reportService;
        this.exportTour = exportTour;
        this.importTour = importTour;
    }

    public void generateReport() {
        reportService.generateReport();
    }

    public void generateTourReport() {
        reportService.generateTourReport(this.tourFx);
    }

    public void setTourModel(TourFx tourFx) {
        this.tourFx = tourFx;
    }

    public void generateExport() {
        exportTour.exportTour();
    }


    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));

        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            importTour.importTour(selectedFile);
        }
    }
}
