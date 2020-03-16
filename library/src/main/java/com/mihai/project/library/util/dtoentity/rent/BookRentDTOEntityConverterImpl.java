package com.mihai.project.library.util.dtoentity.rent;

import com.mihai.project.library.dto.rent.BookRentDTOOut;
import com.mihai.project.library.entity.rent.BookRent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRentDTOEntityConverterImpl implements BookRentDTOEntityConverter {

    @Override
    public BookRentDTOOut fromBookRentToDtoOut(BookRent bookRent) {
        BookRentDTOOut bookRentDTOOut = new BookRentDTOOut();
        bookRentDTOOut.setBook(bookRent.getBook().getId());
        bookRentDTOOut.setCopy(bookRent.getCopy().getCode());
        bookRentDTOOut.setDateRent(bookRent.getDateRent());
        bookRentDTOOut.setEndDateRent(bookRent.getEndDateRent());
        bookRentDTOOut.setId(bookRent.getId());
        bookRentDTOOut.setNote(bookRent.getNote());
        bookRentDTOOut.setUser(bookRent.getUser().getId());
        bookRentDTOOut.setStatus(bookRent.getStatus());
        return bookRentDTOOut;
    }

    @Override
    public List<BookRentDTOOut> fromBookRentListToDtoOutList(List<BookRent> bookRents) {
        List<BookRentDTOOut> bookRentDTOOuts = new ArrayList<>();
        bookRents.stream().forEach(bookRent -> {
            bookRentDTOOuts.add(fromBookRentToDtoOut(bookRent));
        });
        return bookRentDTOOuts;
    }

}
