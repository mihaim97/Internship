package com.mihai.project.library.service.request;

import com.mihai.project.library.contralleradvice.exception.BookRentOrRequestException;
import com.mihai.project.library.dao.RentRequestDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.book.BookService;
import com.mihai.project.library.service.stock.CopyStockService;
import com.mihai.project.library.service.user.UserService;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.enumeration.RentRequestStatus;
import com.mihai.project.library.util.enumeration.RentStatus;
import com.mihai.project.library.util.enumeration.Status;
import com.mihai.project.library.util.factory.LibraryFactoryManager;
import com.mihai.project.library.util.message.book.BookMessageBuilder;
import com.mihai.project.library.util.message.request.RentRequestMessageBuilder;
import com.mihai.project.library.util.message.user.UserMessageBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
        RentRequest rentRequest = null;
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
            rentRequest = LibraryFactoryManager.getInstance().getRentRequestInstance();
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
    public List<RentRequest> queryUserRentRequest(int userId) {
        return rentRequestDAO.queryUserRentRequest(userId);
    }

    @Override
    @Transactional
    public RentRequest acceptOrCancelRentRequest(int rentRequestId, RentRequestStatus status) {
        // querySingleRentRequestById - need to modify, just WFC - rent
        RentRequest rentRequest = querySingleRentRequestById(rentRequestId);
        if (rentRequest != null) {
            updateDataBaseOnStatus(rentRequest, status);
            return rentRequest;
        } else {
            throw new BookRentOrRequestException("");
        }
    }

    @Override
    @Transactional
    public RentRequest querySingleRentRequestById(int rentRequestId) {
        return rentRequestDAO.querySingleRentRequestById(rentRequestId);
    }

    private void updateDataBaseOnStatus(RentRequest rentRequest, RentRequestStatus status) {
        BookRent bookRent = null;
        CopyStock copyStock = null;
        if (status == RentRequestStatus.GR) {
            copyStock = copyStockService.queryCopyStock(rentRequest.getPending().getCopyId());
            bookRent = setStatusToBookRentAndCreate(rentRequest, copyStock);
            copyStock.setStatus(Status.RE.toString());
            rentRequest.setStatus(RentRequestStatus.GR.toString());
            rentRequestDAO.registerBookRentAfterUserAccept(bookRent);
        } else if (status == RentRequestStatus.DE) {

        }
    }

    private BookRent setStatusToBookRentAndCreate(RentRequest rentRequest, CopyStock copyStock){
        BookRent bookRent = LibraryFactoryManager.getInstance().getBookRentInstance();
        bookRent.setStatus(RentStatus.ON.toString());
        bookRent.setDateRent(new Date());
        bookRent.setEndDateRent(DateUtils.addMonths(new Date(),1));
        bookRent.setUser(rentRequest.getUser());
        bookRent.setBook(rentRequest.getBookId());
        bookRent.setCopy(copyStock);
        return  bookRent;
    }

}
