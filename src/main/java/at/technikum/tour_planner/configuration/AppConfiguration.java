package at.technikum.tour_planner.configuration;

import lombok.Builder;
import lombok.Data;

import java.util.Locale;
import java.util.Properties;

@Data
@Builder
public class AppConfiguration {
    private String apiKey;

    public static AppConfiguration fromProperties(Properties appProps) {
        return AppConfiguration.builder()
                .apiKey(appProps.getProperty("key"))
                .build();
    }
}
