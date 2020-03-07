package com.mihai.project.library.util.message.rent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mihai.project.library.util.mapper.JsonMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookRentMessageBuilderImpl implements BookRentMessageBuilder {

    private static Logger logger = LoggerFactory.getLogger(BookRentMessageBuilderImpl.class);

    @Override
    public String getMessageOnNoCopyAvailable() {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("No book available, please register for one");
        } catch (JsonProcessingException exc) {
            logger.error("Fail to parse json in " + BookRentMessageBuilderImpl.class + " getMessageOnNoCopyAvailable()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnUserAlreadyRentABookWithId(int id) {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("User already rent a book with id " + id);
        } catch (JsonProcessingException exc) {
            logger.error("Fail to parse json in " + BookRentMessageBuilderImpl.class + " getMessageOnUserAlreadyRentABookWithId()");
        }
        return toJson;
    }
}

