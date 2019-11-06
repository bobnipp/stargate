package com.afresearchlab.stargate.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordIdentifier {
    private String recordType;
    private String recordId;
}
