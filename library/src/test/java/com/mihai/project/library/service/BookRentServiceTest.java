package com.mihai.project.library.service;

import com.mihai.project.library.contralleradvice.exception.BookRentOrRequestException;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.book.BookServiceImpl;
import com.mihai.project.library.service.rent.BookRentService;
import com.mihai.project.library.service.rent.BookRentServiceImpl;
import com.mihai.project.library.util.enumeration.RentStatus;
import com.mihai.project.library.util.message.ExceptionMessage;
import com.mihai.project.library.util.message.MessageBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookRentServiceTest {

    @Mock
    private BookRentService bookRentResource;

    private static BookRentServiceImpl bookRentImplResource;

    private static MessageBuilder messageBuilder;

    @BeforeAll
    public static void init() {
        bookRentImplResource = new BookRentServiceImpl();
        messageBuilder = new MessageBuilder();
        bookRentImplResource.setMessageBuilder(messageBuilder);
    }

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
    public void given_whenReturnARentedBook_returnBookRent() {
        BookRent bookRent = new BookRent();
        bookRent.setStatus(RentStatus.RE.toString());
        bookRent.setId(1);
        bookRent.setNote(4.5f);
        when(bookRentResource.returnARentedBook(1, 4.5f)).thenReturn(bookRent);
        BookRent bookRentReturned = bookRentResource.returnARentedBook(1, 4.5f);
        Assertions.assertEquals(bookRent.getStatus(), bookRentReturned.getStatus());
    }

    @Test
    public void givenDate_whenBannedUserFromRenting_returnBannedDays() throws NoSuchMethodException, ParseException, InvocationTargetException, IllegalAccessException {
        Date endDate = getDate("2020-03-11 12:10:30");
        Method method = bookRentImplResource.getClass().getDeclaredMethod("bannedUserFromRenting", Date.class);
        method.setAccessible(true);
        int daysBanned = (int) method.invoke(bookRentImplResource, endDate);
        Assertions.assertEquals(12, daysBanned);
    }

    @Test
    public void givenBookRent_whenExtendUserBookRent_throwsException() throws BookRentOrRequestException, ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BookRent bookRent = new BookRent();
        BookRent bookRentAfterExtend = null;
        Method extendUserBookRentMethod;
        bookRent.setDateRent(getDate("2020-02-16 12:10:30"));
        bookRent.setEndDateRent(getDate("2020-03-16 12:10:30"));
        extendUserBookRentMethod = getExtendUserBookRentMethod();
        InvocationTargetException exception = Assertions.assertThrows(InvocationTargetException.class, ()->{
            extendUserBookRentMethod.invoke(bookRentImplResource, bookRent);
        });
        Assertions.assertEquals(messageBuilder.asJSON(ExceptionMessage.BOOK_RENT_INCORRECT_EXTEND_DATE), exception.getCause().getMessage());
    }

    @Test
    public void givenBookRent_whenExtendUserBookRent_returnBookRent() throws ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BookRent bookRent = new BookRent();
        BookRent bookRentAfterExtend;
        Method extendUserBookRentMethod;
        bookRent.setDateRent(getDate("2020-02-01 12:10:30"));
        bookRent.setEndDateRent(getDate("2020-04-01 12:10:30"));
        extendUserBookRentMethod = getExtendUserBookRentMethod();
        bookRentAfterExtend = (BookRent) extendUserBookRentMethod.invoke(bookRentImplResource, bookRent);
        Assertions.assertEquals(getDate("2020-04-16 00:10:30"), bookRentAfterExtend.getEndDateRent());
    }

    private Date getDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateFormat = format.parse(date);
        return dateFormat;
    }

    private Method getExtendUserBookRentMethod() throws NoSuchMethodException {
        Method method = bookRentImplResource.getClass().getDeclaredMethod("extendUserBookRent", BookRent.class);
        method.setAccessible(true);
        return method;
    }

}
