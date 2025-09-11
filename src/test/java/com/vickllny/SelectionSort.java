package com.vickllny;

/**
 * O(N²)
 */
public class SelectionSort {
    
    
    public static void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[k]){
                    k = j;
                }
            }
            if(k != i){
                swap(arr, i, k);
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
