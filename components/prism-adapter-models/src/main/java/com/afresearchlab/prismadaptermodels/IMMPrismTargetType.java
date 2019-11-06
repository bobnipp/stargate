package com.afresearchlab.prismadaptermodels;

public enum IMMPrismTargetType {
    DSA,
    LOC,
    POINT,
    RWAC;

    public String value() {
        return name();
    }

    public static IMMPrismTargetType fromValue(String v) {
        return valueOf(v);
    }

}
