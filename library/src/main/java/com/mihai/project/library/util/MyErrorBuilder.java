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


    //@@ Simple Helper
    class SimpleMessageHelper{
        private String message;

        public SimpleMessageHelper(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
