package org.examples.jacksontest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Extends the default map deserializer to accept the {@link JacksonImportExportMapParameters} annotation.
 */
public class AnnotationBasedGenericMapDeserializer extends JsonDeserializer<Map<Object, Object>> implements ContextualDeserializer {

    private Class<?> key;
    private Class<?> value;


    public AnnotationBasedGenericMapDeserializer() {
        this(null, null);
    }

    private AnnotationBasedGenericMapDeserializer(Class<?> key, Class<?> value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public Map<Object, Object> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return this.deserialize(p, ctxt, new HashMap<>());
    }

    @Override
    public Map<Object, Object> deserialize(JsonParser p, DeserializationContext ctxt, Map<Object, Object> map) throws IOException {
        if (this.key == null || this.value == null) {
            throw new UnsupportedOperationException("Cannot deserialize a map if the map is not annotated with the JacksonImportExportMapParameters annotation");
        }

        // let's manually deserialize this stuff
        // why not automatically? because
        //  - XML does not know arrays (and our target format is XML)
        //  - parsing using p.readValueAsTree uses the BaseNodeDeserializer internally (as we do not have any better identified object in here)
        //  - and this one in its deserializeObject method (remember, deserializeArray cannot be used with XML) overwrites keys it has already seen
        //  - hence only the last entry would survive...

        while (GenericMapSerializer.ENTRY_TAG_NAME.equals(p.nextFieldName())) {
            p.nextToken(); // skip the entry field name token
            p.nextToken(); // skip the start object token
            p.nextToken(); // skip the key field name
            Object keyObject = ctxt.readValue(p, this.key);

            p.nextToken(); // skip the key's value (it has been parsed, but the parser did not proceed)
            p.nextToken(); // skip the value field name
            Object valueObject = ctxt.readValue(p, this.value);

            p.nextToken(); // skip the values's value (it has been parsed, but the parser did not proceed)
            map.put(keyObject, valueObject);
        }

        return map;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            JacksonImportExportMapParameters annotation = beanProperty.getAnnotation(JacksonImportExportMapParameters.class);
            if (annotation != null) {
                return new AnnotationBasedGenericMapDeserializer(annotation.key(), annotation.value());
            }
        }
        if (ctxt.getContextualType() != null) {
            JavaType type = ctxt.getContextualType();
            if (type.isMapLikeType()) {
                return new AnnotationBasedGenericMapDeserializer(type.getKeyType().getRawClass(), type.getContentType().getRawClass());
            }
        }
        return new AnnotationBasedGenericMapDeserializer(String.class, Object.class);
    }

}
