package com.mihai.ejb;

import javax.ejb.Singleton;

@Singleton
public class Databases {

    public void getMsg(){
        System.out.println("Mesajjj!!! from ejb");
    }
}
