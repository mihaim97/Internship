package com.mihai.loginstate;

public class LogInUser {

    public static LogInUser instance = new LogInUser();
    private boolean state;
    private String username;
    private LogInUser(){}

    public void setUserLogInState(boolean state){this.state = state;}
    public boolean getUserLogInState(){return this.state;}



}
