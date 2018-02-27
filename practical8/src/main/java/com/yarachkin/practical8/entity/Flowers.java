package com.yarachkin.practical8.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"flower"})
@XmlRootElement(name = "flowers", namespace = "http://www.dei.isep.ipp.pt/Flowers")
public class Flowers {
    @XmlElementRef(name = "flower", namespace = "http://www.dei.isep.ipp.pt/Flowers", type = JAXBElement.class)
    private List<JAXBElement<? extends Flower>> flower;

    public List<JAXBElement<? extends Flower>> getFlower() {
        if ( flower == null ) {
            flower = new ArrayList<>();
        }
        return this.flower;
    }

    @Override
    public String toString() {
        return "Flowers{" +
                "flower=" + flower +
                '}';
    }
}