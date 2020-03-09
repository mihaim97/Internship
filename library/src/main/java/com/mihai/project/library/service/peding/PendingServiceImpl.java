package com.mihai.project.library.service.peding;

import com.mihai.project.library.dao.PendingDAO;
import com.mihai.project.library.entity.interntable.Pending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

}
