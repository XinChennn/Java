package com.cc.test.t4;

public class Account {
     int banlance;

     public void save(int money){
         banlance+=money;
     }

    public void draw(int money){
        banlance-=money;
    }
}
