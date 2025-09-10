package com.vickllny;

import org.junit.Test;

public class XORTest {

    @Test
    public void test(){
        int a = 10;
        int b = 20;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void test1(){
        Integer a = Integer.valueOf("17");
        Integer b = Integer.valueOf("17");
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void test2(){
        int[] arr = new int[]{1 ,1};
        arr[0] = arr[0] ^ arr[1];
        arr[1] = arr[0] ^ arr[1];
        arr[0] = arr[0] ^ arr[1];
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }

    @Test
    public void test3(){
        //查找数组中出现奇数次的数
        int eor = 0;
        int[] arr = new int[]{ };

        for (final int a : arr) {
            eor ^= a;
        }
        System.out.println(eor);
    }
}
