package at.technikum.tour_planner.httpClient;

import at.technikum.tour_planner.dal.DaoLog;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import at.technikum.tour_planner.model.TourLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class LogHttpClient implements DaoLog<TourLog> {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(TourHttpClient.class);


    private HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(5))
            .build();


    public List<TourLog> getAll() {
        try {
            // Create a request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/log/all"))
                    .header("User-Agent", "Java 11 HttpClient Bot")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the status code
            logger.debug("Status code: " + response.statusCode());

            // Print the response headers
            response.headers().map().forEach((k, v) -> logger.debug(k + ": " + v));

            // Print the response body
            logger.debug("Response body:");
            logger.debug(response.body());

            return objectMapper.readValue(response.body(), new TypeReference<List<TourLog>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<TourLog> getLog(int id) {
        try {
            // Create a request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/log/tour/" + id))
                    .header("User-Agent", "Java 11 HttpClient Bot")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the status code
            logger.debug("Status code: " + response.statusCode());

            // Print the response headers
            response.headers().map().forEach((k, v) -> logger.debug(k + ": " + v));

            // Print the response body
            logger.debug("Response body:");
            logger.debug(response.body());

            return objectMapper.readValue(response.body(), new TypeReference<List<TourLog>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }

    @Override
    public void delete(TourLog log) {
        try {
            // Create a request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/log/delete/" + log.getId()))
                    .headers("Content-Type", "application/json")
                    .DELETE()
                    .build();

            // Execute the request
            HttpResponse<String> response = getHttpResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TourLog create() {
        return null;
    }

    @Override
    public void update(TourLog tourLog, List<?> params) {

    }

    @Override
    public void update(TourLog tourLog) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody;
            try {
                requestBody = objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(tourLog);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/log/update"))
                    .headers("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = getHttpResponse(request);

            // Print the response body
            logger.debug("Response body:");
            logger.debug(response.body());
        } catch (URISyntaxException e) {
            logger.error("Error with Updating Log");
        }
    }


    private HttpResponse<String> getHttpResponse(HttpRequest request) {
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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
