package com.roadmanagement.controller;
import java.util.List;
import com.roadmanagement.model.BusRequest;
import com.roadmanagement.model.RouteResponse;
import com.roadmanagement.service.RouteOrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RouteController {

    @Autowired
    private RouteOrchestratorService orchestrator;

    @PostMapping("/route")
    public List<RouteResponse> getRoute(@RequestBody BusRequest request)
            throws Exception {

        return orchestrator.processRoute(request);
    }
}

