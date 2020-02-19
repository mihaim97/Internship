package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.BookDesc;

public interface BookDescriptionDAO {
    public void addBookDescription(int bookId, BookDesc bookDesc);
}
