package com.mihai.project.library.dto.request;

import com.mihai.project.library.annotation.validation.EnumValidationRentRequest;
import com.mihai.project.library.util.enumeration.RentRequestStatus;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RentRequestDTOUpdate {

    @NotNull
    @Min(1)
    private int rentRequestId;

    @NotNull
    @EnumValidationRentRequest(regexp = "GR|DE")
    @Valid
    private RentRequestStatus response;

    public RentRequestDTOUpdate() {
    }

    public RentRequestDTOUpdate(@NotNull @Min(1) int rentRequestId, @NotNull @Pattern(regexp = "DE|GR") @Valid RentRequestStatus response) {
        this.rentRequestId = rentRequestId;
        this.response = response;
    }

    public int getRentRequestId() {
        return rentRequestId;
    }

    public void setRentRequestId(int rentRequestId) {
        this.rentRequestId = rentRequestId;
    }

    public RentRequestStatus getResponse() {
        return response;
    }

    public void setResponse(RentRequestStatus response) {
        this.response = response;
    }
}
