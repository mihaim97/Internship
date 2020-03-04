package com.mihai.project.library.util.dtoentity.stock;

import com.mihai.project.library.dto.stock.CopyStockDTOOut;
import com.mihai.project.library.entity.stock.CopyStock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CopyStockDTOEntityConverterImpl implements CopyStockDTOEntityConverter {

    @Override
    public CopyStockDTOOut fromCopyStockToDto(CopyStock copyStock) {
        CopyStockDTOOut copyStockDTOOut = new CopyStockDTOOut();
        copyStockDTOOut.setCode(copyStock.getCode());
        copyStockDTOOut.setFlag(copyStock.getFlag());
        copyStockDTOOut.setStatus(copyStock.getStatus());
        copyStockDTOOut.setBookId(copyStock.getBookId().getId());
        return copyStockDTOOut;
    }

    @Override
    public List<CopyStockDTOOut> fromCopyStockListToDtoOutList(List<CopyStock> copyStocks) {
        List<CopyStockDTOOut> listToReturn = new ArrayList<>();
        copyStocks.stream().forEach(copyStock -> {
           listToReturn.add(fromCopyStockToDto(copyStock));
        });
        return listToReturn;
    }
}
