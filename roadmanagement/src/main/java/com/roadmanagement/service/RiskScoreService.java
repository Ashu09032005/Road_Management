package com.roadmanagement.service;

import org.springframework.stereotype.Service;

@Service
public class RiskScoreService {

    public double calculateRiskScore(double disasterRisk,
                                     double congestion,
                                     double accidentDensity) {

        return (disasterRisk * 0.5)
                + (congestion * 0.3)
                + (accidentDensity * 0.2);
    }

    public String classifyRisk(double riskScore) {

        if (riskScore > 70)
            return "HIGH";

        if (riskScore > 40)
            return "MEDIUM";

        return "LOW";
    }

    // 🔹 ADD THIS METHOD
    public int calculateRisk(String disaster){

        switch(disaster){

            case "Earthquake":
                return 90;

            case "Cyclone":
                return 85;

            case "Flood":
                return 75;

            case "Landslide":
                return 65;

            case "Fire":
                return 40;

            case "Stampede":
                return 30;

            default:
                return 10;
        }
    }
}