package at.technikum.tour_planner.configuration;

public class AppConfigurationLoader {

    private static AppConfigurationLoader appConfigurationLoader;
    private final AppConfigurationReader appConfigurationReader;

    public AppConfigurationLoader() {
        this.appConfigurationReader = new PropertyConfigurationReader();
    }

    public static AppConfigurationLoader getInstance() {
        if (appConfigurationLoader == null) {
            appConfigurationLoader = new AppConfigurationLoader();
        }
        return appConfigurationLoader;
    }

    public AppConfiguration getAppConfiguration() {
        return appConfigurationReader.getAppConfiguration();
    }

}
