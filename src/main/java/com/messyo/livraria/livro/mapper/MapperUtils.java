package com.messyo.livraria.livro.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MapperUtils {
    public static String localDateTimeToString(LocalDateTime timestamp) {
        if (timestamp == null)
            return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.from(timestamp).format(formatter);
    }

    public static LocalDateTime stringToLocalDateTime(String timestamp) {
        if (timestamp == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(timestamp, formatter);
    }
}
