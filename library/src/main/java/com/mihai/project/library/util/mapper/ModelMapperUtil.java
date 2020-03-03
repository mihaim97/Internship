package com.mihai.project.library.util.mapper;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

    private static ModelMapper mapper;

    public static ModelMapper getMapper() {
        if (mapper == null) {
            synchronized (ModelMapperUtil.class) {
                if (mapper == null) {
                    mapper = new ModelMapper();
                }
            }
        }
        return mapper;
    }

}
