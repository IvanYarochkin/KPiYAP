package com.yarachkin.practical8.parser;

import com.yarachkin.practical8.entity.CutFlower;
import com.yarachkin.practical8.entity.Flower;
import com.yarachkin.practical8.entity.GardenFlower;
import com.yarachkin.practical8.entity.GrowingTips;
import com.yarachkin.practical8.entity.Multiplying;
import com.yarachkin.practical8.entity.VisualParameters;
import com.yarachkin.practical8.exception.XmlParserException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlowersStaxBuilder {
    private List<Flower> flowers;
    private XMLInputFactory inputFactory;

    public FlowersStaxBuilder() throws XmlParserException {
        inputFactory = XMLInputFactory.newInstance();
        flowers = new ArrayList<>();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void buildListFlowers(String filePath) throws XmlParserException {
        try (FileInputStream inputStream = new FileInputStream(new File(filePath))) {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if ( type == XMLStreamConstants.START_ELEMENT ) {
                    String name = reader.getLocalName();
                    if ( FlowerEnum.fromValue(name) == FlowerEnum.CUT_FLOWERS ) {
                        Flower flower = buildCutFlower(reader);
                        flowers.add(flower);
                    } else if ( FlowerEnum.fromValue(name) == FlowerEnum.GARDEN_FLOWER ) {
                        Flower flower = buildGardenFlower(reader);
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            throw new XmlParserException(e);
        }
    }

    private Flower buildCutFlower(XMLStreamReader reader) throws XMLStreamException, XmlParserException {
        CutFlower cutFlower = new CutFlower();
        cutFlower.setId(reader.getAttributeValue(null, FlowerEnum.ID.getValue()));
        if ( reader.getAttributeCount() == 2 ) {
            int freshness = Integer.parseInt(reader.getAttributeValue(null, FlowerEnum.FRESHNESS.getValue()));
            cutFlower.setFreshness(freshness);
        }
        while (reader.hasNext()) {
            int type = reader.next();
            String name;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.fromValue(name)) {
                        case NAME:
                            cutFlower.setName(getXmlText(reader));
                            break;
                        case ORIGIN:
                            cutFlower.setOrigin(getXmlText(reader));
                            break;
                        case MULTIPLYING:
                            cutFlower.setMultiplying(Multiplying.fromValue(getXmlText(reader)));
                            break;
                        case VISUAL_PARAMETERS:
                            cutFlower.setVisualParameters(getVisualParameters(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if ( FlowerEnum.fromValue(name) == FlowerEnum.CUT_FLOWERS ) {
                        return cutFlower;
                    }
            }
        }
        throw new XmlParserException("Unknown element.");
    }

    private Flower buildGardenFlower(XMLStreamReader reader) throws XMLStreamException, XmlParserException {
        GardenFlower gardenFlower = new GardenFlower();
        gardenFlower.setId(reader.getAttributeValue(null, FlowerEnum.ID.getValue()));
        if ( reader.getAttributeCount() == 2 ) {
            gardenFlower.setSoil(reader.getAttributeValue(null, FlowerEnum.SOIL.getValue()));
        }
        while (reader.hasNext()) {
            int type = reader.next();
            String name;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.fromValue(name)) {
                        case GROWING_TIPS:
                            gardenFlower.setGrowingTips(getGrowingTips(reader));
                            break;
                        case NAME:
                            gardenFlower.setName(getXmlText(reader));
                            break;
                        case ORIGIN:
                            gardenFlower.setOrigin(getXmlText(reader));
                            break;
                        case MULTIPLYING:
                            gardenFlower.setMultiplying(Multiplying.fromValue(getXmlText(reader)));
                            break;
                        case VISUAL_PARAMETERS:
                            gardenFlower.setVisualParameters(getVisualParameters(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if ( FlowerEnum.fromValue(name) == FlowerEnum.GARDEN_FLOWER ) {
                        return gardenFlower;
                    }
            }
        }
        throw new XmlParserException("Unknown element.");
    }

    private GrowingTips getGrowingTips(XMLStreamReader reader) throws XmlParserException, XMLStreamException {
        GrowingTips growingTips = new GrowingTips();
        while (reader.hasNext()) {
            int type = reader.next();
            String name;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.fromValue(name)) {
                        case TEMPERATURE:
                            growingTips.setTemperature(Double.parseDouble(getXmlText(reader)));
                            break;
                        case LIGHTING:
                            growingTips.setLighting(Boolean.parseBoolean(getXmlText(reader)));
                            break;
                        case WATERING:
                            growingTips.setWatering(Double.parseDouble(getXmlText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if ( FlowerEnum.fromValue(name) == FlowerEnum.GROWING_TIPS ) {
                        return growingTips;
                    }
            }
        }
        throw new XmlParserException("Unknown element.");
    }

    private VisualParameters getVisualParameters(XMLStreamReader reader) throws XMLStreamException, XmlParserException {
        VisualParameters visualParameters = new VisualParameters();
        while (reader.hasNext()) {
            int type = reader.next();
            String name;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.fromValue(name)) {
                        case STEM_COLOR:
                            visualParameters.setStemColor(getXmlText(reader));
                            break;
                        case LEAF_COLOR:
                            visualParameters.setLeafColor(getXmlText(reader));
                            break;
                        case FLOWER_HEIGHT:
                            double flowerHeight = Double.parseDouble(getXmlText(reader));
                            visualParameters.setFlowerHeight(flowerHeight);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if ( FlowerEnum.fromValue(name) == FlowerEnum.VISUAL_PARAMETERS ) {
                        return visualParameters;
                    }
            }
        }
        throw new XmlParserException("Unknown element.");
    }

    private String getXmlText(XMLStreamReader reader) throws XMLStreamException {
        String text = "";
        if ( reader.hasNext() ) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
