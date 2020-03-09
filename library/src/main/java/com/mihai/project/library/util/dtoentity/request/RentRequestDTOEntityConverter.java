package com.mihai.project.library.util.dtoentity.request;

import com.mihai.project.library.dto.request.RentRequestDTOOut;
import com.mihai.project.library.entity.request.RentRequest;

import java.util.List;

public interface RentRequestDTOEntityConverter {

    RentRequestDTOOut fromRentRequestToDtoOut(RentRequest rentRequest);

    List<RentRequestDTOOut> fromListRentRequestToListDtoOut(List<RentRequest> rentRequests);

}
