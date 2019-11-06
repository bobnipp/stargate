package
    com.afresearchlab.stargate.rfis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Link {
    private String record1Id;
    private String record2Id;
    private String record1System;
    private String record2System;
    private String title;
    private String status;
}