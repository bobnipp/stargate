package com.afresearchlab.stargate.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class FiltersService {

    private final FiltersRepository filtersRepository;

    public FiltersService(FiltersRepository filtersRepository) {
        this.filtersRepository = filtersRepository;
    }

    public Filters getFiltersByUser(String user) {
        return this.filtersRepository.getFiltersByUser(user);
    }

    public void saveFiltersByUser(String user, Filters request) throws JsonProcessingException {
        this.filtersRepository.saveFiltersByUser(user, request);
    }

}
