package com.mihai.project.library.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class RentRequestDTOOut {

    @NotNull
    @Min(1)
    private int id;

    @NotNull
    private Date dateRequest;

    @NotBlank
    private String status;

    @NotNull
    @Min(1)
    private int bookId;

    @NotNull
    @Min(1)
    private int user;

    public RentRequestDTOOut() {
    }

    public RentRequestDTOOut(@NotNull @Min(1) int id, @NotNull Date dateRequest, @NotBlank String status, @NotNull @Min(1) int bookId, @NotNull @Min(1) int user) {
        this.id = id;
        this.dateRequest = dateRequest;
        this.status = status;
        this.bookId = bookId;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
