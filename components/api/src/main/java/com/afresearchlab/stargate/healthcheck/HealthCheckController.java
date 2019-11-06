package com.afresearchlab.stargate.healthcheck;

import com.afresearchlab.stargate.requirements.service.PrismService;
import com.afresearchlab.stargate.rfis.service.RfiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthCheckController {

    private final PrismService prismService;
    private final RfiService rfiService;

    public HealthCheckController(PrismService prismService, RfiService rfiService) {
        this.prismService = prismService;
        this.rfiService = rfiService;
    }

    @GetMapping("/{integrationName}")
    public HealthCheck healthCheck(@PathVariable("integrationName") String integrationName) {
        switch (integrationName) {
            case "prism":
                return prismService.healthCheck();
            case "imm":
                return rfiService.healthCheck();
        }

        return new HealthCheck(false, -1);
    }
}
