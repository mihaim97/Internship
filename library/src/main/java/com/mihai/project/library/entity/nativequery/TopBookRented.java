package com.mihai.project.library.entity.nativequery;

public class TopBookRented {

    private String title;

    private int number;

    public TopBookRented() {
    }

    public TopBookRented(String title, Number number) {
        this.title = title;
        this.number = number.intValue();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
