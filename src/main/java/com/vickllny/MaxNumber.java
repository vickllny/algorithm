package com.vickllny;

public class MaxNumber {

    public static int max(int[] arr){
        if(arr == null){
            return -1;
        }
        if(arr.length == 1){
            return arr[0];
        }
        return process(arr, 0, arr.length - 1);
    }

    static int process(int[] arr, int left, int right){
        if(left == right){
            return arr[left];
        }
        int mid = left + ((right - left) >> 1); // left + (right - left) / 2
        int leftMax = process(arr, left, mid);
        int rightMax = process(arr, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        final int[] array = CommonUtils.generateRandomArray(1000, 1000);
        System.out.println(max(array));
        BubbleSort.sort(array);
        System.out.println(1);
    }
}
