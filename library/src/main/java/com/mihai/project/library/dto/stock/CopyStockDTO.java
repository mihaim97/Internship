package com.mihai.project.library.dto.stock;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CopyStockDTO {

    @NotBlank
    @Pattern(regexp = "AV|UN", message = "Incorrect flag")
    private String flag;

    @NotBlank
    @Pattern(regexp = "AV", message = "Incorrect status")
    private String status;

    @NotNull
    @Min(1)
    private int bookId;

    public CopyStockDTO(){}

    public CopyStockDTO(@NotBlank @Pattern(regexp = "AV|UN", message = "Incorrect flag") String flag, @NotBlank @Pattern(regexp = "AV", message = "Incorrect status") String status, @NotNull @Min(1) int bookId) {
        this.flag = flag;
        this.status = status;
        this.bookId = bookId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
