package com.roadmanagement.service;

import org.springframework.stereotype.Service;

@Service
public class DisasterPredictionService {

    private RandomForestTrainingService model = new RandomForestTrainingService();

    public String predict(String city,String weather,int temp,int rainfall) throws Exception {

        int year = 2025;

        return model.predict(city,weather,temp,rainfall,year);
    }
}