package com.mihai.project.library.service.request;

import com.mihai.project.library.contralleradvice.exception.BookRentOrRequestException;
import com.mihai.project.library.dao.RentRequestDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.book.BookService;
import com.mihai.project.library.service.stock.CopyStockService;
import com.mihai.project.library.service.user.UserService;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.factory.LibraryFactoryManager;
import com.mihai.project.library.util.message.book.BookMessageBuilder;
import com.mihai.project.library.util.message.request.RentRequestMessageBuilder;
import com.mihai.project.library.util.message.user.UserMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RentRequestServiceImpl implements RentRequestService {

    @Autowired
    private RentRequestDAO rentRequestDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CopyStockService copyStockService;

    @Autowired
    private UserMessageBuilder userMessageBuilder;

    @Autowired
    private BookMessageBuilder bookMessageBuilder;

    @Autowired
    private RentRequestMessageBuilder rentRequestMessageBuilder;


    @Override
    @Transactional
    public RentRequest registerRentRequest(int bookId, int userId) {
        User user = userService.queryUserById(userId);
        Book book = bookService.queryBook(bookId);
        /** When copy are available, no need for request **/
        if (copyStockService.queryAvailableSingleBookCopyByBookId(bookId) != null) {
            throw new BookRentOrRequestException(rentRequestMessageBuilder.getMessageOnCopyAreAvailable());
        }
        if (book == null) {
            throw new BookRentOrRequestException(bookMessageBuilder.getMessageOnIncorrectBookId(bookId));
        }
        if (user == null) {
            throw new BookRentOrRequestException(userMessageBuilder.getMessageOnUserNotFind(userId));
        }
        /** Prevent user for making a request if he already has a rent with status ON (on going) or LA (late) **/
        if (checkIfUserAlreadyHasAvailableBookRent(book, user) != null) {
            throw new BookRentOrRequestException(rentRequestMessageBuilder.getMessageOnUserAlreadyHasARent());
        }
        /** Prevent user for making more than one request for a book **/
        if (checkIfUserHasARequestForCurrentBook(book, user) == null) {
            RentRequest rentRequest = LibraryFactoryManager.getInstance().getRentRequestInstance();
            return rentRequestDAO.registerRentRequest(rentRequest, book, user);
        } else {
            throw new BookRentOrRequestException(rentRequestMessageBuilder.getMessageOnRentRequestExist());
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public RentRequest checkIfUserHasARequestForCurrentBook(Book book, User user) {
        return HibernateUtil.getUniqueResult(rentRequestDAO.checkIfUserHasARequestForCurrentBook(book, user));
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public BookRent checkIfUserAlreadyHasAvailableBookRent(Book book, User user) {
        return HibernateUtil.getUniqueResult(rentRequestDAO.checkIfUserAlreadyHasAvailableBookRent(book, user));
    }

    @Override
    @Transactional
    public RentRequest checkForExistingRequest(Book book) {
        return HibernateUtil.getUniqueResult(rentRequestDAO.checkForExistingRequest(book));
    }

    @Override
    @Transactional
    public Pending registerPending(Pending pending) {
        return rentRequestDAO.registerPending(pending);
    }

}
