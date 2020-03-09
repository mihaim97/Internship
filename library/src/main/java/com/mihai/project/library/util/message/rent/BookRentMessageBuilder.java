package com.mihai.project.library.util.message.rent;

public interface BookRentMessageBuilder {

    String getMessageOnNoCopyAvailable();

    String getMessageOnUserAlreadyRentABookWithId(int id);

    String getMessageOnReturnFail();

}
