package com.afresearchlab.stargate.rfis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentRequest {
    private String filename;
    private String fileBytes;
    private String fileSize;
}
