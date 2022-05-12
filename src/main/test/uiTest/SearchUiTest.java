package uiTest;

import at.technikum.tour_planner.FXMLDependencyInjection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.assertions.api.Assertions;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    void testSearchWithBar(FxRobot robot) {
        robot.lookup("#searchTextField").queryTextInputControl().setText("H");
        robot.clickOn("#searchButton");
        assertThat(robot.lookup("#listView").queryListView()).hasExactlyNumItems(1);
    }

    @Test
    void testSearchWithNoValue(FxRobot robot) {
        robot.lookup("#searchTextField").queryTextInputControl().setText("Vladi");
        robot.clickOn("#searchButton");
        assertThat(robot.lookup("#listView").queryListView()).hasExactlyNumItems(0);
    }

    @Test
    void testSearchWithDeleteSearchInput(FxRobot robot) {
        robot.lookup("#searchTextField").queryTextInputControl().setText("Vladi");
        robot.clickOn("#searchButton");
        robot.clickOn("#clearButton");
        assertThat(robot.lookup("#listView").queryListView()).hasExactlyNumItems(2);
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