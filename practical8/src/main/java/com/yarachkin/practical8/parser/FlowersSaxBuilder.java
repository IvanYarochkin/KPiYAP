package com.yarachkin.practical8.parser;

import com.yarachkin.practical8.entity.Flower;
import com.yarachkin.practical8.exception.XmlParserException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class FlowersSaxBuilder {
    private List<Flower> flowers;
    private SaxFlowerHandler handler = new SaxFlowerHandler();
    private XMLReader reader;

    public FlowersSaxBuilder() throws XmlParserException {
        try {
            reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            throw new XmlParserException(e);
        }
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void buildFlowers(String filePath) throws XmlParserException {
        try {
            reader.parse(filePath);
        } catch (SAXException | IOException e) {
            throw new XmlParserException(e);
        }

        flowers = handler.getFlowers();
    }
}
