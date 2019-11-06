package com.afresearchlab.stargate.rfis.controller;

import com.afresearchlab.stargate.rfis.model.Rfi;
import com.afresearchlab.stargate.rfis.model.SelectOption;
import com.afresearchlab.stargate.rfis.requests.CreateRfiRequest;
import com.afresearchlab.stargate.rfis.service.RfiService;
import com.afresearchlab.stargate.rfis.service.SelectOptionsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/imm")
public class RfiController {

    private final RfiService rfiService;
    private final SelectOptionsService selectOptionsService;

    public RfiController(RfiService rfiService, SelectOptionsService selectOptionsService) {
        this.rfiService = rfiService;
        this.selectOptionsService = selectOptionsService;
    }

    @GetMapping("/rfis")
    public List<Rfi> getAll() {
        return rfiService.getAll();
    }

    @GetMapping("/rfis/{id}")
    public Rfi get(@PathVariable Long id) {
        return this.rfiService.get(id);
    }

    @PutMapping("/rfis/{id}")
    public Rfi update(Principal principal, @PathVariable Long id, @RequestBody Rfi rfi) {
        return rfiService.updateRfi(principal.getName(), rfi);
    }

    @PostMapping("/rfis")
    @ResponseStatus(HttpStatus.CREATED)
    public Rfi create(Principal principal, @Valid @RequestBody CreateRfiRequest request) {
        return this.rfiService.create(principal.getName(), request);
    }

    @DeleteMapping("/rfis/{id}")
    public void delete(@PathVariable Long id) {
        this.rfiService.delete(id);
    }

    @GetMapping("/options")
    public Map<String, List<SelectOption>> getOptions() {
        return this.selectOptionsService.getRfiOptions();
    }

    @GetMapping("/delete/all")
    public void deleteAll() {
        this.rfiService.deleteAll();
    }
}