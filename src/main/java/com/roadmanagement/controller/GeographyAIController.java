
package com.roadmanagement.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import com.roadmanagement.service.GeographyAIService;
@RestController
@RequestMapping("/geo-ai")
public class GeographyAIController {

    @Autowired
    private GeographyAIService service;

    @PostMapping("/ask")
    public String ask(@RequestBody Map<String, String> body) {
        return service.getAnswer(body.get("question"));
    }
}
