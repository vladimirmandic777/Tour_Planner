package at.technikum.tour_planner.controller;

import at.technikum.tour_planner.model.TourFx;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.List;


public class TourHttpClient {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(TourHttpClient.class);

    public List<TourFx> getToursbyRequest() {
        try {
            // Create a new HttpClient
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .connectTimeout(Duration.ofSeconds(5))
                    .build();

            // Create a request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/tour/all"))
                    .header("User-Agent", "Java 11 HttpClient Bot")
                    .GET()
                    .build();

            // Execute the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the status code
            logger.info("Status code: " + response.statusCode());

            // Print the response headers
            response.headers().map().forEach((k, v) -> logger.info(k + ": " + v));

            // Print the response body
            logger.info("Response body:");
            logger.info(response.body());

            return objectMapper.readValue(response.body(), new TypeReference<List<TourFx>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
