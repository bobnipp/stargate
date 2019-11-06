package com.afresearchlab.stargate.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filters {
    private List<FilterOption> systemList;
    private List<FilterOption> statusList;
    private List<FilterOption> priorityList;
    private List<FilterOption> targetList;
    private List<FilterOption> sensorList;
    private String keywordSearch;
}
