package com.mihai.project.library.entity.nativequery;

import java.util.Date;

public class TopBookEntity {

    private Integer topBook;

    private Date startDate;

    private Date endDate;

    public TopBookEntity() {
    }

    public TopBookEntity(Integer topBook, Date startDate, Date endDate) {
        this.topBook = topBook;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getTopBook() {
        return topBook;
    }

    public void setTopBook(Integer topBook) {
        this.topBook = topBook;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
