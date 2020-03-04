package com.mihai.project.library.entity.stock;

import com.mihai.project.library.entity.book.Book;

import javax.persistence.*;

@Entity
@Table(name = "copy_stock")
public class CopyStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(name = "flag")
    private String flag;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book bookId;

    public CopyStock(){}

    public CopyStock(int code, String flag, String status, Book bookId) {
        this.code = code;
        this.flag = flag;
        this.status = status;
        this.bookId = bookId;
    }

    public CopyStock(String flag, String status, Book bookId) {
        this.flag = flag;
        this.status = status;
        this.bookId = bookId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
}
