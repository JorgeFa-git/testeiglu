package br.com.testeiglu.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonHelper {

    private static final ObjectMapper mapper = createObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(JsonHelper.class);

    public static byte[] toJson(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    public static <T> T toObject(byte[] json, Class<T> javaClass) {
        try {
            return mapper.readValue(json, javaClass);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
