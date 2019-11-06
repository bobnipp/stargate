package com.afresearchlab.stargate.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageResponse {
    private String recordId;
    private String filename;
    private String bytes;
}
