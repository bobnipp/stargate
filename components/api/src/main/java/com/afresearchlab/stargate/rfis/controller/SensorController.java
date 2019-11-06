package com.afresearchlab.stargate.rfis.controller;

import com.afresearchlab.stargate.rfis.service.SensorService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @DeleteMapping
    public void deleteById(@RequestParam("id") Long id) {
        this.sensorService.deleteById(id);
    }
}
