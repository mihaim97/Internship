package com.mihai.project.library.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mihai.project.library.dto.errormodel.NullFieldDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyErrorBuilder {

    private static final Logger logger = LoggerFactory.getLogger(MyErrorBuilder.class);

    public String getErrorMessageFromResultBinding(BindingResult bindingResult){
        List<NullFieldDTO> buildErrorMessageList = new ArrayList<>();
        String toJson = "";
        bindingResult.getFieldErrors().stream().forEach(err->{
            buildErrorMessageList.add(new NullFieldDTO(err.getField(), err.getDefaultMessage()));
        });
        try{
           toJson  = MyObjectMapper.getJsonMapper().writeValueAsString(buildErrorMessageList);
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MyErrorBuilder.class + " getErrorMessageFromResultBinding()");
        }
        return toJson;
    }

    public String getErrorMessageOnIncorrectBookIdException(int id){
        String toJson = "";
        try {
            toJson = MyObjectMapper.getJsonMapper().writeValueAsString("No book with id " + id);
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MyErrorBuilder.class + " getErrorMessageOnIncorrectBookIdException()");
        }
        return toJson;
    }

    public String getErrorMessageOnUserExistException(String username){
        String toJson = "";
        try {
            toJson = MyObjectMapper.getJsonMapper().writeValueAsString("Employee with username " + username + " already exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MyErrorBuilder.class + " getErrorMessageOnUserExistException()");
        }
        return toJson;
    }

    public String getErrorMessageOnEmailAlreadyExist(String email){
        String toJson = "";
        try {
            toJson = MyObjectMapper.getJsonMapper().writeValueAsString("Email " + email + " already exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MyErrorBuilder.class + " getErrorMessageOnEmailAlreadyExist()");
        }
        return toJson;
    }

    public String getErrorMessageOnNoSuchUserToDeleteOrUpdate(String username){
        String toJson = "";
        try {
            toJson = MyObjectMapper.getJsonMapper().writeValueAsString("Employee " + username + " doesn't not exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MyErrorBuilder.class + " getErrorMessageOnNoSuchUserToDelete()");
        }
        return toJson;
    }

    public String getMessageOnUserSuccessfullyDeleted(String username){
        String toJson = "";
        try {
            toJson = MyObjectMapper.getJsonMapper().writeValueAsString("Employee with username " + username + " successfully deleted");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MyErrorBuilder.class + " getErrorMessageOnUserSuccessfullyDeleted()");
        }
        return toJson;
    }

    public String getErrorMessageOnAuthorNotFoundException(String author){
        String toJson = "";
        try {
            toJson = MyObjectMapper.getJsonMapper().writeValueAsString("Author with name " + author + " doesn't exist");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + MyErrorBuilder.class + " getErrorMessageOnUserSuccessfullyDeleted()");
        }
        return toJson;
    }


}
