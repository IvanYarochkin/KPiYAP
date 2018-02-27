package com.yarachkin.practical8.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visual-parameters", propOrder = {"stemColor", "leafColor", "flowerHeight"})
public class VisualParameters {
    @XmlElement(name = "stem-color", required = true)
    private String stemColor;
    @XmlElement(name = "leaf-color", required = true)
    private String leafColor;
    @XmlElement(name = "flower-height")
    private double flowerHeight;

    public VisualParameters() {
    }

    public VisualParameters(String stemColor, String leafColor, double flowerHeight) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.flowerHeight = flowerHeight;
    }

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String value) {
        this.stemColor = value;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(String value) {
        this.leafColor = value;
    }

    public double getFlowerHeight() {
        return flowerHeight;
    }

    public void setFlowerHeight(double value) {
        this.flowerHeight = value;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof VisualParameters) ) return false;

        VisualParameters that = (VisualParameters) o;

        if ( Double.compare(that.flowerHeight, flowerHeight) != 0 ) return false;
        if ( stemColor != null ? !stemColor.equals(that.stemColor) : that.stemColor != null ) return false;
        return leafColor != null ? leafColor.equals(that.leafColor) : that.leafColor == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = stemColor != null ? stemColor.hashCode() : 0;
        result = 31 * result + (leafColor != null ? leafColor.hashCode() : 0);
        temp = Double.doubleToLongBits(flowerHeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "stemColor='" + stemColor + '\'' +
                ", leafColor='" + leafColor + '\'' +
                ", flowerHeight=" + flowerHeight +
                '}';
    }
}