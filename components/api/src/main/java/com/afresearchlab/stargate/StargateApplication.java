package com.afresearchlab.stargate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class StargateApplication {

    public static void main(String[] args) {
        SpringApplication.run(StargateApplication.class, args);
    }
}
