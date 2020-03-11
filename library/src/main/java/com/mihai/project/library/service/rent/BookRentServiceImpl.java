package com.mihai.project.library.service.rent;

import com.mihai.project.library.annotation.AfterReturningBookAOP;
import com.mihai.project.library.annotation.BookRentAOP;
import com.mihai.project.library.contralleradvice.exception.BookRentOrRequestException;
import com.mihai.project.library.dao.BookRentDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.request.RentRequestService;
import com.mihai.project.library.service.stock.CopyStockService;
import com.mihai.project.library.service.user.UserService;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.enumeration.RentStatus;
import com.mihai.project.library.util.enumeration.Status;
import com.mihai.project.library.util.factory.LibraryFactoryManager;
import com.mihai.project.library.util.message.ExceptionMessage;
import com.mihai.project.library.util.message.MessageBuilder;
import com.mihai.project.library.util.message.user.UserMessageBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookRentServiceImpl implements BookRentService {

    @Autowired
    private BookRentDAO bookRentDAO;

    @Autowired
    private CopyStockService copyStockService;

    @Autowired
    private UserService userService;

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private UserMessageBuilder userMessageBuilder;

    @Autowired
    private MessageBuilder messageBuilder;

    @Override
    @Transactional
    @BookRentAOP
    public BookRent registerBookRent(int bookIdToRent, int userId, int period) {
        CopyStock copyStock = copyStockService.queryAvailableSingleBookCopyByBookId(bookIdToRent);
        User user = userService.queryUserById(userId);
        if (copyStock == null) {
            throw new BookRentOrRequestException(messageBuilder.asJSON(ExceptionMessage.BOOK_RENT_NO_BOOK_AVAILABLE));
        }
        if (user != null) {
            System.out.println(rentRequestService.checkIfUserHasARequestForCurrentBook(copyStock.getBookId(), user));
            if (checkIfUserAlreadyHasARentForCurrentBook(copyStock.getBookId(), user) == null
                    && rentRequestService.checkIfUserHasARequestForCurrentBook(copyStock.getBookId(), user) == null) {
                BookRent bookRent = LibraryFactoryManager.getInstance().getBookRentInstance();
                return bookRentDAO.registerBookRent(bookRent, copyStock, user, period);
            } else {
                throw new BookRentOrRequestException(messageBuilder.asJSON(ExceptionMessage.BOOK_RENT_RENT_EXIST));
            }
        } else {
            throw new BookRentOrRequestException(userMessageBuilder.getMessageOnUserNotFind(userId));
        }
    }

    @Override
    @Transactional
    public BookRent checkIfUserAlreadyHasARentForCurrentBook(Book book, User user) {
        return HibernateUtil.getUniqueResult(bookRentDAO.checkIfUserAlreadyHasARentForCurrentBook(book, user));
    }

    @Override
    @Transactional
    @AfterReturningBookAOP
    public BookRent returnARentedBook(int bookRentId, float note) {
        BookRent bookRent = queryBookRent(bookRentId);
        if (bookRent != null && !bookRent.getStatus().equals(Status.RE.toString())) {
            return bookRentDAO.returnARentedBook(bookRent, note);
        }
        return null;
    }

    @Override
    @Transactional
    public BookRent queryBookRent(int id) {
        return bookRentDAO.queryBookRent(id);
    }

    @Override
    @Transactional
    public List<BookRent> markBookRentAsLateIfExist() {
        List<BookRent> affectedBookRent = new ArrayList<>();
        List<BookRent> bookRents = queryAllBookRent();
        bookRents.stream().forEach(bookRent -> {
            if(new Date().compareTo(bookRent.getEndDateRent()) > 0){
                affectedBookRent.add(bookRent);
                bookRent.setStatus(RentStatus.LA.toString());
            }
        });
        return affectedBookRent;
    }

    @Override
    @Transactional
    public List<BookRent> queryAllBookRent() {
        return bookRentDAO.queryAllBookRent();
    }

}
