package com.mihai.project.library.util.message.user;

public interface UserMessageBuilder {

    String getMessageOnUserNotFind(int id);

    String getMessageOnEmailAlreadyExist(String username);

    String getMessageOnUserSuccessfullyDeleted(String username);

    String getMessageOnUserExistException(String username);

    String getMessageOnNoSuchUserToDeleteOrUpdate(String username);
}
