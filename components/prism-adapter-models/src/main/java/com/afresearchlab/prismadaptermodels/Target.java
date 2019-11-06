package com.afresearchlab.prismadaptermodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Target {
    private String name;
    private IMMPrismTargetType type;
    private String coordinates;
}
