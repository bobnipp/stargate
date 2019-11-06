
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismPlatformStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismPlatformStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NOMINAL"/&gt;
 *     &lt;enumeration value="CONVENTIONAL_ANTI_AIRCRAFT_ATTACK"/&gt;
 *     &lt;enumeration value="AIR_COLLISION"/&gt;
 *     &lt;enumeration value="ATTACKED_BY_ENEMY_AIRCRAFT"/&gt;
 *     &lt;enumeration value="ADVISORY_WARNING"/&gt;
 *     &lt;enumeration value="COUNTRY_CLEARANCE_DIFFICULTIES"/&gt;
 *     &lt;enumeration value="CONTROL_MALFUNCTION"/&gt;
 *     &lt;enumeration value="CRASHED"/&gt;
 *     &lt;enumeration value="ELECTRICAL_SYSTEM_FAILURE"/&gt;
 *     &lt;enumeration value="ENGINE_MALFUNCTION"/&gt;
 *     &lt;enumeration value="INSUFFICIENT_FUEL"/&gt;
 *     &lt;enumeration value="ATTACKED_ON_THE_GROUND"/&gt;
 *     &lt;enumeration value="ORDERED_ACTION_NOT_BY_JCS"/&gt;
 *     &lt;enumeration value="ORDERED_BY_JCS"/&gt;
 *     &lt;enumeration value="LANDING_ACCIDENT"/&gt;
 *     &lt;enumeration value="MAINTENANCE_CAUSE"/&gt;
 *     &lt;enumeration value="OPERATIONS"/&gt;
 *     &lt;enumeration value="OTHER"/&gt;
 *     &lt;enumeration value="PILOT_ERROR"/&gt;
 *     &lt;enumeration value="SMALL_ARMS_ATTACK"/&gt;
 *     &lt;enumeration value="STRUCTURAL_FAILURE"/&gt;
 *     &lt;enumeration value="SAM_ATTACK"/&gt;
 *     &lt;enumeration value="TAKE_OFF_ACCIDENT"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *     &lt;enumeration value="UNUSED_CONTINGENCY_MISSION"/&gt;
 *     &lt;enumeration value="WEATHER"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismPlatformStatus")
@XmlEnum
public enum PrismPlatformStatus {

    NOMINAL,
    CONVENTIONAL_ANTI_AIRCRAFT_ATTACK,
    AIR_COLLISION,
    ATTACKED_BY_ENEMY_AIRCRAFT,
    ADVISORY_WARNING,
    COUNTRY_CLEARANCE_DIFFICULTIES,
    CONTROL_MALFUNCTION,
    CRASHED,
    ELECTRICAL_SYSTEM_FAILURE,
    ENGINE_MALFUNCTION,
    INSUFFICIENT_FUEL,
    ATTACKED_ON_THE_GROUND,
    ORDERED_ACTION_NOT_BY_JCS,
    ORDERED_BY_JCS,
    LANDING_ACCIDENT,
    MAINTENANCE_CAUSE,
    OPERATIONS,
    OTHER,
    PILOT_ERROR,
    SMALL_ARMS_ATTACK,
    STRUCTURAL_FAILURE,
    SAM_ATTACK,
    TAKE_OFF_ACCIDENT,
    UNKNOWN,
    UNUSED_CONTINGENCY_MISSION,
    WEATHER;

    public String value() {
        return name();
    }

    public static PrismPlatformStatus fromValue(String v) {
        return valueOf(v);
    }

}
