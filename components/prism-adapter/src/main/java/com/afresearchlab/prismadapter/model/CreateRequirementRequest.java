package com.afresearchlab.prismadapter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequirementRequest {
    private String name;
    private String responsibleOrg;
    private String crType;
    private String startStopDateFrom;
    private String startStopDateTo;
    private String standingAdHoc;
    private Integer priority;
}
