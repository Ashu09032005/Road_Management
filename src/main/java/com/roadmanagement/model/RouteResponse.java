package com.roadmanagement.model;

import lombok.Data;

@Data
public class RouteResponse {

    private double distance;
    private double duration;
    private Object geometry;

    private double riskScore;
    private String riskLevel;
    private boolean bestRoute;
}