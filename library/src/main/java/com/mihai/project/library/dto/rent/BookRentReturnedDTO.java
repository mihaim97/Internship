package com.mihai.project.library.dto.rent;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BookRentReturnedDTO {

    @NotNull
    @Min(1)
    private int rentId;

    @NotNull
    @Min(1)
    @Max(5)
    private float note;

    public BookRentReturnedDTO(@NotNull @Min(1) int rentId, @NotNull @Min(1) @Max(5) float note) {
        this.rentId = rentId;
        this.note = note;
    }

    public BookRentReturnedDTO() {
    }

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
