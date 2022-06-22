package at.technikum.tourspring.controller;

import at.technikum.tourspring.model.Tour;
import at.technikum.tourspring.service.TourService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class TourControllerTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    public TourService tourService;

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
    public void getTourByIdTest() {
        try {
            mockMvc.perform(get("/tour/{id}", 1)).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void getAllTourTest() {
        try {
            when(tourService.findAll()).thenReturn(Collections.emptyList());

            MvcResult mvcResult = mockMvc.perform(get("/tour/all").contentType(MediaType.APPLICATION_JSON)).andReturn();
            verify(tourService).findAll();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void createTourTest() {
        try {
            Tour tour = Tour.builder().id(1).name("Test").description("test").fromDestination("Austria").toDestination("Vienna").transport("Car").distance(10).estimatedTime("new").routeInformation("Ja").build();

            tourService.createTour(tour);

            final String jsonScore = jacksonTester.write(tour).getJson();

            mockMvc.perform(post("/tour/create").content(jsonScore).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

            verify(tourService).createTour(tour);
        } catch (Exception e) {
            logger.error(e);
        }
    }


    @Test
    public void deleteTourbyIdTest() {
        try {
            mockMvc.perform(delete("/tour/deleteId/{id}", 1).contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
