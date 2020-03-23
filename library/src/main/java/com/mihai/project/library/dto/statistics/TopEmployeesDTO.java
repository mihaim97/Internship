package com.mihai.project.library.dto.statistics;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class TopEmployeesDTO {

    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format, please try {yyyy-MM-dd}")
    private String startDate;

    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format, please try {yyyy-MM-dd}")
    private String endDate;

    public TopEmployeesDTO() {
    }

    public TopEmployeesDTO(@NotBlank @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format, please try {yyyy-MM-dd}") String startDate, @NotBlank @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format, please try {yyyy-MM-dd}") String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
