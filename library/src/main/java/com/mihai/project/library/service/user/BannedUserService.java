package com.mihai.project.library.service.user;

import com.mihai.project.library.entity.user.BannedUser;
import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface BannedUserService {
    void registerBannedUser(User user, int days);
    BannedUser checkIfUserIsBanned(User user);
}
