package com.mihai.project.library.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RentRequestDTO {

    @NotNull
    @Min(1)
    private int userId;

    @NotNull
    @Min(1)
    private int bookId;

    public RentRequestDTO() { }

    public RentRequestDTO(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
