package com.afresearchlab.stargate.email;

import lombok.Data;

@Data
public class EmailMessage {
    private final String subject;
    private final String body;
}