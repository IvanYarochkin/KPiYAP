package com.yarachkin.practical8.parser;

import com.yarachkin.practical8.entity.CutFlower;
import com.yarachkin.practical8.entity.Flower;
import com.yarachkin.practical8.entity.GardenFlower;
import com.yarachkin.practical8.entity.Multiplying;
import com.yarachkin.practical8.exception.XmlParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlowersDomBuilder {
    private List<Flower> flowers;
    private DocumentBuilder documentBuilder;

    public FlowersDomBuilder() throws XmlParserException {
        flowers = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new XmlParserException(e);
        }
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void buildListFlowers(String filePath) {
        try {
            Document document = documentBuilder.parse(filePath);
            Element root = document.getDocumentElement();
            NodeList cutFlowersList = root.getElementsByTagName("cut-flower");
            NodeList gardenFlowersList = root.getElementsByTagName("garden-flower");

            for (int i = 0; i < cutFlowersList.getLength(); i++) {
                Element flowerElement = (Element) cutFlowersList.item(i);
                Flower flower = buildCutFlower(flowerElement);
                flowers.add(flower);
            }

            for (int i = 0; i < gardenFlowersList.getLength(); i++) {
                Element flowerElement = (Element) gardenFlowersList.item(i);
                Flower flower = buildGardenFlower(flowerElement);
                flowers.add(flower);
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private Flower buildCutFlower(Element flowerElement) {
        CutFlower cutFlower = new CutFlower();
        if ( !"".equals(flowerElement.getAttribute("freshness")) ) {
            cutFlower.setFreshness(Integer.parseInt(flowerElement.getAttribute("freshness")));
        }

        return buildFlower(flowerElement, cutFlower);
    }

    private Flower buildGardenFlower(Element flowerElement) {
        GardenFlower gardenFlower = new GardenFlower();
        if ( !"".equals(flowerElement.getAttribute("soil")) ) {
            gardenFlower.setSoil(flowerElement.getAttribute("soil"));
        }
        double temperature = Double.parseDouble(getElementTextContent(flowerElement, "temperature"));
        gardenFlower.getGrowingTips().setTemperature(temperature);
        double watering = Double.parseDouble(getElementTextContent(flowerElement, "watering"));
        gardenFlower.getGrowingTips().setWatering(watering);
        boolean lighting = Boolean.parseBoolean(getElementTextContent(flowerElement, "lighting"));
        gardenFlower.getGrowingTips().setLighting(lighting);
        return buildFlower(flowerElement, gardenFlower);
    }

    private Flower buildFlower(Element flowerElement, Flower flower) {
        flower.setId(flowerElement.getAttribute("id"));
        flower.setName(getElementTextContent(flowerElement, "name"));
        flower.setOrigin(getElementTextContent(flowerElement, "origin"));
        flower.setMultiplying(Multiplying.fromValue(getElementTextContent(flowerElement, "multiplying")));

        String flowerHeight = getElementTextContent(flowerElement, "flower-height");
        flower.getVisualParameters().setFlowerHeight(Double.parseDouble(flowerHeight));

        flower.getVisualParameters().setLeafColor(getElementTextContent(flowerElement, "leaf-color"));
        flower.getVisualParameters().setStemColor(getElementTextContent(flowerElement, "stem-color"));

        return flower;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
