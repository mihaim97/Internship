package com.mihai.project.library.dao;

import com.mihai.project.library.entity.user.BannedUser;
import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface BannedUserDAO {
    void registerBannedUser(User user, int days);
    List<BannedUser> checkIfUserIsBanned(User user);
}
