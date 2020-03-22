package com.mihai.project.library.util.dtoentity.request;

import com.mihai.project.library.dto.request.BookRequestDTO;
import com.mihai.project.library.dto.request.BookRequestDTOOut;
import com.mihai.project.library.entity.request.BookRequest;

import java.util.List;

public interface BookRequestDTOEntityConverter {

    BookRequest fromBookRequestDTOToBookRequest(BookRequestDTO bookRequestDTO);

    BookRequestDTOOut fromBookRequestToBookRequestDTOOut(BookRequest bookRequest);

    List<BookRequestDTOOut> fromListBookRequestToListOut(List<BookRequest> bookRequests);


}
