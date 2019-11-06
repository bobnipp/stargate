package com.afresearchlab.stargate.rfis.presenter;

import com.afresearchlab.stargate.rfis.model.Target;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RfiListItem {
    private Long id;
    private String title;
    private String status;
    private String priority;
    private String justification;
    private String coordinates;
    private String createdOn;
    private List<Target> targets;
    private String city;
    private String country;
    private String nai;
    private String operation;
    private String poc;
    private String prevResearch;
    private String region;
}
