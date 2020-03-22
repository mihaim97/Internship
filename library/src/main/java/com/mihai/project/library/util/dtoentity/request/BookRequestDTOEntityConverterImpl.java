package com.mihai.project.library.util.dtoentity.request;

import com.mihai.project.library.dto.request.BookRequestDTO;
import com.mihai.project.library.dto.request.BookRequestDTOOut;
import com.mihai.project.library.entity.request.BookRequest;
import com.mihai.project.library.util.enumeration.BookRequestStatus;
import com.mihai.project.library.util.factory.LibraryFactoryManager;
import com.mihai.project.library.util.mapper.ModelMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRequestDTOEntityConverterImpl implements BookRequestDTOEntityConverter {

    @Override
    public BookRequest fromBookRequestDTOToBookRequest(BookRequestDTO bookRequestDTO) {
        BookRequest bookRequest = LibraryFactoryManager.getInstance().getBookRequestInstance();
        bookRequest.setAuthor(bookRequestDTO.getAuthor());
        bookRequest.setCopyNumber(bookRequestDTO.getCopyNumber());
        bookRequest.setCost(bookRequestDTO.getCost());
        bookRequest.setLink(bookRequestDTO.getLink());
        bookRequest.setPublishingCompany(bookRequestDTO.getPublishingCompany());
        bookRequest.setTitle(bookRequestDTO.getTitle());
        bookRequest.setStatus(BookRequestStatus.PE.toString());
        return bookRequest;
    }

    @Override
    public BookRequestDTOOut fromBookRequestToBookRequestDTOOut(BookRequest bookRequest) {
        ModelMapper mapper = ModelMapperUtil.getMapper();
        BookRequestDTOOut bookRequestDTOOut = mapper.map(bookRequest, BookRequestDTOOut.class);
        return bookRequestDTOOut;
    }

    @Override
    public List<BookRequestDTOOut> fromListBookRequestToListOut(List<BookRequest> bookRequests) {
        List<BookRequestDTOOut> bookRequestDTOOutList = new ArrayList<>();
        bookRequests.stream().forEach(bookRequest -> {
            BookRequestDTOOut bookRequestDTOOut = fromBookRequestToBookRequestDTOOut(bookRequest);
            bookRequestDTOOutList.add(bookRequestDTOOut);
        });
        return bookRequestDTOOutList;
    }

}
