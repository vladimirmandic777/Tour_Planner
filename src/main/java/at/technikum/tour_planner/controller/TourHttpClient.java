package at.technikum.tour_planner.controller;

import at.technikum.tour_planner.BAL.MapAPIServiceImpl;
import at.technikum.tour_planner.dal.Dao;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import at.technikum.tour_planner.model.TourFx;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



public class TourHttpClient implements Dao<TourFx> {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger();


    private HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    @Override
    public Optional<TourFx> get(int id) {
        try {
            // Create a request
            HttpRequest request = getHttpRequest(Integer.toString(id));
            HttpResponse<String> response = getHttpResponse(request);

            return Optional.of(objectMapper.readValue(response.body(), new TypeReference<List<TourFx>>() {
            }).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public List<TourFx> getAll() {
        try {
            // Create a request
            HttpRequest request = getHttpRequest("all");

            // Execute the request
            HttpResponse<String> response = getHttpResponse(request);

            return objectMapper.readValue(response.body(), new TypeReference<List<TourFx>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public TourFx create() {
        return null;
    }

    @Override
    public void update(TourFx tourFx, List<?> params) {

    }

    @Override
    public void update(TourFx tourFx) throws URISyntaxException, IOException {
        //MapAPIServiceImpl mapAPIService = new MapAPIServiceImpl("New+York,NY", "Washington,DC");
        MapAPIServiceImpl mapAPIService = new MapAPIServiceImpl(tourFx.getFromDestination(), tourFx.getToDestination());

        //get data from mapAPI
        tourFx.setDistance((int) Double.parseDouble(mapAPIService.queryDistance()));
        tourFx.setEstimatedTime(mapAPIService.queryTime());
        var src = "src/main/resources/images/mapImage" + String.valueOf(tourFx.getId()) + ".jpg";

        FileOutputStream fos = new FileOutputStream(src);
        try {
            IOUtils.copy(mapAPIService.queryMap(), fos);
        }
        finally {
            fos.close();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody;
        try {
            requestBody = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(tourFx);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/tour/update"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = getHttpResponse(request);

        // Print the response body
        logger.debug("Response body:");
        logger.debug(response.body());
        System.out.println(response.body());
    }

    @Override
    public void delete(TourFx tourFx) {
        try {
            // Create a request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/tour/delete/" + tourFx.getId()))
                    .headers("Content-Type", "application/json")
                    .DELETE()
                    .build();

            // Execute the request
            HttpResponse<String> response = getHttpResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private HttpRequest getHttpRequest(String s) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/tour/" + s))
                .header("User-Agent", "Java 11 HttpClient Bot")
                .GET()
                .build();
    }

    private HttpResponse<String> getHttpResponse(HttpRequest request)  {
        try {
            // Execute the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the status code
            logger.debug("Status code: " + response.statusCode());

            // Print the response headers
            response.headers().map().forEach((k, v) -> logger.debug(k + ": " + v));

            // Print the response body
            logger.debug("Response body:");
            logger.debug(response.body());
            return response;
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
