package com.afresearchlab.stargate.rfis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    private Long id;
    private Long rfiId;
    private String sensor;
    private String sensorType;
    private String mode;
    private Integer desiredQuality;
    private Integer requiredQuality;
}
