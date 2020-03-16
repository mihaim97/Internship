package com.mihai.project.library.service.user;

import com.mihai.project.library.entity.user.User;

public interface BannedUserService {
    void registerBannedUser(User user, int days);
}
