package org.examples.jacksontest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to denote the data inside a Map.
 * This is required during XML import and export as Jackson would otherwise not know how to deal with map data.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JacksonImportExportMapParameters {
    Class key();
    Class value();
}
