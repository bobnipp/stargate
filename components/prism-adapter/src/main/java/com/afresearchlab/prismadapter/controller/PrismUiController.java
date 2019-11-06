package com.afresearchlab.prismadapter.controller;

import com.afresearchlab.prismadapter.model.PrismUiNomination;
import com.afresearchlab.prismadapter.service.PrismUiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ui")
public class PrismUiController {

    private PrismUiService prismUiService;

    public PrismUiController(PrismUiService prismUiService) {
        this.prismUiService = prismUiService;
    }

    @GetMapping
    @RequestMapping("/nominations")
    public List<PrismUiNomination> getAllNominations() {
        return prismUiService.getAllNominations();
    }
}
