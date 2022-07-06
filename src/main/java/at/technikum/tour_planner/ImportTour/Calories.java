package at.technikum.tour_planner.ImportTour;

public class Calories implements CaloriesService {
    @Override
    public String calculateCalories(String transportation, int distance) {
        //walking 0, running 1, cycling 2
        int calories = 0;
        if (transportation.equals("Walking")) {
            calories = (int) (distance * 76);
        } else if (transportation.equals("Running")) {
            calories = (int) (distance * 155);
        } else if (transportation.equals("Bike")) {
            calories = (int) (distance * 84);
        }
        return String.valueOf(calories);
    }
}
