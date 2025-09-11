package com.vickllny;

/**
 * O(N²)
 */
public class BubbleSort {
    
    
    public static void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
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
