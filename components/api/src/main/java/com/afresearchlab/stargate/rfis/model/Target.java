package com.afresearchlab.stargate.rfis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Target {
    private Long id;
    private Long rfiId;
    private String name;
    private String type;
    private Float radius;
    private String radiusUnit;
    private String coordinates;
}
