package org.examples.jacksontest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.base.Preconditions;

import java.util.List;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        XmlMapper xmlMapper = XmlMapper.builder()
                //.configure(FromXmlParser.Feature.EMPTY_ELEMENT_AS_NULL, true)
                //.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .defaultUseWrapper(false).build();

        MyType myType = new MyType("hello", List.of("type1", "type2"));

        String stringValue = xmlMapper.writeValueAsString(myType);
        System.out.println(stringValue);
        IMyType outputType = xmlMapper.readValue(stringValue, IMyType.class);
        Preconditions.checkState(myType.equals(outputType));
    }

}
