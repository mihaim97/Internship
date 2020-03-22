package com.mihai.project.library.util.factory;

import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.request.BookRequest;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.util.mapper.ModelMapperUtil;

public class LibraryFactoryManager {

    private static LibraryFactoryManager instance;

    private LibraryFactoryManager() {
    }

    public static LibraryFactoryManager getInstance() {
        if (instance == null) {
            synchronized (ModelMapperUtil.class) {
                if (instance == null) {
                    instance = new LibraryFactoryManager();
                }
            }
        }
        return instance;
    }

    public BookRent getBookRentInstance() {
        return new BookRent();
    }

    public RentRequest getRentRequestInstance() {
        return new RentRequest();
    }

    public Pending getPendingInstance() {
        return new Pending();
    }

    public BookRequest getBookRequestInstance(){return new BookRequest();}

}
