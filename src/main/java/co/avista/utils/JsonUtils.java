package co.avista.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String jsonSerializer(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
