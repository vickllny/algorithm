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
//        int eor = 0;
//        int[] arr = new int[]{ };
//
//        for (final int a : arr) {
//            eor ^= a;
//        }
//        System.out.println(eor);

        int a = 17;
        int b = a & (~a + 1);
        System.out.println(b);
    }

    @Test
    public void test4(){
        int[] arr = {1,1,2,2,3,4,4,5,5,6,6,6,6,7,8,8,9,9,9,9,9,9};
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        //取最右的1
        //     0 1 1 0 1 0 0 0
        //取反  1 0 0 1 0 1 1 1
        //+1   1 0 0 1 1 0 0 0
        //&    1 0 0 1 1 0 0 0
        //r    0 0 0 0 1 0 0 0
        int r = eor & (~eor + 1);

        int o = 0;

        for (final int cur : arr) {
            if((cur & r) == 0){
                o ^= cur;
            }
        }

        System.out.println(o + " " + (o ^ eor));
    }
}
