package com.vickllny;

import org.junit.Test;

public class HashTests {

    @Test
    public void test(){
        final String key = "num";
        final int size = 2 << 3;
        final int sizeMask = size - 1;
        final int num = 123123;
        System.out.println(num >>> 1);
        System.out.println(num % size);
        System.out.println(num & sizeMask);
        System.out.println((num % size) == (num & sizeMask));
    }
}
