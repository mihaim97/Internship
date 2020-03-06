package com.mihai.project.library.dto.rent;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BookRentDTO {

    @NotNull
    @Min(1)
    private int bookToRentId;

    @NotNull
    @Min(1)
    private int userId;

    public BookRentDTO() {
    }

    public BookRentDTO(@NotNull @Min(1) int bookToRentId, @NotNull @Min(1) int userId) {
        this.bookToRentId = bookToRentId;
        this.userId = userId;
    }

    public int getBookToRentId() {
        return bookToRentId;
    }

    public void setBookToRentId(int bookToRentId) {
        this.bookToRentId = bookToRentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
