package com.mihai.project.library.dto.rent;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class BookRentDTOOut {

    @NotNull
    @Min(1)
    private int id;

    @NotNull
    private Date dateRent;

    @NotNull
    private Date endDateRent;

    @NotBlank
    private String status;

    @NotNull
    @Min(1)
    private float note;

    @NotNull
    @Min(1)
    private int copy;

    @NotNull
    @Min(1)
    private int book;

    @NotNull
    @Min(1)
    private int user;

    public BookRentDTOOut() {
    }

    public BookRentDTOOut(@NotNull @Min(1) int id, @NotNull Date dateRent, @NotNull Date endDateRent, @NotBlank String status, @NotNull @Min(1) float note, @NotNull @Min(1) int copy, @NotNull @Min(1) int book, @NotNull @Min(1) int user) {
        this.id = id;
        this.dateRent = dateRent;
        this.endDateRent = endDateRent;
        this.status = status;
        this.note = note;
        this.copy = copy;
        this.book = book;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateRent() {
        return dateRent;
    }

    public void setDateRent(Date dateRent) {
        this.dateRent = dateRent;
    }

    public Date getEndDateRent() {
        return endDateRent;
    }

    public void setEndDateRent(Date endDateRent) {
        this.endDateRent = endDateRent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
