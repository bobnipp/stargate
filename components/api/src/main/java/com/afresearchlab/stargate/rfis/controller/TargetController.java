package com.afresearchlab.stargate.rfis.controller;

import com.afresearchlab.stargate.rfis.service.TargetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/targets")
public class TargetController {
    private final TargetService targetService;

    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @DeleteMapping
    public void deleteById(@RequestParam("id") Long id) {
        this.targetService.deleteById(id);
    }
}
