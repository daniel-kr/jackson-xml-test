package org.examples.jacksontest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Map serializer that writes nice entry/key/value tags for its content.
 * Works well in conjunction with {@link AnnotationBasedGenericMapDeserializer}.
 */
public class GenericMapSerializer extends StdSerializer<Map> {

    static final String
            ENTRY_TAG_NAME = "entry",
            KEY_TAG_NAME = "key",
            VALUE_TAG_NAME = "value";

    public GenericMapSerializer() {
        super(Map.class);
    }


    @Override
    public void serialize(Map map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(ENTRY_TAG_NAME);
        jsonGenerator.writeStartArray();

        if (map == null || map.isEmpty()) {
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
            return;
        }

        final Map<Object, Object> mapCopy = new HashMap<>(map);
        for (Map.Entry<Object, Object> entry : mapCopy.entrySet()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName(KEY_TAG_NAME);
            jsonGenerator.writeObject(entry.getKey());
            jsonGenerator.writeFieldName(VALUE_TAG_NAME);
            jsonGenerator.writeObject(entry.getValue());
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
