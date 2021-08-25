package org.examples.jacksontest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class MyType implements IMyType {

    @JsonCreator
    public MyType(
            @JsonProperty("stringValue") String stringValue,
            @JsonProperty("typeNames") Collection<String> typeNames) {
        this.stringValue = stringValue;
        this.typeNames = typeNames;
    }

    public final String stringValue;
    public final Collection<String> typeNames;
}
