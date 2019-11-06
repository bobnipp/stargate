package com.afresearchlab.stargate.rfis.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecordLinkRequest {
    private String record1Id;
    private String record1System;
    private String record2Id;
    private String record2System;
}
