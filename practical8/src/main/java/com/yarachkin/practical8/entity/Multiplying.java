package com.yarachkin.practical8.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "multiplying")
@XmlEnum
public enum Multiplying {

    @XmlEnumValue("leaf")
    LEAF("leaf"),
    @XmlEnumValue("stalk")
    STALK("stalk"),
    @XmlEnumValue("seed")
    SEED("seed");
    private final String value;

    Multiplying(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Multiplying fromValue(String v) {
        for (Multiplying c : Multiplying.values()) {
            if ( c.value.equals(v) ) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}