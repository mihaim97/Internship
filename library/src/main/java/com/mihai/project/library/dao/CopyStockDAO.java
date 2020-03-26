package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.entity.stock.CopyStock;

import java.util.List;

public interface CopyStockDAO {

    CopyStock addBookCopy(CopyStock copyStock);

    CopyStock queryCopyStock(int id);

    List<CopyStock> queryAllBookCopy(int bookId);

    List<CopyStock> querySingleBookCopyByBookId(int bookId);

    List<CopyStock> queryAllCopy();

    boolean deleteCopy(CopyStock copyStock);

    List<RentRequest> checkForRentRequestOnCurrentBook(Book book);

}
