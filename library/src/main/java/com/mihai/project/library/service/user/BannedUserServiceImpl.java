package com.mihai.project.library.service.user;

import com.mihai.project.library.dao.BannedUserDAO;
import com.mihai.project.library.entity.user.BannedUser;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BannedUserServiceImpl implements BannedUserService {

    @Autowired
    private BannedUserDAO bannedUserDAO;

    @Override
    @Transactional
    public void registerBannedUser(User user, int days) {
        bannedUserDAO.registerBannedUser(user, days);
    }

    @Override
    @Transactional
    public BannedUser checkIfUserIsBanned(User user) {
        return HibernateUtil.getUniqueResult(bannedUserDAO.checkIfUserIsBanned(user));
    }

    @Override
    @Transactional
    public void checkIfBannedExpiredAndDelete() {
        bannedUserDAO.checkIfBannedExpiredAndDelete();
    }

}
