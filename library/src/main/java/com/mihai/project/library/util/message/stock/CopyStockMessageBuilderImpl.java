package com.mihai.project.library.util.message.stock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mihai.project.library.util.mapper.JsonMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CopyStockMessageBuilderImpl implements CopyStockMessageBuilder {

    private static Logger logger = LoggerFactory.getLogger(CopyStockMessageBuilderImpl.class);

    @Override
    public String getMessageOnNoCopyWithCode(int code) {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("No copy with code " + code);
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + CopyStockMessageBuilderImpl.class + " getMessageOnNoCopyWithCode()");
        }
        return toJson;
    }

    @Override
    public String getMessageOnCopySuccessfullyDeleted(int code) {
        String toJson = "";
        try {
            toJson = JsonMapperUtil.getJsonMapper().writeValueAsString("Copy with code " + code + " successfully deleted");
        }catch (JsonProcessingException exc){
            logger.error("Fail to parse json in " + CopyStockMessageBuilderImpl.class + " getMessageOnCopySuccessfullyDeleted()");
        }
        return toJson;
    }
}
