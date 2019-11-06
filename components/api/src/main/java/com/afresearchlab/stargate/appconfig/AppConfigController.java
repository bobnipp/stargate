package com.afresearchlab.stargate.appconfig;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
@RequestMapping("/api/v1/config")
public class AppConfigController {

    public AppConfigController() {}

    @GetMapping
    @RequestMapping("/{key}")
    @Produces("text/html")
    public String getEnvVar(@PathVariable String key) {
        return System.getenv(key);
    }

}
