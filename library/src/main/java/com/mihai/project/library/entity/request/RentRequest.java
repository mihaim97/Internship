package com.mihai.project.library.entity.request;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="rent_request")
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_request")
    private Date dateRequest;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book bookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User user;

    @OneToOne(mappedBy = "rentRequestId", fetch = FetchType.LAZY)
    private Pending pending;

    public RentRequest() {
    }

    public RentRequest(int id, Date dateRequest, String status, Book bookId, User user) {
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

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pending getPending() {
        return pending;
    }

    public void setPending(Pending pending) {
        this.pending = pending;
    }
}
