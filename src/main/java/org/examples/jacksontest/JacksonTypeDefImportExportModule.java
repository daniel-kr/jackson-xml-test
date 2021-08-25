package org.examples.jacksontest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.Map;


/**
 * Jackson import and export module for cplace classes.
 * Wrapper for all custom (de)serialization implementations. Has to be
 * included into the XmlMapper using
 * <code>xmlMapper.registerModule(new JacksonTypeDefImportExportModule);</code>
 */
public class JacksonTypeDefImportExportModule extends SimpleModule {

    public JacksonTypeDefImportExportModule() {
        initMap();
    }

    private void initMap() {
        this.addDeserializer(Map.class, new AnnotationBasedGenericMapDeserializer());
        this.addSerializer(Map.class, new GenericMapSerializer());
    }
}
