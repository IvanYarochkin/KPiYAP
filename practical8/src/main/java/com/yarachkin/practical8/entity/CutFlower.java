package com.yarachkin.practical8.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cut-flower")
public class CutFlower extends Flower {

    @XmlAttribute(name = "freshness")
    private int freshness;

    public CutFlower() {
    }

    public CutFlower(String name, String origin, VisualParameters visualParameters,
                     Multiplying multiplying, String id, int freshness) {
        super(name, origin, visualParameters, multiplying, id);
        this.freshness = freshness;
    }

    public int getFreshness() {
        return freshness;
    }

    public void setFreshness(int value) {
        this.freshness = value;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof CutFlower) ) return false;

        CutFlower cutFlower = (CutFlower) o;

        return freshness == cutFlower.freshness;
    }

    @Override
    public int hashCode() {
        return freshness;
    }

    @Override
    public String toString() {
        return "CutFlower{" +
                "id='" + getId() +
                ", name='" + getName() +
                ", origin='" + getOrigin() +
                ", visualParameters=" + getVisualParameters() +
                ", multiplying=" + getMultiplying() +
                ", freshness=" + freshness +
                '}';
    }
}