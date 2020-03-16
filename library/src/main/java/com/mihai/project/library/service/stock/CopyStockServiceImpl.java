package com.mihai.project.library.service.stock;

import com.mihai.project.library.dao.CopyStockDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.service.peding.PendingService;
import com.mihai.project.library.service.request.RentRequestService;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.enumeration.Flag;
import com.mihai.project.library.util.enumeration.RentRequestStatus;
import com.mihai.project.library.util.enumeration.Status;
import com.mihai.project.library.util.factory.LibraryFactoryManager;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class CopyStockServiceImpl implements CopyStockService {

    @Autowired
    private CopyStockDAO copyStockDAO;

    @Autowired
    private PendingService pendingService;

    @Override
    @Transactional
    public void addBookCopy(Book book, int copyNumber) {
        while (copyNumber != 0) {
            copyStockDAO.addBookCopy(createCopyStock(book));
            copyNumber--;
        }
    }

    @Override
    @Transactional
    public CopyStock addSingleCopy(Book book, String flag, String status) {
        RentRequest rentRequest = checkForRentRequestOnCurrentBook(book);
        CopyStock copyStock = copyStockDAO.addBookCopy(createCopyStock(book, flag, status));
        if(rentRequest != null && copyStock.getFlag().equals(Status.AV.toString())){
            copyStock.setStatus(Status.PE.toString());
            rentRequest.setStatus(RentRequestStatus.WFC.toString());
            Pending pending = LibraryFactoryManager.getInstance().getPendingInstance();
            pending.setRentRequestId(rentRequest);
            pending.setCopyId(copyStock.getCode());
            pending.setEndDate(DateUtils.addDays(new Date(), 1));
            pendingService.registerPending(pending);
        }
        return copyStock;
    }

    @Override
    @Transactional
    public CopyStock queryCopyStock(int id) {
        return copyStockDAO.queryCopyStock(id);
    }

    @Override
    @Transactional
    public List<CopyStock> queryAllBookCopy(int bookId) {
        return copyStockDAO.queryAllBookCopy(bookId);
    }

    @Override
    @Transactional
    public CopyStock queryAvailableSingleBookCopyByBookId(int bookId) {
        return HibernateUtil.getUniqueResult(copyStockDAO.querySingleBookCopyByBookId(bookId));
    }

    @Override
    @Transactional
    public List<CopyStock> queryAllCopy() {
        return copyStockDAO.queryAllCopy();
    }

    @Override
    @Transactional
    public boolean deleteCopy(int id) {
        CopyStock copyStock = queryCopyStock(id);
        if (copyStock != null) {
            return copyStockDAO.deleteCopy(copyStock);
        }
        return false;
    }

    @Override
    @Transactional
    public CopyStock updateCopy(CopyStock newValue) {
        CopyStock copyToUpdate = queryCopyStock(newValue.getCode());
        if(copyToUpdate != null){
            return copyStockDAO.updateCopy(copyToUpdate, newValue);
        }
        return null;
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public RentRequest checkForRentRequestOnCurrentBook(Book book) {
        return HibernateUtil.getUniqueResult(copyStockDAO.checkForRentRequestOnCurrentBook(book));
    }


    private CopyStock createCopyStock(Book book, String flag, String status) {
        return new CopyStock(flag, status, book);
    }

    private CopyStock createCopyStock(Book book) {
        return new CopyStock(Flag.AV.toString(), Status.AV.toString(), book);
    }

}
