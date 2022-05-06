package at.technikum.tour_planner.controller;

import at.technikum.tour_planner.model.TourFx;


import com.google.gson.Gson;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public class TourHttpClient {


    public static void main(String[] args) {
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
            System.out.println("Status code: " + response.statusCode());

            // Print the response headers
            response.headers().map().forEach((k, v) -> System.out.println(k + ": " + v));

            // Print the response body
            System.out.println("Response body:");
            System.out.println(response.body());

            // Object Mapper
            // ObjectMapper objMapper = new ObjectMapper();
            //    TourFx[] fx = objMapper.readValue(response.body(), TourFx[].class);

            TourFx data = new Gson().fromJson(response.body(), TourFx.class);

            System.out.println(data);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
