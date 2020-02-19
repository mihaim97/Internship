package com.mihai.project.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(value = "id", allowGetters = true)
public class AuthorDTO {

    private int id;

    @NotNull
    private String name;

    public AuthorDTO(){}

    public AuthorDTO(int id, @NotNull String name) {
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
