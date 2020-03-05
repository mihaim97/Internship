package com.mihai.project.library.util.message.tag;

public interface TagMessageBuilder {

    String getMessageOnTagExist(String name);

    String getMessageOnNoTagWithId(int id);

    String getMessageOnNoTagWithName(String name);

    String getMessageOnSuccessfullyDeleted(int id);

}
