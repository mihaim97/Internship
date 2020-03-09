package com.mihai.project.library.util.dtoentity.request;

import com.mihai.project.library.dto.request.RentRequestDTOOut;
import com.mihai.project.library.entity.request.RentRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentRequestDTOEntityConverterImpl implements RentRequestDTOEntityConverter {

    @Override
    public RentRequestDTOOut fromRentRequestToDtoOut(RentRequest rentRequest) {
        RentRequestDTOOut rentRequestDTOOut = new RentRequestDTOOut();
        rentRequestDTOOut.setId(rentRequest.getId());
        rentRequestDTOOut.setStatus(rentRequest.getStatus());
        rentRequestDTOOut.setBookId(rentRequest.getBookId().getId());
        rentRequestDTOOut.setDateRequest(rentRequest.getDateRequest());
        rentRequestDTOOut.setUser(rentRequest.getUser().getId());
        return rentRequestDTOOut;
    }

    @Override
    public List<RentRequestDTOOut> fromListRentRequestToListDtoOut(List<RentRequest> rentRequests) {
        List<RentRequestDTOOut> rentRequestDTOOuts = new ArrayList<>();
        rentRequests.stream().forEach(req->{
            rentRequestDTOOuts.add(fromRentRequestToDtoOut(req));
        });
        return rentRequestDTOOuts;
    }

}
