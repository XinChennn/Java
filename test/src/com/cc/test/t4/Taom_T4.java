package com.cc.test.t4;

import com.cc.test.t4.Account;

public class Taom_T4 {
    public static void main(String[] args) {
        //创建一个账户
        Account fir = new Account();
        //存1000
        fir.save(1000);
        //取100
        fir.draw(100);
        //创建第二个账户  并转账500
        Account sec=new Account();
        fir.draw(500);
        sec.save(500);
        System.out.println("第一个账户的余额为："+fir.banlance+",第二个账户的余额为："+sec.banlance);
    }
}
