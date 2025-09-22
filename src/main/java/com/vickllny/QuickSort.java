package com.vickllny;

import java.util.Arrays;

/**
 * 快排
 */
public class QuickSort {


    /**
     * 将数组中小于等于num的数字放左边，大于num的数字放右边
     *
     * @param arr
     * @param num
     */
    public static void sort1(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, num);
    }

    static void process1(int[] arr, int num) {
        int left = -1;
        int i = 0;
        while (i < arr.length) {
            if (arr[i] <= num) {
                swap(arr, left + 1, i);
                left++;
            }
            i++;
        }
    }

    /**
     * 荷兰国旗问题：将数组中小于num的数字放左边，等于num的数字放中间，大于num的数字放右边，
     *
     * @param arr
     * @param num
     */
    public static void sort2(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, num);
    }

    static void process2(int[] arr, int num) {
        int left = -1;
        int right = arr.length;
        int i = 0;
        while (i < right) {
            if (arr[i] < num) {
                swap(arr, left + 1, i);
                left++;
                i++;
            } else if (arr[i] > num) {
                swap(arr, right - 1, i);
                right--;
            } else {
                i++;
            }
        }
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //使用随机的一个数放在最后作为被比较的数，使得时间复杂度是 O(N * logN)，否则是O(N^2)
            swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
            int[] p = partition(arr, left, right);
            quickSort(arr, left, p[0] - 1);
            quickSort(arr, p[1] + 1, right);
        }
    }

    /**
     * 分区，以数组的右边界作为比较值
     * @param arr
     * @param left
     * @param right
     * @return
     */
    static int[] partition(int[] arr, int left, int right) {
        int less = left - 1; // < 区左边界
        int more = right; // > 区右边界
        while (left < more) {
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, --more, left);
            } else {
                left++;
            }
        }
        swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int k) {
        if (i == k) {
            return;
        }
        arr[i] = arr[i] ^ arr[k];
        arr[k] = arr[i] ^ arr[k];
        arr[i] = arr[i] ^ arr[k];
    }

    public static void main(String[] args) {
//        int[] arr = {17,11,33,69,1,0,-1,32,17,11,33,69};
//        int[] arr = {1,3,4,2,5};
        int[] arr = CommonUtils.generateRandomArray(100, 100);
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
