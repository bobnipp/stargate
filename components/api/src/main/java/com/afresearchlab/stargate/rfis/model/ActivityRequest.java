package com.afresearchlab.stargate.rfis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {
    private Long id;
    private String rfiId;
    private String text;
    private String username;
    private String timestamp;
    private List<AttachmentRequest> attachments;
}
