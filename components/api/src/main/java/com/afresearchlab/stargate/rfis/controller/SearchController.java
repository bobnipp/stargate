package com.afresearchlab.stargate.rfis.controller;


import com.afresearchlab.stargate.rfis.service.RfiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    private final RfiService service;

    public SearchController(RfiService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/rfis")
    public List<String> get(@QueryParam("title") String title, @QueryParam("country") String country) {
        return service.search(title, country);
    }
}
