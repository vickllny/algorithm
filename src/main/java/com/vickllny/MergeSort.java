package com.vickllny;

import com.alibaba.fastjson.JSONObject;


/**
 * 归并排序
 * T(N) = 2 * T(N/2) + O(N^d)
 * a = 2, b = 2, d = 1，满足log(b, a) = d -> 复杂度为 O(N^d * logN)
 * O(N * logN)
 */
public class MergeSort {
    
    
    public static void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    static void process(int[] arr, int left, int right){
        if(left == right){
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right, 0);
    }


    public static int smallerNumber(int[] arr){
        if(arr == null || arr.length  == 1){
            return -1;
        }
        return smallerNumberProcess(arr, 0, arr.length - 1);
    }

    static int smallerNumberProcess(int[] arr, int left, int right){
        if(left == right){
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        int value = 0;
        merge(arr, left, mid, right, value);
        return value;
    }

    static void merge(int[] arr, int left, int mid, int right, int value){
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right){
            if(arr[p1] <= arr[p2]){
                help[i++] = arr[p1++];
                if(arr[p1] != arr[p2]){
                    value += help[i - 1];
                }
            }else {
                help[i++] = arr[p2++];
            }

        }
        while (p1 <= mid){
            help[i++] = arr[p1++];
        }
        while (p2 <= right){
            help[i++] = arr[p2++];
        }
        System.arraycopy(help, 0, arr, left, help.length);
    }


    private static void swap(int[] arr, int i, int k){
        arr[i] = arr[i] ^ arr[k];
        arr[k] = arr[i] ^ arr[k];
        arr[i] = arr[i] ^ arr[k];
    }

    public static void main(String[] args) {
//        int[] arr = {17,11,33,69,1,0,-1,32,17,11,33,69};
        int[] arr = {1,3,4,2,5};
        System.out.println(smallerNumber(arr));;
        System.out.println(JSONObject.toJSONString(arr));
    }
}
