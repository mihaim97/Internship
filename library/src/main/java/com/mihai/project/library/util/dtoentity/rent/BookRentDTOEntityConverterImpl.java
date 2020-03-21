package com.mihai.project.library.util.dtoentity.rent;

import com.mihai.project.library.dto.book.BookDTOQuery;
import com.mihai.project.library.dto.rent.BookRentAdminDTO;
import com.mihai.project.library.dto.rent.BookRentDTOOut;
import com.mihai.project.library.dto.stock.CopyStockDTOOut;
import com.mihai.project.library.dto.user.UserDTOOut;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.dtoentity.book.BookDTOEntityConverter;
import com.mihai.project.library.util.dtoentity.stock.CopyStockDTOEntityConverter;
import com.mihai.project.library.util.dtoentity.user.UserDTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRentDTOEntityConverterImpl implements BookRentDTOEntityConverter {

    @Autowired
    private UserDTOEntityConverter userDTOEntityConverter;

    @Autowired
    private BookDTOEntityConverter bookDTOEntityConverter;

    @Autowired
    private CopyStockDTOEntityConverter copyStockDTOEntityConverter;

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

    @Override
    public List<BookRentAdminDTO> fromBookRentListToAdminDto(List<BookRent> bookRents) {
        List<BookRentAdminDTO> bookRentAdminDTOS = new ArrayList<>();
        bookRents.stream().forEach(bookRent -> {
            BookRentAdminDTO rent = new BookRentAdminDTO();
            setNoonInnerData(rent, bookRent);
            setInnerUserData(rent, bookRent.getUser());
            setInnerCopy(rent, bookRent.getCopy());
            setInnerBook(rent, bookRent.getCopy().getBookId());
            bookRentAdminDTOS.add(rent);
        });
        return bookRentAdminDTOS;
    }

    private void setNoonInnerData(BookRentAdminDTO bookRentAdminDTO, BookRent bookRent) {
        bookRentAdminDTO.setId(bookRent.getId());
        bookRentAdminDTO.setDateRent(bookRent.getDateRent());
        bookRentAdminDTO.setDateEnd(bookRent.getEndDateRent());
    }

    private void setInnerUserData(BookRentAdminDTO bookRentAdminDTO, User user) {
        UserDTOOut userDTOOut = userDTOEntityConverter.fromUserToUserDTOOut(user);
        bookRentAdminDTO.setUser(userDTOOut);
    }

    private void setInnerCopy(BookRentAdminDTO bookRentAdminDTO, CopyStock copyStock) {
        CopyStockDTOOut copyStockDTOOut = copyStockDTOEntityConverter.fromCopyStockToDto(copyStock);
        bookRentAdminDTO.setCopy(copyStockDTOOut);
    }

    private void setInnerBook(BookRentAdminDTO bookRentAdminDTO, Book book) {
        BookDTOQuery bookDTOQuery = bookDTOEntityConverter.fromBookToDTO(book);
        bookRentAdminDTO.setBook(bookDTOQuery);
    }

}
