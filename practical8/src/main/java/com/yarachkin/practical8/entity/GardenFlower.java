package com.yarachkin.practical8.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "garden-flower", propOrder = {"growingTips"})
public class GardenFlower extends Flower {
    @XmlElement(name = "growing-tips", required = true)
    private GrowingTips growingTips;
    @XmlAttribute(name = "soil")
    private String soil = "unpaved";

    public GardenFlower() {
        growingTips = new GrowingTips();
    }

    public GardenFlower(String name, String origin, VisualParameters visualParameters, Multiplying multiplying,
                        String id, GrowingTips growingTips, String soil) {
        super(name, origin, visualParameters, multiplying, id);
        this.growingTips = growingTips;
        this.soil = soil;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips value) {
        this.growingTips = value;
    }

    public String getSoil() {
        if ( soil == null ) {
            return "unpaved";
        } else {
            return soil;
        }
    }

    public void setSoil(String value) {
        this.soil = value;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof GardenFlower) ) return false;

        GardenFlower that = (GardenFlower) o;

        if ( growingTips != null ? !growingTips.equals(that.growingTips) : that.growingTips != null ) return false;
        return soil != null ? soil.equals(that.soil) : that.soil == null;
    }

    @Override
    public int hashCode() {
        int result = growingTips != null ? growingTips.hashCode() : 0;
        result = 31 * result + (soil != null ? soil.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GardenFlower{" +
                "id='" + getId() +
                ", name='" + getName() +
                ", origin='" + getOrigin() +
                ", visualParameters=" + getVisualParameters() +
                ", multiplying=" + getMultiplying() +
                ", growingTips=" + growingTips +
                ", soil='" + soil +
                '}';
    }
}