package com.mihai.project.library.service.peding;

import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.stock.CopyStock;

public interface PendingService {

    Pending registerPending(Pending pending);

    Pending removePending(Pending pending);

    void deletePendingIn24HPassed();

}
