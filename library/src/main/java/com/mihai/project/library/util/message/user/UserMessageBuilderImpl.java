package com.mihai.project.library.util.message.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mihai.project.library.util.mapper.JsonMapperUtil;
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
            logger.error("Fail to parse json in " + UserMessageBuilderImpl.class + " userNotFind()");
        }
        return toJson;
    }
}
