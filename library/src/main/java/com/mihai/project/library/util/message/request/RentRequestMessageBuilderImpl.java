package com.mihai.project.library.util.message.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mihai.project.library.util.mapper.JsonMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RentRequestMessageBuilderImpl implements RentRequestMessageBuilder {

    private static Logger logger = LoggerFactory.getLogger(RentRequestMessageBuilderImpl.class);

    @Override
    public String getMessageOnRentRequestExist() {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("User already has a rent request for this book");
        } catch (JsonProcessingException exc) {
            logger.error("Fail to parse json in " + RentRequestMessageBuilderImpl.class + " getMessageOnRentRequestExist()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnCopyAreAvailable() {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Copy are available, you can't make a rent request");
        } catch (JsonProcessingException exc) {
            logger.error("Fail to parse json in " + RentRequestMessageBuilderImpl.class + " getMessageOnCopyAreAvailable()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnUserAlreadyHasARent() {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("User has a rent for this book");
        } catch (JsonProcessingException exc) {
            logger.error("Fail to parse json in " + RentRequestMessageBuilderImpl.class + " getMessageOnUserAlreadyHasARent()");
        }
        return toJson;
    }
}
