package com.mihai.project.library.util.message.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mihai.project.library.util.mapper.JsonMapperUtil;
import com.mihai.project.library.util.message.MessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookMessageBuilderImpl implements BookMessageBuilder {

    private static final Logger logger = LoggerFactory.getLogger(BookMessageBuilderImpl.class);

    @Override
    public String getMessageOnIncorrectBookId(int id){
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("No book with id " + id);
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + BookMessageBuilderImpl.class + " getErrorMessageOnIncorrectBookIdException()");
        }
        return toJson;
    }

}
