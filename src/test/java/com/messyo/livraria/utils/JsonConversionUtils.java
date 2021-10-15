package com.messyo.livraria.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.messyo.livraria.editora.dto.EditoraDTO;

public class JsonConversionUtils {
    public static String asJsonString(EditoraDTO expectedCreatedEditoraDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(expectedCreatedEditoraDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
