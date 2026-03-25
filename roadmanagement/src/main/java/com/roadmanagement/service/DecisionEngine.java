package com.roadmanagement.service;

import org.springframework.stereotype.Service;

@Service
public class DecisionEngine {

    public double calculateFillPercent(int passengers, int capacity) {
        return (passengers * 100.0) / capacity;
    }

    public boolean isLowLoad(double fillPercent) {
        return fillPercent <= 30;
    }

    public boolean isHighLoad(double fillPercent) {
        return fillPercent >= 80;
    }

    public boolean isEtaAllowed(double originalEta, double newEta) {

        double maxPercentIncrease = 0.05; // 5%
        double maxSeconds = 180;          // 3 minutes

        double allowedIncrease =
                Math.min(originalEta * maxPercentIncrease, maxSeconds);

        return newEta <= originalEta + allowedIncrease;
    }
}

