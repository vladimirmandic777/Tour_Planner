package at.technikum.tourspring.controller;

import at.technikum.tourspring.model.Tour;
import at.technikum.tourspring.model.TourLog;
import at.technikum.tourspring.service.TourLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class TourLogControllerTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    public TourLogService logService;

    @Autowired
    private ObjectMapper objectMapper;

    private JacksonTester<Tour> jacksonTester;

    static Logger logger = Logger.getLogger(TourControllerTest.class.getName());

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void getTourLogByIdTest() {
        try {
            mockMvc.perform(get("/log/{id}", 1)).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void createTourLogTest() {
        try {
            Tour tour = Tour.builder().id(1).name("Test").description("test").fromDestination("Austria").toDestination("Vienna").transport("Car").distance(10).estimatedTime("new").routeInformation("Ja").build();

            TourLog log = TourLog.builder().id(1).tour(tour).date(new Date()).comment("Log Test").difficulty(1).time(new Date())
                    .rating(1).build();

            logService.createTourLog(log);

            final String jsonScore = jacksonTester.write(tour).getJson();

            mockMvc.perform(post("/tour/create").content(jsonScore).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

            verify(logService).createTourLog(log);
        } catch (Exception e) {
            logger.error(e);
        }
    }


    @Test
    public void deleteTourLogbyIdTest() {
        try {
            mockMvc.perform(delete("/tour/deleteId/{id}", 1).contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
