package com.mihai.project.library.util;

import org.modelmapper.ModelMapper;

public class MyObjectMapper {
    public static ModelMapper mapper;

    public static ModelMapper getMapper(){
        if(mapper == null) mapper = new ModelMapper();
        return mapper;
    }
}
