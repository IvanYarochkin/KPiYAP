package com.yarachkin.practical8.executor;

import com.yarachkin.practical8.exception.XmlParserException;
import com.yarachkin.practical8.parser.FlowersDomBuilder;

public class DomExecutor {
    public static void main(String[] args) throws XmlParserException {
        FlowersDomBuilder flowersDomBuilder = new FlowersDomBuilder();
        flowersDomBuilder.buildListFlowers("src/main/resources/flowers.xml");
        flowersDomBuilder.getFlowers().forEach(System.out::println);
    }
}
