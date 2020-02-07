package com.mihai.db;

import com.mihai.model.UserInfo;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserDB {

    //public static UserDB instance = new UserDB();

    private List<UserInfo> users;
    public UserDB(){
        if(users == null){populateData();}
    }

    public boolean findUserByCredentials(final String username, final String password){
        //if(users == null){populateData();}

        UserInfo userInfo = users.stream()
                            .filter(user -> (user.getUsername().equals(username) && user.getPassword().equals(password)))
                            .findAny().orElse(null);
        if(userInfo != null) return true;

        return false;
    }

    private void populateData(){
        users = new ArrayList<>();
        users.add(new UserInfo("mihai", "mihai"));
        users.add(new UserInfo("ana", "ana"));
        users.add(new UserInfo("user", "user"));
    }


}
