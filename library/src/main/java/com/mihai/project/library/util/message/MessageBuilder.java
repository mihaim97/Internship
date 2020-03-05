package com.mihai.project.library.util.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mihai.project.library.dto.errormodel.NullFieldDTO;
import com.mihai.project.library.util.mapper.JsonMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageBuilder {

    private static final Logger logger = LoggerFactory.getLogger(MessageBuilder.class);

    public String getErrorMessageFromResultBinding(BindingResult bindingResult){
        List<NullFieldDTO> buildErrorMessageList = new ArrayList<>();
        String toJson = "";
        bindingResult.getFieldErrors().stream().forEach(err->{
            buildErrorMessageList.add(new NullFieldDTO(err.getField(), err.getDefaultMessage()));
        });
        try{
           toJson  = JsonMapperUtil.getJsonMapper().writeValueAsString(buildErrorMessageList);
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MessageBuilder.class + " getErrorMessageFromResultBinding()");
        }
        return toJson;
    }

    public String getErrorMessageOnIncorrectBookIdException(int id){
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("No book with id " + id);
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MessageBuilder.class + " getErrorMessageOnIncorrectBookIdException()");
        }
        return toJson;
    }

    public String getErrorMessageOnUserExistException(String username){
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Employee with username " + username + " already exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MessageBuilder.class + " getErrorMessageOnUserExistException()");
        }
        return toJson;
    }

    public String getErrorMessageOnEmailAlreadyExist(String email){
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Email " + email + " already exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MessageBuilder.class + " getErrorMessageOnEmailAlreadyExist()");
        }
        return toJson;
    }

    public String getErrorMessageOnNoSuchUserToDeleteOrUpdate(String username){
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Employee " + username + " doesn't exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MessageBuilder.class + " getErrorMessageOnNoSuchUserToDelete()");
        }
        return toJson;
    }

    public String getMessageOnUserSuccessfullyDeleted(String username){
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Employee with username " + username + " successfully deleted");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MessageBuilder.class + " getErrorMessageOnUserSuccessfullyDeleted()");
        }
        return toJson;
    }

}
