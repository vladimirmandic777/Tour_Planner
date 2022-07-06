package at.technikum.tour_planner.controllers;

import at.technikum.tour_planner.ImportTour.ImportTour;
import at.technikum.tour_planner.ImportTour.ExportTour;
import at.technikum.tour_planner.ImportTour.PDFListReportService;
import at.technikum.tour_planner.dal.DAL;
import at.technikum.tour_planner.dal.DALLOG;
import at.technikum.tour_planner.viewModels.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Log4j2
public class ControllerFactory {
    private final MainWindowViewModel mainWindowViewModel;
    private final NavigationBarViewModel navigationBarViewModel;
    private final TourListViewModel tourListViewModel;
    private final TourDetailViewModel tourDetailViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final TourLogViewModel tourLogViewModel;

    public ControllerFactory() {
        navigationBarViewModel = new NavigationBarViewModel(new PDFListReportService(DAL.getInstance().tourDao()), new ExportTour(DAL.getInstance().tourDao()), new ImportTour(DAL.getInstance().tourDao()));
        tourListViewModel = new TourListViewModel(DAL.getInstance().tourDao());
        tourDetailViewModel = new TourDetailViewModel();
        searchBarViewModel = new SearchBarViewModel();
        tourLogViewModel = new TourLogViewModel(DALLOG.getInstance().tourLogDao());
        mainWindowViewModel = new MainWindowViewModel(navigationBarViewModel, searchBarViewModel, tourListViewModel, tourDetailViewModel, tourLogViewModel);

        checkFolder();
    }

    private void checkFolder() {
        String[] folders = {"target/res", "target/res/images", "target/res/logs", "target/res/csv", "target/res/PDF"};
        for (String folder : folders) {
            Path of = Path.of(folder);
            if (!Files.exists(of)) {
                try {
                    Files.createDirectories(of);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        log.info("Folders created");
    }

    public Object create(Class<?> controllerClass) {
        if (controllerClass == MainWindowController.class) {
            return new MainWindowController(mainWindowViewModel);
        } else if (controllerClass == NavigationBarController.class) {
            return new NavigationBarController(navigationBarViewModel);
        } else if (controllerClass == TourListController.class) {
            return new TourListController(tourListViewModel);
        } else if (controllerClass == TourDetailController.class) {
            return new TourDetailController(tourDetailViewModel);
        } else if (controllerClass == SearchBarController.class) {
            return new SearchBarController(searchBarViewModel);
        } else if (controllerClass == TourLogController.class) {
            return new TourLogController(tourLogViewModel);
        } else {
            log.error("Unknown controller class: " + controllerClass.getName());
            throw new IllegalArgumentException("Unknown controller class: " + controllerClass.getName());
        }
    }

    private static ControllerFactory instance = new ControllerFactory();

    public static ControllerFactory getInstance() {
        return instance;
    }

}
