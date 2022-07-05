package at.technikum.tourspring.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TourLogTest {

    @Test
    public void buildLog() {
        TourLog log = TourLog.builder().id(1).date(new Date()).comment("Log Test").difficulty(1).time(new Date())
                .rating(1).build();

        Assertions.assertThat(log.getId()).isNotNull();
    }
}
