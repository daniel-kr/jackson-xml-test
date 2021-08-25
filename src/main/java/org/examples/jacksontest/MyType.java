package org.examples.jacksontest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyType myType = (MyType) o;
        return Objects.equals(stringValue, myType.stringValue) && Objects.equals(typeNames, myType.typeNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringValue, typeNames);
    }
}
