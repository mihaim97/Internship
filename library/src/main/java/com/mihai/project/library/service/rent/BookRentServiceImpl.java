package com.mihai.project.library.service.rent;

import com.mihai.project.library.dao.BookRentDAO;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.service.stock.CopyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRentServiceImpl implements BookRentService {

    @Autowired
    private BookRentDAO bookRentDAO;

    @Autowired
    private CopyStockService copyStockService;

    @Override
    public BookRent registerBookRent(BookRent bookRent) {
        return null;
    }

    @Override
    public List<BookRent> queryAllBookRent() {
        return null;
    }
}
