package com.mihai.project.library.entity.request;

import javax.persistence.*;

@Entity
@Table(name = "book_request")
public class BookRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "pubCompany")
    private String publishingCompany;

    @Column(name = "link")
    private String link;

    @Column(name = "copyNum")
    private int copyNumber;

    @Column(name = "cost")
    private float cost;

    @Column(name = "status")
    private String status;

    public BookRequest() {
    }

    public BookRequest(int id, String title, String author, String publishingCompany, String link, int copyNumber, float cost, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishingCompany = publishingCompany;
        this.link = link;
        this.copyNumber = copyNumber;
        this.cost = cost;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
