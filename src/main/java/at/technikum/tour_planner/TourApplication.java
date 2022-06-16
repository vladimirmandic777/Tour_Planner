package at.technikum.tour_planner;

import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;



public class TourApplication extends Application {
    public static void main(String[] args) {
        launch();
    }
    private static final ILoggerWrapper logger = LoggerFactory.getLogger();

    @Override
    public void start(Stage primaryStage) throws IOException {
        logger.debug("This is a debug message.");
        logger.fatal("This is a fatal message.");
        logger.warn("This is a warning message.");
        logger.error("This is an error message.");
        logger.info("This is an info message.");



        Parent root = FXMLDependencyInjection.load("MainWindow.fxml", Locale.GERMAN);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tour Planner");
        primaryStage.show();

    }



}