package com.mihai.project.library.service.peding;

import com.mihai.project.library.dao.PendingDAO;
import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.service.stock.CopyStockService;
import com.mihai.project.library.util.enumeration.RentRequestStatus;
import com.mihai.project.library.util.enumeration.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class PendingServiceImpl implements PendingService {

    @Autowired
    private PendingDAO pendingDAO;

    @Override
    @Transactional
    public Pending registerPending(Pending pending) {
        return pendingDAO.registerPending(pending);
    }

    @Override
    @Transactional
    public Pending removePending(Pending pending) {
        return pendingDAO.removePending(pending);
    }

    @Override
    @Transactional
    public void deletePendingIn24HPassed() {
        List<Pending> pendingList = pendingDAO.queryAllPending();
        pendingList.stream().forEach(pending -> {
            if(pending.getEndDate().before(new Date())){
                CopyStock copyStock = pendingDAO.queryCopyStockById(pending.getCopyId());
                copyStock.setStatus(Status.AV.toString());
                pending.getRentRequestId().setStatus(RentRequestStatus.DE.toString());
                removePending(pending);
            }
        });
    }

}
