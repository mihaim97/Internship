package com.mihai.project.library.util.factory;

import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.util.mapper.ModelMapperUtil;

public class BookRentFactory {

    private static BookRentFactory instance;

    private BookRentFactory() {
    }

    public static BookRentFactory getInstance() {
        if (instance == null) {
            synchronized (ModelMapperUtil.class) {
                if (instance == null) {
                    instance = new BookRentFactory();
                }
            }
        }
        return instance;
    }

    public BookRent getBookRentInstance() {
        return new BookRent();
    }

}
