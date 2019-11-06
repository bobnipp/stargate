package com.afresearchlab.stargate.requirements.controller;

import com.afresearchlab.stargate.requirements.model.ImmFullNom;
import com.afresearchlab.stargate.requirements.service.PrismService;
import com.afresearchlab.stargate.rfis.model.SelectOption;
import com.afresearchlab.stargate.rfis.service.SelectOptionsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/prism")
public class NominationController {

    private final PrismService prismService;
    private final SelectOptionsService selectOptionsService;

    public NominationController(PrismService prismService, SelectOptionsService selectOptionsService) {
        this.prismService = prismService;
        this.selectOptionsService = selectOptionsService;
    }

    @GetMapping("/nominations")
    public List<ImmFullNom> getAllNominations() {
        return prismService.getAllNominations();
    }

    @GetMapping("/nominations/{id}")
    public ImmFullNom getNom(@PathVariable("id") String id) {
        return prismService.getNominationById(id);
    }

    @GetMapping("/options")
    public Map<String, List<SelectOption>> getOptions() {
        return this.selectOptionsService.getNomOptions();
    }
}
