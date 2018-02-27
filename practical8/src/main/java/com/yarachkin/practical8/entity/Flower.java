package com.yarachkin.practical8.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flower", propOrder = {"name", "origin", "visualParameters", "multiplying"})
@XmlSeeAlso({CutFlower.class, GardenFlower.class})
public class Flower {

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String origin;
    @XmlElement(name = "visual-parameters", required = true)
    private VisualParameters visualParameters;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    private Multiplying multiplying;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    private String id;

    public Flower() {
        visualParameters = new VisualParameters();
    }

    public Flower(String name, String origin, VisualParameters visualParameters, Multiplying multiplying, String id) {
        this.name = name;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.multiplying = multiplying;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String value) {
        this.origin = value;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters value) {
        this.visualParameters = value;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying value) {
        this.multiplying = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Flower) ) return false;

        Flower flower = (Flower) o;

        if ( name != null ? !name.equals(flower.name) : flower.name != null ) return false;
        if ( origin != null ? !origin.equals(flower.origin) : flower.origin != null ) return false;
        if ( visualParameters != null ? !visualParameters.equals(flower.visualParameters) : flower.visualParameters != null )
            return false;
        if ( multiplying != flower.multiplying ) return false;
        return id != null ? id.equals(flower.id) : flower.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (visualParameters != null ? visualParameters.hashCode() : 0);
        result = 31 * result + (multiplying != null ? multiplying.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", visualParameters=" + visualParameters +
                ", multiplying=" + multiplying +
                ", id='" + id + '\'' +
                '}';
    }
}