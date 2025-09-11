package com.vickllny;

public class CommonUtils {

    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(final int[] sourceArr){
        final int[] arr = new int[sourceArr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sourceArr[i];
        }
        return arr;
    }

    public static boolean isEquals(int[] arr, int[] arr2){
        if(arr.length != arr2.length){
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }
}
