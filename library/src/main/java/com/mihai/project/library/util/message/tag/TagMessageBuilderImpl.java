package com.mihai.project.library.util.message.tag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mihai.project.library.util.mapper.JsonMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TagMessageBuilderImpl implements TagMessageBuilder {

    private static Logger logger = LoggerFactory.getLogger(TagMessageBuilderImpl.class);

    @Override
    public String getMessageOnTagExist(String name) {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Tag " + name + " exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + TagMessageBuilderImpl.class + " getMessageOnTagExist()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnNoTagWithId(int id) {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("No tag with id " + id);
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + TagMessageBuilderImpl.class + " getMessageOnNoTagExistWithId()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnNoTagWithName(String name) {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("No tag with name " + name);
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + TagMessageBuilderImpl.class + " getMessageOnNoTagExistWithName()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnSuccessfullyDeleted(int id) {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Tag with id " + id + " was successfully deleted");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + TagMessageBuilderImpl.class + " getMessageOnNoTagExistWithName()");
        }
        return toJson;
    }

}
