package com.roadmanagement.model;

public class BusRequest {

    private int busId;

    private double startLat;
    private double startLon;

    private double endLat;
    private double endLon;

    private Double disasterLat;
    private Double disasterLon;
    private Double disasterRadius;

    // ---- GETTERS & SETTERS ----

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLon() {
        return startLon;
    }

    public void setStartLon(double startLon) {
        this.startLon = startLon;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public double getEndLon() {
        return endLon;
    }

    public void setEndLon(double endLon) {
        this.endLon = endLon;
    }

    public Double getDisasterLat() {
        return disasterLat;
    }

    public void setDisasterLat(Double disasterLat) {
        this.disasterLat = disasterLat;
    }

    public Double getDisasterLon() {
        return disasterLon;
    }

    public void setDisasterLon(Double disasterLon) {
        this.disasterLon = disasterLon;
    }

    public Double getDisasterRadius() {
        return disasterRadius;
    }

    public void setDisasterRadius(Double disasterRadius) {
        this.disasterRadius = disasterRadius;
    }
}