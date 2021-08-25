package org.examples.jacksontest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import java.util.Objects;

public class MyType implements IMyType {

    @JsonCreator
    public MyType(
            @JsonProperty("defaultValue") @Nullable OtherType defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Nullable
    public final IOtherType defaultValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyType myType = (MyType) o;
        return Objects.equals(defaultValue, myType.defaultValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultValue);
    }
}
