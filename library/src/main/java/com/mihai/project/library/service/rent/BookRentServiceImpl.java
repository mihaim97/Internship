package com.mihai.project.library.service.rent;

import com.mihai.project.library.annotation.BookRentAOP;
import com.mihai.project.library.contralleradvice.exception.BookRentOrRequestException;
import com.mihai.project.library.dao.BookRentDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.stock.CopyStockService;
import com.mihai.project.library.service.user.UserService;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.enumeration.Status;
import com.mihai.project.library.util.factory.LibraryFactoryManager;
import com.mihai.project.library.util.message.rent.BookRentMessageBuilder;
import com.mihai.project.library.util.message.user.UserMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private BookRentMessageBuilder bookRentMessageBuilder;

    @Autowired
    private UserMessageBuilder userMessageBuilder;

    @Override
    @Transactional
    @BookRentAOP
    public BookRent registerBookRent(int bookIdToRent, int userId, int period) {
        CopyStock copyStock = copyStockService.queryAvailableSingleBookCopyByBookId(bookIdToRent);
        User user = userService.queryUserById(userId);
        if (copyStock == null) {
            throw new BookRentOrRequestException(bookRentMessageBuilder.getMessageOnNoCopyAvailable());
        }
        if (user != null) {
            /** If user has a rent on current book and status is ON or LA he can't make a book rent
             *  If there is a rent made by current user with status RE he can make one. No matter how many they are.
             **/
            if (checkIfUserAlreadyHasARentForCurrentBook(copyStock.getBookId(), user) == null) {
                BookRent bookRent = LibraryFactoryManager.getInstance().getBookRentInstance();
                return bookRentDAO.registerBookRent(bookRent, copyStock, user, period);
            } else {
                throw new BookRentOrRequestException(bookRentMessageBuilder.getMessageOnUserAlreadyRentABookWithId(bookIdToRent));
            }
        }else{
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
    public List<BookRent> queryAllBookRent() {
        return null;
    }
}
