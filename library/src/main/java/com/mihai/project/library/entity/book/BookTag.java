package com.mihai.project.library.entity.book;

public class BookTag {

    private int id;
    private String tag;

    public BookTag() { }

    public BookTag(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
