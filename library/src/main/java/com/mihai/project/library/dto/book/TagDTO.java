package com.mihai.project.library.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(value = "id", allowGetters = true)
public class TagDTO {

    private int id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    public TagDTO() {
    }

    public TagDTO(int id, @NotBlank @Size(min = 3, max = 50) String name) {
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
