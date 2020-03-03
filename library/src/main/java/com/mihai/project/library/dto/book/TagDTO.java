package com.mihai.project.library.dto.book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TagDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    public TagDTO() {
    }

    public TagDTO(@NotBlank @Size(min = 3, max = 50) String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
