package service;
import at.technikum.tour_planner.dal.map.*;


import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class MapQuestTest {

    private MapRouteRepository mapRouteRepository = new MapRouteRepository("New+York,NY", "Washington,DC");
    private static final ILoggerWrapper logger = LoggerFactory.getLogger();

    public MapQuestTest() throws IOException {
    }


    @Test
     void testBoundingBox() throws IOException {

        assertNotNull(mapRouteRepository.getBoundingBox());
        assertEquals(38.892063, mapRouteRepository.getBoundingBox().get("lr").get("lat"));
        assertEquals(-74.004807, mapRouteRepository.getBoundingBox().get("lr").get("lng"));

        assertEquals(40.731976, mapRouteRepository.getBoundingBox().get("ul").get("lat"));
        assertEquals(-77.019913, mapRouteRepository.getBoundingBox().get("ul").get("lng"));

        assertNotNull(mapRouteRepository.getSessionId());
        logger.info("SessionId: " + mapRouteRepository.getSessionId());

    }
    @Test
    void testDistance() throws IOException {
        assertEquals("225.993", mapRouteRepository.getDistance());
        logger.info("Distance: " + mapRouteRepository.getDistance());
    }

    @Test
    void testMap() throws IOException {
        MapRouteRepository mapRouteRepository2 = new MapRouteRepository("Vienna", "Bratislava");
        assertNotNull(mapRouteRepository2.getMap());
        logger.info("Map: " + mapRouteRepository2.getMap().toString());
        FileOutputStream fos = new FileOutputStream("image2.jpg");
        try {
            IOUtils.copy(mapRouteRepository2.getMap(), fos);

        }
        finally {
            fos.close();
        }
    }

}