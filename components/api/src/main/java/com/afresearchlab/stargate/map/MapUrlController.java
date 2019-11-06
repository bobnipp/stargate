package com.afresearchlab.stargate.map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/map_url")
public class MapUrlController {
    private final String mapOfTheWorldDomain;

    public MapUrlController(@Qualifier("mowDomain") String mapOfTheWorldDomain) {
        this.mapOfTheWorldDomain = mapOfTheWorldDomain;
    }

    @GetMapping
    public MapUrlResponse getMapUrl() {
        return new MapUrlResponse(this.mapOfTheWorldDomain);
    }
}
