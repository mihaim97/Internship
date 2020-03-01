package com.mihai.project.library.dto.book.update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AuthorDTOID {

    private int id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    public AuthorDTOID(){}

    public AuthorDTOID(int id, @NotBlank @Size(min = 3, max = 50) String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
