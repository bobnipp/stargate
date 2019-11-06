package com.afresearchlab.stargate.rfis.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecordActivityRequest {
    private Long id;
    private Long recordId;
    private String text;
    private String username;
    private String date;
}
