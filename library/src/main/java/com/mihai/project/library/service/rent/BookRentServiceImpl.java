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
import com.mihai.project.library.service.user.BannedUserService;
import com.mihai.project.library.service.user.UserService;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.UtilConstant;
import com.mihai.project.library.util.enumeration.RentStatus;
import com.mihai.project.library.util.factory.LibraryFactoryManager;
import com.mihai.project.library.util.message.ExceptionMessage;
import com.mihai.project.library.util.message.MessageBuilder;
import com.mihai.project.library.util.message.user.UserMessageBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BookRentServiceImpl implements BookRentService {

    @Autowired
    private BookRentDAO bookRentDAO;

    @Autowired
    private CopyStockService copyStockService;

    @Autowired
    private UserService userService;

    @Autowired
    private BannedUserService bannedUserService;

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
        if (bookRent != null) {
            if (bookRent.getStatus().equals(RentStatus.LA.toString())) {
                int daysForBanned = bannedUserFromRenting(bookRent.getEndDateRent());
                bannedUserService.registerBannedUser(bookRent.getUser(), daysForBanned);
            }
            if (!bookRent.getStatus().equals(RentStatus.RE.toString())) {
                return bookRentDAO.returnARentedBook(bookRent, note);
            }
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
            if (new Date().compareTo(bookRent.getEndDateRent()) > 0) {
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

    @Override
    @Transactional
    public BookRent extendRent(int bookRentId) {
        BookRent bookRent = queryBookRentWithAVStatus(bookRentId);
        if (bookRent != null) {
            return extendUserBookRent(bookRent);
        } else {
            throw new BookRentOrRequestException(messageBuilder.asJSON(ExceptionMessage.BOOK_RENT_FAIL_ON_EXTEND));
        }
    }

    @Override
    @Transactional
    public BookRent queryBookRentWithAVStatus(int id) {
        return HibernateUtil.getUniqueResult(bookRentDAO.queryBookRentWithAVStatus(id));
    }

    private int bannedUserFromRenting(Date endDate) {
        int daysDiff = getDifference(endDate, new Date(), ChronoUnit.DAYS);
        int daysBanned = daysDiff * UtilConstant.BANNED_MULTIPLIER;
        return daysBanned < UtilConstant.MINIMUM_DAYS_BAN ? UtilConstant.MINIMUM_DAYS_BAN : daysBanned;
    }

    private BookRent extendUserBookRent(BookRent bookRent) {
        /**If user end date is less then current date**/
        if (bookRent.getEndDateRent().before(new Date())) {
            throw new BookRentOrRequestException(messageBuilder.asJSON(ExceptionMessage.BOOK_RENT_INCORRECT_EXTEND_DATE));
        }
        /**check if user has a month **/
        int monthDiff = getDifference(bookRent.getDateRent(), new Date(), ChronoUnit.MONTHS);
        if (monthDiff < 1) {
            throw new BookRentOrRequestException(messageBuilder.asJSON(ExceptionMessage.BOOK_RENT_ONE_MONTH_NOT_PASS));
        }
        /**Total month - initial rent**/
        int monthDiffRented = getDifference(bookRent.getDateRent(), bookRent.getEndDateRent(), ChronoUnit.MONTHS);
        if (monthDiffRented < 3) {
            int daysToExtend = calculateDaysAllowedToExtendRent(bookRent);
            bookRent.setEndDateRent(DateUtils.addDays(bookRent.getEndDateRent(), daysToExtend));
        } else {
            throw new BookRentOrRequestException(messageBuilder.asJSON(ExceptionMessage.BOOK_RENT_EXTEND_MAX_MONTH));
        }
        return bookRent;
    }

    private int calculateDaysAllowedToExtendRent(BookRent bookRent) {
        Date endDate = DateUtils.addMonths(bookRent.getDateRent(), UtilConstant.MAX_RENT_MONTH);
        Date nextRentDate = DateUtils.addDays(bookRent.getEndDateRent(), UtilConstant.MAX_EXTENSION_DAYS);
        if (nextRentDate.before(endDate)) {
            return UtilConstant.MAX_EXTENSION_DAYS;
        } else {
            return getDifference(bookRent.getEndDateRent(), endDate, ChronoUnit.DAYS);
        }
    }

    private int compareDateToCurrentDate(Date date1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime date1Formatted = LocalDateTime.parse(date1.toString(), formatter);
        return date1Formatted.toLocalDate().compareTo(LocalDateTime.now().toLocalDate());
    }

    private int getDifference(Date from, Date to, ChronoUnit unit) {
        LocalDateTime startRentDate = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime nowDate = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime template = LocalDateTime.from(startRentDate);
        long monthDiff = template.until(nowDate, unit);
        return (int) monthDiff;
    }

    public void setMessageBuilder(MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

}
