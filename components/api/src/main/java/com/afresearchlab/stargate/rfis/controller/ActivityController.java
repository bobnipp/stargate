package com.afresearchlab.stargate.rfis.controller;

import com.afresearchlab.stargate.rfis.model.Activity;
import com.afresearchlab.stargate.rfis.model.ActivityRequest;
import com.afresearchlab.stargate.rfis.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Activity createRecordActivity(@RequestBody ActivityRequest request) throws IOException {
        return this.activityService.create(request);
    }

    @DeleteMapping
    public void deleteById(@RequestParam("id") Long id) {
        this.activityService.deleteById(id);
    }
}
