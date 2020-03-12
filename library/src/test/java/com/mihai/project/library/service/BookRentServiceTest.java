package com.mihai.project.library.service;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.rent.BookRentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BookRentServiceTest {

    @Mock
    private BookRentService bookRentResource;


    @Test
    public void given_whenMarkBookRentAsLateIfExist_returnEmpty(){
        when(bookRentResource.markBookRentAsLateIfExist()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>().size(), bookRentResource.markBookRentAsLateIfExist().size());
    }

    @Test
    public void given_whenCheckIfUserAlreadyHasARentForCurrentBook_throwException(){
        Book book = new Book();
        User user = new User();
        when(bookRentResource.checkIfUserAlreadyHasARentForCurrentBook(book, user)).thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class, ()->bookRentResource.checkIfUserAlreadyHasARentForCurrentBook(book,user));
    }

}
