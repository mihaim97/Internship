package com.mihai.project.library.util.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapperUtil {

    private static ObjectMapper jsonMapper;

    public static ObjectMapper getJsonMapper() {
        if (jsonMapper == null) {
            synchronized (JsonMapperUtil.class) {
                if (jsonMapper == null) {
                    jsonMapper = new ObjectMapper();
                }
            }
        }
        return jsonMapper;
    }
}
