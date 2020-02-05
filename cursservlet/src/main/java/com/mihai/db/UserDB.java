package com.mihai.db;

import com.mihai.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserDB {

    public static UserDB instance = new UserDB();

    private List<UserInfo> users;
    private UserDB(){}

    public boolean findUserByCredentials(final String username, final String password){
        if(users == null){populateData();}

        UserInfo userInfo = users.stream()
                            .filter(user -> (user.getUsername().equals(username) && user.getPassword().equals(password)))
                            .findAny().orElse(null);
        if(userInfo != null) return true;

        return false;
    }

    public void populateData(){
        users = new ArrayList<>();
        users.add(new UserInfo("mihai", "mihai"));
        users.add(new UserInfo("ana", "ana"));
        users.add(new UserInfo("user", "user"));
    }


}
