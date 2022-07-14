package uiTest;

import at.technikum.tour_planner.FXMLDependencyInjection;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Locale;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class SearchUiTest {


    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage stage) throws IOException {
        Parent root = FXMLDependencyInjection.load("MainWindow.fxml", Locale.GERMAN);
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }


    @Test
    void testSearchWithDeleteSearchInput(FxRobot robot) {
        robot.lookup("#searchTextField").queryTextInputControl().setText("HALLLLLLLLLLL");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.clickOn("#clearButton");
        assertThat(robot.lookup("#searchTextField").queryTextInputControl().getText()).isEqualTo("");
    }

    @Test
    void testSearchWithNoValue(FxRobot robot) {
        robot.lookup("#searchTextField").queryTextInputControl().setText("AAAAAAAAAAAAAAAA");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        assertThat(robot.lookup("#listView").queryListView()).hasExactlyNumItems(0);
    }

    @Test
    void testSearchWithBar(FxRobot robot) {
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        assertThat(robot.lookup("#listView").queryListView()).hasExactlyNumItems(0);
        robot.clickOn("#addTour");
        assertThat(robot.lookup("#listView").queryListView()).hasExactlyNumItems(1);
        robot.clickOn("#addTour");
        robot.clickOn("#addTour");
        robot.lookup("#nameTextField").queryTextInputControl().setText("happy");
        assertThat(robot.lookup("#listView").queryListView()).hasExactlyNumItems(3);
    }


    @Test
    void testSearchLogWithBar(FxRobot robot) {
        robot.clickOn("#addTour");
        robot.clickOn("#addTourLog");

        robot.lookup("#listView").queryListView().getSelectionModel().selectLast();
        robot.lookup("#searchLogTextField").queryTextInputControl().setText("Log");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        assertThat(robot.lookup("#logTable").queryTableView()).hasExactlyNumRows(2);
    }


    /*

    @Test
    void should_contain_button_with_text(FxRobot robot) {
        Assertions.assertThat(button).hasText("click me!");
        // or (lookup by css id):
        Assertions.assertThat(robot.lookup("#myButton").queryAs(Button.class)).hasText("click me!");
        // or (lookup by css class):
        Assertions.assertThat(robot.lookup(".button").queryAs(Button.class)).hasText("click me!");
        // or (query specific type):
        Assertions.assertThat(robot.lookup(".button").queryButton()).hasText("click me!");
    }


    @Test
    void when_button_is_clicked_text_changes(FxRobot robot) {
        // when:
        robot.clickOn(".button");

        // then:
        Assertions.assertThat(button).hasText("clicked!");
        // or (lookup by css id):
        Assertions.assertThat(robot.lookup("#myButton").queryAs(Button.class)).hasText("clicked!");
        // or (lookup by css class):
        Assertions.assertThat(robot.lookup(".button").queryAs(Button.class)).hasText("clicked!");
        // or (query specific type)
        Assertions.assertThat(robot.lookup(".button").queryButton()).hasText("clicked!");
    }
    */
}