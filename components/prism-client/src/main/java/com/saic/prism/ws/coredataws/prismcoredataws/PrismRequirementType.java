
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismRequirementType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismRequirementType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ACTIVE"/&gt;
 *     &lt;enumeration value="CONTINGENCY"/&gt;
 *     &lt;enumeration value="REPORT"/&gt;
 *     &lt;enumeration value="INACTIVE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismRequirementType")
@XmlEnum
public enum PrismRequirementType {

    ACTIVE,
    CONTINGENCY,
    REPORT,
    INACTIVE;

    public String value() {
        return name();
    }

    public static PrismRequirementType fromValue(String v) {
        return valueOf(v);
    }

}
