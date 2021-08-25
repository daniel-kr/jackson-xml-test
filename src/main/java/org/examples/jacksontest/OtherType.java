package org.examples.jacksontest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class OtherType {
    @JsonCreator
    public OtherType(
            @JsonProperty("defaultValues") @JsonSetter(nulls = Nulls.AS_EMPTY) List<String> defaultValues,
            @JsonProperty("typeNames") @JsonSetter(nulls = Nulls.AS_EMPTY) Collection<String> typeNames,
            @JsonProperty("prop") @JsonSetter(nulls = Nulls.AS_EMPTY) Collection<String> prop) {
        this.typeNames = typeNames;
        this.defaultValues = defaultValues;
        this.prop = prop;
    }

    public final List<String> defaultValues;
    public final Collection<String> typeNames;
    public final Collection<String> prop;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OtherType otherType = (OtherType) o;
        return Objects.equals(defaultValues, otherType.defaultValues) && Objects.equals(typeNames, otherType.typeNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultValues, typeNames);
    }
}
