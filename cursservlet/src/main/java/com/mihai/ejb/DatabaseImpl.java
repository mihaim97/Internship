package com.mihai.ejb;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class DatabaseImpl implements Database{

    private int dbId;

    public DatabaseImpl(){
        this.dbId = new Random().nextInt(400);
    }

    @Override
    public void getMsg(){
        System.out.println("Mesajjj!!! from ejb " + Integer.toString(dbId));
    }
}
