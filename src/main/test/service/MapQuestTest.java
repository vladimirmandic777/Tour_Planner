package service;
import at.technikum.tour_planner.BAL.MapAPIServiceImpl;
import at.technikum.tour_planner.dal.map.*;


import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import javafx.scene.image.Image;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class MapQuestTest {

    //private MapRouteRepository mapRouteRepository = new MapRouteRepository("New+York,NY", "Washington,DC");
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(MapQuestTest.class);

    public MapQuestTest() throws IOException {
    }


    @Test
     void testBoundingBox() throws IOException {
        MapRouteRepository mapRouteRepository = new MapRouteRepository("New+York,NY", "Washington,DC");
        assertNotNull(mapRouteRepository.getBoundingBox());
        assertEquals(38.892063, mapRouteRepository.getBoundingBox().get("lr").get("lat"));
        assertEquals(-74.004807, mapRouteRepository.getBoundingBox().get("lr").get("lng"));

        assertEquals(40.731976, mapRouteRepository.getBoundingBox().get("ul").get("lat"));
        assertEquals(-77.019913, mapRouteRepository.getBoundingBox().get("ul").get("lng"));

        assertNotNull(mapRouteRepository.getSessionId());
        logger.info("getBoundingBox: " + mapRouteRepository.getBoundingBox());

        logger.info("SessionId: " + mapRouteRepository.getSessionId());

    }
    @Test
    void testDistance() throws IOException {
        MapRouteRepository mapRouteRepository = new MapRouteRepository("Berlin", "Hamburg");
        assertEquals("180.006", mapRouteRepository.getDistance());
        logger.info("Distance: " + mapRouteRepository.getDistance());
    }
    @Test
    void testTime() throws IOException {
        MapRouteRepository mapRouteRepository = new MapRouteRepository("Dubrovnik", "Zadar");
        assertEquals("03:55:59", mapRouteRepository.getTime());
        logger.info("Time: " + mapRouteRepository.getTime());
    }


    @Test
    void testMap() throws IOException {
        MapAPIServiceImpl mapRouteRepository2 = new MapAPIServiceImpl("Vienna", "Bratislava");

        assertNotNull(mapRouteRepository2.queryMap());
        logger.info("Map: " + mapRouteRepository2.queryMap().toString());
        var src = "target/res/images/mapImage" + "test" + ".jpg";
        try (FileOutputStream fos = new FileOutputStream(src)) {
            IOUtils.copy(mapRouteRepository2.queryMap(), fos);
        }
    }


}