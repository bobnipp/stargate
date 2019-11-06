package com.afresearchlab.stargate.rfis.service;

import com.afresearchlab.stargate.persistence.SelectOptionsRepository;
import com.afresearchlab.stargate.persistence.SelectOptionsRepository.SelectOptionsObjectType;
import com.afresearchlab.stargate.rfis.model.SelectOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SelectOptionsService {
    private final SelectOptionsRepository selectOptionsRepository;

    public SelectOptionsService(SelectOptionsRepository selectOptionsRepository) {this.selectOptionsRepository = selectOptionsRepository;}

    public Map<String, List<SelectOption>> getNomOptions() {
        return selectOptionsRepository.getOptions(SelectOptionsObjectType.NOM);
    }

    public Map<String, List<SelectOption>> getRfiOptions() {
        return selectOptionsRepository.getOptions(SelectOptionsObjectType.RFI);
    }
}
