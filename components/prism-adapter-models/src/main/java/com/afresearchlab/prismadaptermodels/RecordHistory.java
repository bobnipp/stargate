package com.afresearchlab.prismadaptermodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordHistory {
    private String username;
    private String action;
    private String date;
}
