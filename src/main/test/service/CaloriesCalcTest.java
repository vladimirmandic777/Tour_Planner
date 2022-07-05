package service;
import at.technikum.tour_planner.ImportTour.Calories;
import at.technikum.tour_planner.ImportTour.SearchService;
import at.technikum.tour_planner.viewModels.TourDetailViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;


public class CaloriesCalcTest {

    @Test
    void testCaloriesWalking() {
        Calories calories = new Calories();
        assertEquals("760", calories.calculateCalories("Walking", 10));
    }
    @Test
    void testCaloriesRunning() {
        Calories calories = new Calories();
        assertEquals("1550", calories.calculateCalories("Running", 10));
    }
    @Test
    void testCaloriesCycling() {
        Calories calories = new Calories();
        assertEquals("840", calories.calculateCalories("Bike", 10));
    }
}
