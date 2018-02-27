package com.yarachkin.practical8.parser;

import com.yarachkin.practical8.entity.CutFlower;
import com.yarachkin.practical8.entity.Flower;
import com.yarachkin.practical8.entity.GardenFlower;
import com.yarachkin.practical8.entity.Multiplying;
import com.yarachkin.practical8.exception.XmlParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class SaxFlowerHandler extends DefaultHandler {
    private final static Logger LOGGER = LogManager.getRootLogger();

    private List<Flower> flowers;
    private Flower current;
    private FlowerEnum currentEnum;
    private EnumSet<FlowerEnum> withText;
    private EnumSet<FlowerEnum> withNumber;
    private EnumSet<FlowerEnum> withBoolean;

    public SaxFlowerHandler() {
        flowers = new ArrayList<>();
        withText = EnumSet.range(FlowerEnum.MULTIPLYING, FlowerEnum.SOIL);
        withNumber = EnumSet.range(FlowerEnum.FLOWER_HEIGHT, FlowerEnum.TEMPERATURE);
        withBoolean = EnumSet.of(FlowerEnum.LIGHTING);
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startDocument() throws SAXException {
        LOGGER.log(Level.INFO, "Start a SAX parsing.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ( "cut-flower".equals(localName) ) {
            CutFlower cutFlower = new CutFlower();
            cutFlower.setId(attributes.getValue(0));
            if ( attributes.getLength() == 2 ) {
                cutFlower.setFreshness(Integer.parseInt(attributes.getValue(1)));
            }
            current = cutFlower;
        } else if ( "garden-flower".equals(localName) ) {
            GardenFlower gardenFlower = new GardenFlower();
            gardenFlower.setId(attributes.getValue(0));
            if ( attributes.getLength() == 2 ) {
                gardenFlower.setSoil(attributes.getValue(1));
            }
            current = gardenFlower;
        } else {
            try {
                FlowerEnum temp = FlowerEnum.fromValue(localName);
                if ( withText.contains(temp) | withNumber.contains(temp) | withBoolean.contains(temp) ) {
                    currentEnum = temp;
                }
            } catch (XmlParserException e) {
                LOGGER.log(Level.INFO, e.getMessage());
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();
        if ( currentEnum != null ) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case ORIGIN:
                    current.setOrigin(s);
                    break;
                case STEM_COLOR:
                    current.getVisualParameters().setStemColor(s);
                    break;
                case LEAF_COLOR:
                    current.getVisualParameters().setLeafColor(s);
                    break;
                case FLOWER_HEIGHT:
                    current.getVisualParameters().setFlowerHeight(Double.parseDouble(s));
                    break;
                case MULTIPLYING:
                    current.setMultiplying(Multiplying.fromValue(s));
                    break;
            }
            if ( current instanceof GardenFlower ) {
                GardenFlower gardenFlower = (GardenFlower) current;
                switch (currentEnum) {
                    case TEMPERATURE:
                        gardenFlower.getGrowingTips().setTemperature(Double.parseDouble(s));
                        break;
                    case LIGHTING:
                        gardenFlower.getGrowingTips().setLighting(Boolean.parseBoolean(s));
                        break;
                    case WATERING:
                        gardenFlower.getGrowingTips().setWatering(Double.parseDouble(s));
                        break;
                }
                current = gardenFlower;
            }
        }
        currentEnum = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ( "cut-flower".equals(localName) || "garden-flower".equals(localName) ) {
            flowers.add(current);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        LOGGER.log(Level.INFO, "A SAX parsing is over.");
    }
}
