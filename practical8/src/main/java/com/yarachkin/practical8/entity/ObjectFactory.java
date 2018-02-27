package com.yarachkin.practical8.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _GardenFlower_QNAME = new QName("http://www.dei.isep.ipp.pt/Flowers", "garden-flower");
    private final static QName _Flower_QNAME = new QName("http://www.dei.isep.ipp.pt/Flowers", "flower");
    private final static QName _CutFlower_QNAME = new QName("http://www.dei.isep.ipp.pt/Flowers", "cut-flower");

    public ObjectFactory() {
    }

    public Flowers createFlowers() {
        return new Flowers();
    }

    public Flower createFlower() {
        return new Flower();
    }

    public CutFlower createCutFlower() {
        return new CutFlower();
    }

    public GardenFlower createGardenFlower() {
        return new GardenFlower();
    }

    public GrowingTips createGrowingTips() {
        return new GrowingTips();
    }

    public VisualParameters createVisualParameters() {
        return new VisualParameters();
    }

    @XmlElementDecl(namespace = "http://www.dei.isep.ipp.pt/Flowers", name = "garden-flower",
            substitutionHeadNamespace = "http://www.dei.isep.ipp.pt/Flowers", substitutionHeadName = "flower")
    public JAXBElement<GardenFlower> createGardenFlower(GardenFlower value) {
        return new JAXBElement<>(_GardenFlower_QNAME, GardenFlower.class, null, value);
    }

    @XmlElementDecl(namespace = "http://www.dei.isep.ipp.pt/Flowers", name = "flower")
    public JAXBElement<Flower> createFlower(Flower value) {
        return new JAXBElement<Flower>(_Flower_QNAME, Flower.class, null, value);
    }

    @XmlElementDecl(namespace = "http://www.dei.isep.ipp.pt/Flowers", name = "cut-flower", substitutionHeadNamespace = "http://www.dei.isep.ipp.pt/Flowers", substitutionHeadName = "flower")
    public JAXBElement<CutFlower> createCutFlower(CutFlower value) {
        return new JAXBElement<CutFlower>(_CutFlower_QNAME, CutFlower.class, null, value);
    }
}