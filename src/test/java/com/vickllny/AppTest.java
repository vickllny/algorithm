package com.vickllny;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {


    /**
     * 对数器测试
     *
     */
    @Test
    public void test1(){
        int testTime = 500000;
        int maxSize = 10000;
        int maxValue = 10000;

        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            final int[] arr = CommonUtils.generateRandomArray(maxSize, maxValue);
            final int[] arr1 = CommonUtils.copyArray(arr);
            final int[] arr2 = CommonUtils.copyArray(arr);

            BubbleSort.sort(arr1);
            SelectionSort.sort(arr2);

            if(!CommonUtils.isEquals(arr1, arr2)){
                success = false;
                break;
            }
        }
        System.out.println(success ? "NewBee!!!" : "Fuck !! Fuck!!!!!!!!!!!");
    }

    @Test
    public void test2(){
        //精度丢失
        System.out.println(0.1 + 0.2);
    }
}
