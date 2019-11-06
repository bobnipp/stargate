package com.afresearchlab.prismadapter.controller;

import com.afresearchlab.prismadapter.service.PrismCacheDataJammer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private PrismCacheDataJammer dataJammer;

    public CacheController(PrismCacheDataJammer dataJammer) {
        this.dataJammer = dataJammer;
    }

    @PostMapping
    public void buildCache() {
        dataJammer.jamIt();
    }

    @DeleteMapping
    public void emptyCache() { dataJammer.deleteIt(); }
}
