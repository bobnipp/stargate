package com.afresearchlab.stargate.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomList {
    private String name;
    private List<RecordIdentifier> records;
}
