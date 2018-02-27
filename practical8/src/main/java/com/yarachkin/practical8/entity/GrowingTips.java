package com.yarachkin.practical8.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "growing-tips", propOrder = {"temperature", "lighting", "watering"})
public class GrowingTips {

    private double temperature;
    private boolean lighting;
    private double watering;

    public GrowingTips() {
    }

    public GrowingTips(double temperature, boolean lighting, double watering) {
        this.temperature = temperature;
        this.lighting = lighting;
        this.watering = watering;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double value) {
        this.temperature = value;
    }

    public boolean isLighting() {
        return lighting;
    }

    public void setLighting(boolean value) {
        this.lighting = value;
    }

    public double getWatering() {
        return watering;
    }

    public void setWatering(double value) {
        this.watering = value;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof GrowingTips) ) return false;

        GrowingTips that = (GrowingTips) o;

        if ( Double.compare(that.temperature, temperature) != 0 ) return false;
        if ( lighting != that.lighting ) return false;
        return Double.compare(that.watering, watering) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(temperature);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (lighting ? 1 : 0);
        temp = Double.doubleToLongBits(watering);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "GrowingTips{" +
                "temperature=" + temperature +
                ", lighting=" + lighting +
                ", watering=" + watering +
                '}';
    }
}