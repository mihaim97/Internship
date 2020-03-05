package com.mihai.project.library.dao;

import com.mihai.project.library.entity.stock.CopyStock;

import java.util.List;

public interface CopyStockDAO {

    CopyStock addBookCopy(CopyStock copyStock);

    CopyStock queryCopyStock(int id);

    List<CopyStock> queryAllBookCopy(int bookId);

    List<CopyStock> queryAllCopy();

    boolean deleteCopy(CopyStock copyStock);

    CopyStock updateCopy(CopyStock copyToUpdate, CopyStock newValue);
}
