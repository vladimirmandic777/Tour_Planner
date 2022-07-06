package uiTest;

import at.technikum.tour_planner.FXMLDependencyInjection;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class TourListUiTest {

    @Start
    private void start(Stage stage) throws IOException {
        Parent root = FXMLDependencyInjection.load("MainWindow.fxml", Locale.GERMAN);
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }




    @Test
    void addTour(FxRobot robot) {
        int sizebefore  = robot.lookup("#listView").queryListView().getItems().size();
        robot.clickOn("#addTour");
        assertEquals(robot.lookup("#listView").queryListView().getItems().size(), sizebefore+1);
    }


    @Test
    void deleteTour(FxRobot robot) {
        robot.clickOn("#addTour");
        int sizebefore  = robot.lookup("#listView").queryListView().getItems().size();
        robot.lookup("#listView").queryListView().getSelectionModel().selectLast();
        robot.clickOn("#deleteTour");
        assertEquals(robot.lookup("#listView").queryListView().getItems().size(), sizebefore-1);
    }

}
