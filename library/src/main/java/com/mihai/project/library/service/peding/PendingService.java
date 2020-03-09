package com.mihai.project.library.service.peding;

import com.mihai.project.library.entity.interntable.Pending;

public interface PendingService {

    Pending registerPending(Pending pending);

    Pending removePending(Pending pending);
}
