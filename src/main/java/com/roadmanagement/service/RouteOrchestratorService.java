package com.roadmanagement.service;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.roadmanagement.model.BusRequest;
import com.roadmanagement.model.RouteResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteOrchestratorService {

    @Autowired
    private RoutingService routingService;

    @Autowired
    private DisasterService disasterService;

    public List<RouteResponse> processRoute(BusRequest request) throws Exception {

        System.out.println("=== NEW ROUTE REQUEST ===");

        System.out.println("Disaster Lat: " + request.getDisasterLat());
        System.out.println("Disaster Lon: " + request.getDisasterLon());
        System.out.println("Radius: " + request.getDisasterRadius());

        JsonNode routeJson = routingService.fetchRoutes(
                request.getStartLon(),
                request.getStartLat(),
                request.getEndLon(),
                request.getEndLat()
        );

        JsonNode routesNode = routeJson.get("routes");
        List<RouteResponse> responseList = new ArrayList<>();

        for (int i = 0; i < routesNode.size(); i++) {

            System.out.println("\n--- Checking Route " + i + " ---");

            JsonNode route = routesNode.get(i);
            JsonNode coordinates = route.get("geometry").get("coordinates");

            boolean intersectsDisaster = false;

            if (request.getDisasterLat() != null &&
                    request.getDisasterLon() != null &&
                    request.getDisasterRadius() != null) {

                for (JsonNode coord : coordinates) {

                    double lon = coord.get(0).asDouble();
                    double lat = coord.get(1).asDouble();

                    double distance = disasterService.calculateDistance(
                            lat, lon,
                            request.getDisasterLat(),
                            request.getDisasterLon()
                    );

                    if (distance <= request.getDisasterRadius()) {
                        intersectsDisaster = true;
                        System.out.println("❌ Route intersects disaster zone!");
                        break;
                    }
                }
            }

            if (intersectsDisaster) {
                System.out.println("❌ Skipping route " + i);
                continue;
            }

            double distance = route.get("distance").asDouble();
            double duration = route.get("duration").asDouble();

            RouteResponse response = new RouteResponse();
            response.setDistance(distance);
            response.setDuration(duration);
            response.setGeometry(route.get("geometry"));

            response.setBestRoute(true);

            responseList.add(response);

            System.out.println("✅ Route " + i + " is SAFE and added");
        }

        if (responseList.isEmpty()) {
            System.out.println("🚨 No safe routes available!");
        }

        return responseList;
    }
}