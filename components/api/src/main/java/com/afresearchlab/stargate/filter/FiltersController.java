package com.afresearchlab.stargate.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/filters")
public class FiltersController {

    private final FiltersService filtersService;

    public FiltersController(FiltersService filtersService) {
        this.filtersService = filtersService;
    }

    @GetMapping()
    public Filters getFiltersForUser(Principal principal) {
        return filtersService.getFiltersByUser(principal.getName());
    }

    @PostMapping
    public void saveFiltersForUser(Principal principal, @RequestBody Filters request) throws JsonProcessingException {
        this.filtersService.saveFiltersByUser(principal.getName(), request);
    }
}
