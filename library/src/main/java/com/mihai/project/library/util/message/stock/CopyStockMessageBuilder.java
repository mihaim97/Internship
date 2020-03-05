package com.mihai.project.library.util.message.stock;

public interface CopyStockMessageBuilder {

    String getMessageOnNoCopyWithCode(int code);

    String getMessageOnCopySuccessfullyDeleted(int code);
}
