package com.mihai.project.library.entity.rent;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.entity.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book_rent")
public class BookRent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_rent")
    private Date dateRent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_rent")
    private Date endDateRent;

    @Column(name = "status")
    private String status;

    @Column(name = "note")
    private float note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_copy")
    private CopyStock copy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private User user;

    public BookRent() {
    }

    public BookRent(int id, Date dateRent, Date endDateRent, String status, float note, CopyStock copy, Book book, User user) {
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

    public CopyStock getCopy() {
        return copy;
    }

    public void setCopy(CopyStock copy) {
        this.copy = copy;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookRent)) return false;
        BookRent bookRent = (BookRent) o;
        return Float.compare(bookRent.note, note) == 0 &&
                Objects.equals(dateRent, bookRent.dateRent) &&
                Objects.equals(endDateRent, bookRent.endDateRent) &&
                Objects.equals(status, bookRent.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateRent, endDateRent, status, note);
    }
}
