package com.mihai.project.library.entity.nativequery;

public class TopEmployees {

    private int id;

    private String username;

    private int bookRead;

    public TopEmployees() {
    }

    public TopEmployees(Number id, String username, Number bookRead) {
        this.id = id.intValue();
        this.username = username;
        this.bookRead = bookRead.intValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookRead() {
        return bookRead;
    }

    public void setBookRead(int bookRead) {
        this.bookRead = bookRead;
    }
}
