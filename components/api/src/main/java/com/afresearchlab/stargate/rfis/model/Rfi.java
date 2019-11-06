package com.afresearchlab.stargate.rfis.model;

import com.afresearchlab.prismadaptermodels.RecordHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Rfi {
    private Long id;
    private String title;
    private String country;
    private String coordinates;
    private String city;
    private String region;
    private String nai;
    private String priority;
    private String status;
    private String justification;
    private String prevResearch;
    private Integer requirementTypeId;
    private Integer subWorkflowId;
    private Integer classificationId;
    private String customClassification;
    private Integer caveatId;
    private Integer submittingOrgId;
    private String poc;
    private Integer nipfCodeId;
    private Integer pirNameId;
    private Integer centcomIsrRoleId;
    private String operation;
    private String collectionStartDate;
    private String collectionEndDate;
    private String collectionTerm;
    private String collectionType;
    private Integer assignedTeamId;
    private String assignee;
    private String collectionGuidance;
    private List<String> eeis;
    private List<Link> links;
    private List<Activity> activities;
    private List<Target> targets;
    private List<Sensor> sensors;
    private List<RecordHistory> recordHistory;
    private String createdOn;
}