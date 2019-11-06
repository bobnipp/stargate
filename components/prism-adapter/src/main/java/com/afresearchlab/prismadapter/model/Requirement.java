package com.afresearchlab.prismadapter.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Requirement {
    private final String id;
    private final String title;
    private final String description;
    private final String date;
    private final String status;
    private final Double lat;
    private final Double lng;
}
