package com.afresearchlab.stargate.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterOption {
    private Integer id;
    private String value;
    private String system;
}
