package com.mihai.project.library.util.dtoentity.rent;

import com.mihai.project.library.dto.rent.BookRentDTOOut;
import com.mihai.project.library.entity.rent.BookRent;

import java.util.List;

public interface BookRentDTOEntityConverter {

    BookRentDTOOut fromBookRentToDtoOut(BookRent bookRent);

    List<BookRentDTOOut> fromBookRentListToDtoOutList(List<BookRent> bookRents);

}
