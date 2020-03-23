package com.mihai.project.library.dao;

import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.stock.CopyStock;

import java.util.List;

public interface PendingDAO {

    Pending registerPending(Pending pending);

    Pending removePending(Pending pending);

    List<Pending> queryAllPending();

    CopyStock queryCopyStockById(int id);

}
