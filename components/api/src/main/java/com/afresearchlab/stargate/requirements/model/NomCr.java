package com.afresearchlab.stargate.requirements.model;

import com.afresearchlab.prismadaptermodels.Target;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NomCr {
    private String id;
    private String title;
    private String status;
    private String priority;
    private String crName;
    private Integer crPriority;
    private String crStartDate;
    private String crStopDate;
    private String crStatus;
    private String createdOn;
    private List<Target> targets;
    private String country;
    private String poc;
    private String prevResearch;
    private String region;
    private String justification;
}
