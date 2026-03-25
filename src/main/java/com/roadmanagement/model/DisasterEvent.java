package com.roadmanagement.model;

public class DisasterEvent {

    private String city;
    private String weather;
    private int temperature;
    private int rainfall;
    private int year;
    private String disasterType;

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getWeather() { return weather; }

    public void setWeather(String weather) { this.weather = weather; }

    public int getTemperature() { return temperature; }

    public void setTemperature(int temperature) { this.temperature = temperature; }

    public int getRainfall() { return rainfall; }

    public void setRainfall(int rainfall) { this.rainfall = rainfall; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public String getDisasterType() { return disasterType; }

    public void setDisasterType(String disasterType) { this.disasterType = disasterType; }
}