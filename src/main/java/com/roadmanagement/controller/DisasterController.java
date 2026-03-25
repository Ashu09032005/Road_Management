package com.roadmanagement.controller;

import com.roadmanagement.service.DisasterPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disaster")
public class DisasterController {

    @Autowired
    private DisasterPredictionService prediction;

    @GetMapping("/predict")
    public String predict(
            @RequestParam String city,
            @RequestParam String weather,
            @RequestParam int temperature,
            @RequestParam int rainfall
    ) throws Exception {

        String disaster = prediction.predict(city, weather, temperature, rainfall);

        return "Predicted Disaster: " + disaster;
    }
}