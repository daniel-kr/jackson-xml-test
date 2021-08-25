package org.examples.jacksontest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;
import java.util.*;

public class MyType implements IMyType {

    @JsonCreator
    public MyType(
            @JsonProperty("map") @Nullable Map<String, String> map,
            @JsonProperty("defaultValues") @Nullable OtherType defaultValue,
            @JsonProperty("otherTypes") @JsonSetter(nulls = Nulls.AS_EMPTY) @NonNull List<OtherType> otherTypes) {
        this.otherTypes = otherTypes;
        this.defaultValue = defaultValue;
        if (map != null) {
            this.map.putAll(map);
        }
    }

    @Nullable
    public final OtherType defaultValue;
    @NonNull
    public final List<OtherType> otherTypes;

    @JacksonImportExportMapParameters(key = String.class, value = String.class)
    public final Map<String, String> map = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyType myType = (MyType) o;
        return Objects.equals(defaultValue, myType.defaultValue) && otherTypes.equals(myType.otherTypes) && Objects.equals(map, myType.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultValue, otherTypes, map);
    }
}
