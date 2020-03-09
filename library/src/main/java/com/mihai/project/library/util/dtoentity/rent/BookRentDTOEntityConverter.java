package com.mihai.project.library.util.dtoentity.rent;

import com.mihai.project.library.dto.rent.BookRentDTOOut;
import com.mihai.project.library.entity.rent.BookRent;

public interface BookRentDTOEntityConverter {

    BookRentDTOOut fromBookRentToDtoOut(BookRent bookRent);

}
