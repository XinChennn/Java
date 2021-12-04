package com.cc.test;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class Test01 {
    public static void main(String[] args) {
        int[] arr={23,12,45,2,66};
        Arrays.sort(arr);
//        //外层控制循环次数
//        for (int i = 0; i < arr.length-1; i++) {
//            //内层对元素进行冒泡排序
//            for (int j = 0; j < arr.length-1; j++) {
//                //如果前一个数大于后一个数，他俩交换
//                if ( arr[j] > arr[j+1] ) {
//                    int temp = arr[j+1];
//                    arr[j+1] = arr[j];
//                    arr[j] = temp;
//                }
//            }
//        }
        //现在已经排好序了，遍历数组
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
