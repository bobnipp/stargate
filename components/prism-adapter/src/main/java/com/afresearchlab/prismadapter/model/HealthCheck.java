package com.afresearchlab.prismadapter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthCheck {
    private boolean success;
    private long lastsave;
}
