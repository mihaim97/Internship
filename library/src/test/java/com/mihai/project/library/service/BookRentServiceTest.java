package com.mihai.project.library.service;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.rent.BookRentService;
import com.mihai.project.library.util.enumeration.RentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookRentServiceTest {

    @Mock
    private BookRentService bookRentResource;


    @Test
    public void given_whenMarkBookRentAsLateIfExist_returnEmpty() {
        when(bookRentResource.markBookRentAsLateIfExist()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>().size(), bookRentResource.markBookRentAsLateIfExist().size());
    }

    @Test
    public void given_whenCheckIfUserAlreadyHasARentForCurrentBook_throwException() {
        Book book = new Book();
        User user = new User();
        when(bookRentResource.checkIfUserAlreadyHasARentForCurrentBook(book, user)).thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class, () -> bookRentResource.checkIfUserAlreadyHasARentForCurrentBook(book, user));
    }

    @Test
    public void given_whenReturnARentedBook_returnBookRent(){
        BookRent bookRent = new BookRent();
        bookRent.setStatus(RentStatus.RE.toString());
        bookRent.setId(1);
        bookRent.setNote(4.5f);
        when(bookRentResource.returnARentedBook(1, 4.5f)).thenReturn(bookRent);
        BookRent bookRentReturned = bookRentResource.returnARentedBook(1, 4.5f);
        Assertions.assertEquals(bookRent.getStatus(), bookRentReturned.getStatus());
    }

}
