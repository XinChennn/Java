package com.cc.test.t6;

public class Test {
    public static void main(String[] args) {
        Refrigerator refrigerator=new Refrigerator();
        refrigerator.height=2;
        refrigerator.width=2;
        refrigerator.length=2;
        refrigerator.box(1,1,1);
        refrigerator.close();
    }
}
