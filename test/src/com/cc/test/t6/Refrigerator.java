package com.cc.test.t6;

/**
 * 冰箱类
 */
public class Refrigerator {
    String type;
    double length;
    double width;
    double height;
    boolean flag;

    public void open(){
        System.out.println("打开冰箱门");
    }

    public boolean box(double height,double width,double length){
        if (this.length > length  &&  this.width> width  &&  this.height>height) {
            flag=true;
            return true;
        }
        flag=false;
        return false;

    }

    public void close(){
        if (flag==true) {
            System.out.println("装箱成功，关闭冰箱门");
        }else{
            System.out.println("装箱失败");
        }
    }

}
