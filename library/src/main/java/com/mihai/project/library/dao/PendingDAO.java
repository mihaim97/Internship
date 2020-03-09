package com.mihai.project.library.dao;

import com.mihai.project.library.entity.interntable.Pending;

public interface PendingDAO {

    Pending registerPending(Pending pending);

    Pending removePending(Pending pending);
}
