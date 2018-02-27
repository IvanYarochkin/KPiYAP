package com.yarachkin.practical8.parser;

import com.yarachkin.practical8.exception.XmlParserException;

public enum FlowerEnum {
    FLOWERS("flowers"),
    CUT_FLOWERS("cut-flower"),
    GARDEN_FLOWER("garden-flower"),
    VISUAL_PARAMETERS("visual-parameters"),
    GROWING_TIPS("growing-tips"),
    ID("id"),
    FRESHNESS("freshness"),
    FLOWER_HEIGHT("flower-height"),
    WATERING("watering"),
    TEMPERATURE("temperature"),
    LIGHTING("lighting"),
    MULTIPLYING("multiplying"),
    NAME("name"),
    ORIGIN("origin"),
    STEM_COLOR("stem-color"),
    LEAF_COLOR("leaf-color"),
    SOIL("soil");

    private String value;

    private FlowerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FlowerEnum fromValue(String value) throws XmlParserException {
        for (FlowerEnum c : FlowerEnum.values()) {
            if ( c.value.equals(value) ) {
                return c;
            }
        }
        throw new XmlParserException("Incorrect value: value = " + value);
    }

}
