package com.mihai.project.library.service.stock;

import com.mihai.project.library.entity.book.Book;

public interface CopyStockService {

    void addBookCopy(Book book, int copyNumber);
}
