
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismNomStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismNomStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="APPROVE"/&gt;
 *     &lt;enumeration value="HOLD"/&gt;
 *     &lt;enumeration value="REWORK"/&gt;
 *     &lt;enumeration value="SUBMITTED"/&gt;
 *     &lt;enumeration value="VOTE"/&gt;
 *     &lt;enumeration value="WORKING"/&gt;
 *     &lt;enumeration value="TEMPLATE"/&gt;
 *     &lt;enumeration value="FORWARD"/&gt;
 *     &lt;enumeration value="SEND_TO_NSRP"/&gt;
 *     &lt;enumeration value="SEND_TO_RMS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismNomStatus")
@XmlEnum
public enum PrismNomStatus {

    APPROVE,
    HOLD,
    REWORK,
    SUBMITTED,
    VOTE,
    WORKING,
    TEMPLATE,
    FORWARD,
    SEND_TO_NSRP,
    SEND_TO_RMS;

    public String value() {
        return name();
    }

    public static PrismNomStatus fromValue(String v) {
        return valueOf(v);
    }

}
