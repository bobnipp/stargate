package com.afresearchlab.prismadapter.controller;

import com.afresearchlab.prismadapter.service.PrismCacheDataUnjammer;
import com.afresearchlab.prismadaptermodels.FullNom;
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nominations")
public class PrismController {
    private PrismCacheDataUnjammer unjammer;

    public PrismController(PrismCacheDataUnjammer unjammer) {
        this.unjammer = unjammer;
    }

    @GetMapping
    public List<PrismNom> getAllNominations() {
        return unjammer.getAllNominations();
    }

    @GetMapping("/{id}")
    public FullNom getNomination(@PathVariable String id) {
        return unjammer.getNomination(id);
    }
}
