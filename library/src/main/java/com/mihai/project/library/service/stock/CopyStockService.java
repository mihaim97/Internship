package com.mihai.project.library.service.stock;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.stock.CopyStock;

import java.util.List;

public interface CopyStockService {

    void addBookCopy(Book book, int copyNumber);

    CopyStock addSingleCopy(Book book, String flag, String status);

    CopyStock queryCopyStock(int id);

    List<CopyStock> queryAllBookCopy(int bookId);

    CopyStock querySingleBookCopyByBookId(int bookId);

    List<CopyStock> queryAllCopy();

    boolean deleteCopy(int id);

    CopyStock updateCopy(CopyStock newValue);

}
