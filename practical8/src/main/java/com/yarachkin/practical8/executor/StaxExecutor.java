package com.yarachkin.practical8.executor;

import com.yarachkin.practical8.exception.XmlParserException;
import com.yarachkin.practical8.parser.FlowersStaxBuilder;

public class StaxExecutor {
    public static void main(String[] args) throws XmlParserException {
        FlowersStaxBuilder staxBuilder = new FlowersStaxBuilder();
        staxBuilder.buildListFlowers("src/main/resources/flowers.xml");
        staxBuilder.getFlowers().forEach(System.out::println);
    }
}
