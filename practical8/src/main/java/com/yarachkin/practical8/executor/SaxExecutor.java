package com.yarachkin.practical8.executor;

import com.yarachkin.practical8.exception.XmlParserException;
import com.yarachkin.practical8.parser.FlowersSaxBuilder;

public class SaxExecutor {
    public static void main(String[] args) throws XmlParserException {
        FlowersSaxBuilder saxBuilder = new FlowersSaxBuilder();
        saxBuilder.buildFlowers("src/main/resources/flowers.xml");
        saxBuilder.getFlowers().forEach(System.out::println);
    }
}
