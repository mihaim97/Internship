package com.mihai.project.library.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;

public class MyObjectMapper {
    private static ModelMapper mapper;
    private static ObjectMapper jsonMapper;

    public static ModelMapper getMapper(){
        if(mapper == null) mapper = new ModelMapper();
        return mapper;
    }

    public static ObjectMapper getJsonMapper(){
        if(jsonMapper == null) jsonMapper = new ObjectMapper();
        return jsonMapper;
    }
}
