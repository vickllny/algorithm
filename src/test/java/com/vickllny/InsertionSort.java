package com.vickllny;

/**
 * O(N²)
 */
public class InsertionSort {
    
    
    public static void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = i; j >0; j--) {
                if(arr[j] < arr[j - 1]){
                    swap(arr, j, j - 1);
                }
            }
        }
    }


    static void swap(int[] arr, int i, int k){
        arr[i] = arr[i] ^ arr[k];
        arr[k] = arr[i] ^ arr[k];
        arr[i] = arr[i] ^ arr[k];
    }

    public static void main(String[] args) {
        int[] arr = {17,11,33,69,1,0,-1,32,17,11,33,69};
        sort(arr);
        System.out.println(arr);
    }
}
