package com.mihai.project.library.service.stock;

import com.mihai.project.library.dao.CopyStockDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.util.enumeration.CopyFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CopyStockServiceImpl implements CopyStockService {

    @Autowired
    private CopyStockDAO copyStockDAO;

    @Override
    @Transactional
    public void addBookCopy(Book book, int copyNumber) {
        while (copyNumber != 0) {
            copyStockDAO.addBookCopy(new CopyStock(CopyFlag.AV.toString(), CopyFlag.AV.toString(), book));
            copyNumber--;
        }
    }
}
