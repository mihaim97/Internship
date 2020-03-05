package com.mihai.project.library.util.dtoentity.stock;

import com.mihai.project.library.dto.stock.CopyStockDTOOut;
import com.mihai.project.library.dto.stock.CopyStockDTOUpdate;
import com.mihai.project.library.entity.stock.CopyStock;

import java.util.List;

public interface CopyStockDTOEntityConverter {

    CopyStockDTOOut fromCopyStockToDto(CopyStock copyStock);

    CopyStock fromCopyStockUpdateToCopyStock(CopyStockDTOUpdate copyStockDTOUpdate);

    List<CopyStockDTOOut> fromCopyStockListToDtoOutList(List<CopyStock> copyStocks);
}
