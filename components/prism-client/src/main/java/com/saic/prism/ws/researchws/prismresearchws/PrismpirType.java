
package com.saic.prism.ws.researchws.prismresearchws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismpirType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismpirType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ACTIVE"/&gt;
 *     &lt;enumeration value="CONTIGENCY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismpirType")
@XmlEnum
public enum PrismpirType {

    ACTIVE,
    CONTIGENCY;

    public String value() {
        return name();
    }

    public static PrismpirType fromValue(String v) {
        return valueOf(v);
    }

}
