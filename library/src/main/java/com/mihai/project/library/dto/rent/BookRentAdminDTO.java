package com.mihai.project.library.dto.rent;

import com.mihai.project.library.dto.book.BookDTOQuery;
import com.mihai.project.library.dto.stock.CopyStockDTOOut;
import com.mihai.project.library.dto.user.UserDTOOut;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class BookRentAdminDTO {

    @NotNull
    @Min(1)
    private int id;

    @NotNull
    private Date dateRent;

    @NotNull
    private Date dateEnd;

    @Valid
    @NotNull
    private UserDTOOut user;

    @Valid
    @NotNull
    private BookDTOQuery book;

    @Valid
    @NotNull
    private CopyStockDTOOut copy;

    public BookRentAdminDTO() {
    }

    public BookRentAdminDTO(@NotNull @Min(1) int id, @NotNull Date dateRent, @NotNull Date dateEnd, @Valid @NotNull UserDTOOut user, @Valid @NotNull BookDTOQuery book, @Valid @NotNull CopyStockDTOOut copy) {
        this.id = id;
        this.dateRent = dateRent;
        this.dateEnd = dateEnd;
        this.user = user;
        this.book = book;
        this.copy = copy;
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

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public UserDTOOut getUser() {
        return user;
    }

    public void setUser(UserDTOOut user) {
        this.user = user;
    }

    public BookDTOQuery getBook() {
        return book;
    }

    public void setBook(BookDTOQuery book) {
        this.book = book;
    }

    public CopyStockDTOOut getCopy() {
        return copy;
    }

    public void setCopy(CopyStockDTOOut copy) {
        this.copy = copy;
    }
}
