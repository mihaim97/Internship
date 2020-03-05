package com.mihai.project.library.service.stock;

import com.mihai.project.library.dao.CopyStockDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.util.enumeration.StatusFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CopyStockServiceImpl implements CopyStockService {

    @Autowired
    private CopyStockDAO copyStockDAO;

    @Override
    @Transactional
    public void addBookCopy(Book book, int copyNumber) {
        while (copyNumber != 0) {
            copyStockDAO.addBookCopy(createCopyStock(book));
            copyNumber--;
        }
    }

    @Override
    @Transactional
    public CopyStock addSingleCopy(Book book, String flag, String status) {
        return copyStockDAO.addBookCopy(createCopyStock(book, flag, status));
    }

    @Override
    @Transactional
    public CopyStock queryCopyStock(int id) {
        return copyStockDAO.queryCopyStock(id);
    }

    @Override
    @Transactional
    public List<CopyStock> queryAllBookCopy(int bookId) {
        return copyStockDAO.queryAllBookCopy(bookId);
    }

    @Override
    @Transactional
    public List<CopyStock> queryAllCopy() {
        return copyStockDAO.queryAllCopy();
    }

    @Override
    @Transactional
    public boolean deleteCopy(int id) {
        CopyStock copyStock = queryCopyStock(id);
        if (copyStock != null) {
            return copyStockDAO.deleteCopy(copyStock);
        }
        return false;
    }

    @Override
    @Transactional
    public CopyStock updateCopy(CopyStock newValue) {
        CopyStock copyToUpdate = queryCopyStock(newValue.getCode());
        System.out.println(copyToUpdate);
        if(copyToUpdate != null){
            return copyStockDAO.updateCopy(copyToUpdate, newValue);
        }
        return null;
    }


    private CopyStock createCopyStock(Book book, String flag, String status) {
        return new CopyStock(flag, status, book);
    }

    private CopyStock createCopyStock(Book book) {
        return new CopyStock(StatusFlag.AV.toString(), StatusFlag.AV.toString(), book);
    }

}
