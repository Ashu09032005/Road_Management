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

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonNode fetchRoutes(double startLon, double startLat,
                                double endLon, double endLat) throws Exception {

        // ✅ Get OSRM URL from ENV (for deployment)
        String baseUrl = System.getenv("OSRM_URL");

        // ✅ fallback to public OSRM (IMPORTANT CHANGE)
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://router.project-osrm.org";
        }

        // ✅ Correct coordinate format (lon,lat;lon,lat)
        String coords = startLon + "," + startLat + ";" + endLon + "," + endLat;

        // ✅ Build final URL
        String urlString = baseUrl + "/route/v1/driving/"
                + coords
                + "?overview=full&geometries=geojson&alternatives=true";

        System.out.println("Calling OSRM: " + urlString); // debug

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