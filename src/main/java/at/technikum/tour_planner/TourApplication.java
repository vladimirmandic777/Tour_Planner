package at.technikum.tour_planner;

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

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLDependencyInjection.load("MainWindow.fxml", Locale.GERMAN);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tour Planner");
        primaryStage.show();

    }



}