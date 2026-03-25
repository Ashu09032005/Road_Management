package com.roadmanagement.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class RoutingService {

    private static final String OSRM_URL =
            "http://localhost:5000/route/v1/driving/";

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonNode fetchRoutes(double startLon, double startLat,
                                double endLon, double endLat) throws Exception {

        String urlString = OSRM_URL +
                startLon + "," + startLat + ";" +
                endLon + "," + endLat +
                "?overview=full&geometries=geojson&alternatives=true";

        HttpURLConnection conn =
                (HttpURLConnection) new URL(urlString).openConnection();

        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int status = conn.getResponseCode();
        if (status != 200) {
            throw new RuntimeException("OSRM failed with status: " + status);
        }

        InputStream inputStream = conn.getInputStream();
        Scanner sc = new Scanner(inputStream);

        StringBuilder response = new StringBuilder();
        while (sc.hasNext()) {
            response.append(sc.nextLine());
        }

        sc.close();
        conn.disconnect();

        return mapper.readTree(response.toString());
    }
}