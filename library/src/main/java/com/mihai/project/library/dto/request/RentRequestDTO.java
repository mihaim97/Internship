package com.mihai.project.library.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RentRequestDTO {

    @NotNull
    @Min(1)
    private int bookId;

    public RentRequestDTO() { }

    public RentRequestDTO(int userId, int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
