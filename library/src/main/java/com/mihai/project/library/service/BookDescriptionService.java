package com.mihai.project.library.service;

import com.mihai.project.library.entity.book.BookDesc;

public interface BookDescriptionService {
    public void addBookDescription(int bookId, BookDesc bookDesc);
}
