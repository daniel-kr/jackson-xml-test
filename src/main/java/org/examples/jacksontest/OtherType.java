package org.examples.jacksontest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.util.Collection;
import java.util.Objects;

public class OtherType implements IOtherType {
    @JsonCreator
    public OtherType(
            @JsonProperty("typeNames") @JsonSetter(nulls = Nulls.AS_EMPTY) Collection<String> typeNames) {
        this.typeNames = typeNames;
    }

    public final Collection<String> typeNames;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OtherType otherType = (OtherType) o;
        return Objects.equals(typeNames, otherType.typeNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeNames);
    }
}
