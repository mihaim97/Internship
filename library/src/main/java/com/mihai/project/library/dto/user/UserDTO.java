package com.mihai.project.library.dto.user;

import javax.validation.constraints.*;

public class UserDTO {

    @NotNull
    @Min(1)
    private int id;

    @NotBlank
    @Size(min = 5, max = 50)
    private String username;

    @NotBlank
    @Size(min=6)
    private String password;

    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;

    @Email(regexp = "^(.+)@(.+)$", message = "Please provide a valid email")
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "ADMIN|REGULAR", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Incorrect role")
    private String role;

    public UserDTO() { }

    public UserDTO(@NotNull @Min(1) int id, @NotBlank @Size(min = 5, max = 50) String username, @NotBlank @Size(min = 6) String password, @NotBlank @Size(min = 3, max = 50) String firstName, @NotBlank @Size(min = 3, max = 50) String lastName, @Email(regexp = "^(.+)@(.+)$", message = "Please provide a valid email") @NotBlank String email, @NotBlank @Pattern(regexp = "ADMIN|REGULAR", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Incorrect role") String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
