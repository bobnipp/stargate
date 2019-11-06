package com.afresearchlab.prismadaptermodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullNom {
    private String id;
    private String title;
    private String country;
    private String region;
    private String priority;
    private String status;
    private String justification;
    private String poc;
    private String collectionStartDate;
    private String collectionEndDate;
    private String collectionTerm;
    private String collectionType;
    private String assignee;
    private String createdOn;
    private String prevResearch;
    private String collectionGuidance;
    private List<String> eeis;
    private List<Target> targets;
    private List<RecordHistory> recordHistory;
    private List<Sensor> sensors;
}
