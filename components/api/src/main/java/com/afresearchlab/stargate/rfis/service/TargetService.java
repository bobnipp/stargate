package com.afresearchlab.stargate.rfis.service;

import com.afresearchlab.stargate.persistence.TargetRepository;
import org.springframework.stereotype.Service;

@Service
public class TargetService {

    private final TargetRepository targetRepository;

    public TargetService(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    public void deleteById(Long id) {
        this.targetRepository.deleteById(id);
    }

}
