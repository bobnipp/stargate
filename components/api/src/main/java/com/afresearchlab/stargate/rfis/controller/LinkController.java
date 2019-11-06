package com.afresearchlab.stargate.rfis.controller;

import com.afresearchlab.stargate.rfis.requests.CreateRecordLinkRequest;
import com.afresearchlab.stargate.rfis.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recordlinks")
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRecordLink(@RequestBody CreateRecordLinkRequest request) {
        this.linkService.create(request);
    }

    @DeleteMapping
    public void deleteRecordLink(
            @RequestParam("record1Id") String record1Id,
            @RequestParam("record2Id") String record2Id,
            @RequestParam("record1System") String record1System,
            @RequestParam("record2System") String record2System
    ) {
        this.linkService.delete(record1Id, record2Id, record1System, record2System);
    }
}
