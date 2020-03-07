package com.mihai.project.library.util.message.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mihai.project.library.util.mapper.JsonMapperUtil;
import com.mihai.project.library.util.message.MessageBuilder;
import com.mihai.project.library.util.message.stock.CopyStockMessageBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserMessageBuilderImpl implements  UserMessageBuilder {

    private static Logger logger = LoggerFactory.getLogger(UserMessageBuilderImpl.class);

    @Override
    public String getMessageOnUserNotFind(int id) {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("No user with id " + id);
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + UserMessageBuilderImpl.class + " getMessageOnUserNotFind()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnEmailAlreadyExist(String email){
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Email " + email + " already exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + UserMessageBuilderImpl.class + " getMessageOnEmailAlreadyExist()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnUserSuccessfullyDeleted(String username){
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Employee with username " + username + " successfully deleted");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + UserMessageBuilderImpl.class + " getMessageOnUserSuccessfullyDeleted()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnUserExistException(String username){
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Employee with username " + username + " already exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + UserMessageBuilderImpl.class + " getMessageOnUserExistException()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnNoSuchUserToDeleteOrUpdate(String username){
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Employee " + username + " doesn't exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + UserMessageBuilderImpl.class + " getErrorMessageOnNoSuchUserToDelete()");
        }
        return toJson;
    }

}
