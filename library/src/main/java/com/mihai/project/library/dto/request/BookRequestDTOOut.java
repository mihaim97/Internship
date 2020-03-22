package com.mihai.project.library.dto.request;

import javax.validation.constraints.*;

public class BookRequestDTOOut {

    @NotNull
    @Min(1)
    private int id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String publishingCompany;

    @NotBlank
    private String link;

    @NotNull
    @Min(1)
    private int copyNumber;

    @NotNull
    private float cost;

    @NotBlank
    @Pattern(regexp = "PE|AC|RJ")
    private String status;

    public BookRequestDTOOut() {
    }

    public BookRequestDTOOut(@NotNull @Min(1) int id, @NotBlank @Size(min = 3, max = 50) String title, @NotBlank String author, @NotBlank String publishingCompany, @NotBlank String link, @NotNull @Min(1) int copyNumber, @NotNull float cost, @NotBlank @Pattern(regexp = "PE|AC|RJ") String status) {
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
