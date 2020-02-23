package com.mihai.project.library.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotBlank
    @Size(min = 5, max = 50)
    private String username;

    @NotBlank
    @Size(min=6)
    private String password;

    @Email(regexp = "^(.+)@(.+)$", message = "Please provide a valid email")
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "ADMIN|REGULAR", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Incorrect role")
    private String role;

    public UserDTO() { }

    public UserDTO(@NotBlank @Size(min = 5, max = 50) String username, @NotBlank @Size(min = 6) String password, @Email(regexp = "^(.+)@(.+)$", message = "Please provide a valid email") @NotBlank String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
