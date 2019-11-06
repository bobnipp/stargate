
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismnomPrecedence.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismnomPrecedence"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="IMMEDIATE"/&gt;
 *     &lt;enumeration value="PRIORITY"/&gt;
 *     &lt;enumeration value="ROUTINE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismnomPrecedence")
@XmlEnum
public enum PrismnomPrecedence {

    IMMEDIATE,
    PRIORITY,
    ROUTINE;

    public String value() {
        return name();
    }

    public static PrismnomPrecedence fromValue(String v) {
        return valueOf(v);
    }

}
