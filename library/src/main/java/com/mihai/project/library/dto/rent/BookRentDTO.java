package com.mihai.project.library.dto.rent;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BookRentDTO {

    @NotNull
    @Min(1)
    private int bookToRentId;

    @NotNull
    @Min(1)
    private int userId;

    @NotNull
    @Min(1)
    @Max(3)
    private int period;

    public BookRentDTO() {
    }

    public BookRentDTO(@NotNull @Min(1) int bookToRentId, @NotNull @Min(1) int userId, @NotNull @Min(1) @Max(3) int period) {
        this.bookToRentId = bookToRentId;
        this.userId = userId;
        this.period = period;
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
