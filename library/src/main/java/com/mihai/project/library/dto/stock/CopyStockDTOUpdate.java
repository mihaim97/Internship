package com.mihai.project.library.dto.stock;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CopyStockDTOUpdate {

    @NotNull
    private int code;

    @NotBlank
    @Pattern(regexp = "AV|UN", message = "Incorrect flag")
    private String flag;

    @NotBlank
    @Pattern(regexp = "AV|RE|PE", message = "Incorrect status")
    private String status;

    public CopyStockDTOUpdate() {
    }

    public CopyStockDTOUpdate(@NotNull int code, @NotBlank @Pattern(regexp = "AV|UN", message = "Incorrect flag") String flag, @NotBlank @Pattern(regexp = "AV|RE|PE", message = "Incorrect status") String status) {
        this.code = code;
        this.flag = flag;
        this.status = status;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
