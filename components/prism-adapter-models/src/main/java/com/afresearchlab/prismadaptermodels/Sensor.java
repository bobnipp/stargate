package com.afresearchlab.prismadaptermodels;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {
    private String type;
    private String sensor;
    private String mode;
    private Double requiredQuality;
    private Double desiredQuality;
    private Double startAzimuth;
    private Double stopAzimuth;
    private Double startElevation;
    private Double stopElevation;
    private Double minSunAzimuth;
    private Double maxSunAzimuth;
}
