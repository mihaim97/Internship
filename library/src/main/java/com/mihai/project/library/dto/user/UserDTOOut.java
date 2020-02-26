package com.mihai.project.library.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTOOut {

    @NotNull
    private int id;

    @NotBlank
    @Size(min = 5, max = 50)
    private String username;

    @Email(regexp = "^(.+)@(.+)$", message = "Please provide a valid email")
    @NotBlank
    private String email;

    private String role;

    public UserDTOOut() {
    }

    public UserDTOOut(@NotNull int id, @NotBlank @Size(min = 5, max = 50) String username, @Email(regexp = "^(.+)@(.+)$", message = "Please provide a valid email") @NotBlank String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
