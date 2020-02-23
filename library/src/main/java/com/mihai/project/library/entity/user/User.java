package com.mihai.project.library.entity.user;

public class User {

    private String username;
    private String password;
    private String email;
    private int enable;
    private String role;

    public User() { }

    public User(String username, String password, String email, int enable, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enable = enable;
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

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
