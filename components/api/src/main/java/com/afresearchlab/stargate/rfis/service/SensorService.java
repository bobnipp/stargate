package com.afresearchlab.stargate.rfis.service;

import com.afresearchlab.stargate.persistence.SensorRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void deleteById(Long id) {
        this.sensorRepository.deleteById(id);
    }

}
