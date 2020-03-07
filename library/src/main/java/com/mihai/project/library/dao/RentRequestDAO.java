package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface RentRequestDAO {

    RentRequest registerRentRequest(RentRequest rentRequest, Book book, User user);

    List<RentRequest> checkIfUserHasARequestForCurrentBook(Book book, User user);

}
