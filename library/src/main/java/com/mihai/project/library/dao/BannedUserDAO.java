package com.mihai.project.library.dao;

import com.mihai.project.library.entity.user.User;

public interface BannedUserDAO {
    void registerBannedUser(User user, int days);
}
