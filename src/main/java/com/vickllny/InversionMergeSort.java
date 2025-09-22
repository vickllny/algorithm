package com.vickllny;

import com.alibaba.fastjson.JSONObject;

/**
 * 归并排序求逆序对问题
 */
public class InversionMergeSort {
    
    
    public static int sort(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    static int process(int[] arr, int left, int right){
        if(left == right){
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid)
            + process(arr, mid + 1, right)
            + merge(arr, left, mid, right);
    }

    static int merge(int[] arr, int left, int mid, int right){
        int value = 0;
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right){
            value += arr[p1] > arr[p2] ? (mid - p1 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid){
            help[i++] = arr[p1++];
        }
        while (p2 <= right){
            help[i++] = arr[p2++];
        }
        System.arraycopy(help, 0, arr, left, help.length);
        return value;
    }


    private static void swap(int[] arr, int i, int k){
        arr[i] = arr[i] ^ arr[k];
        arr[k] = arr[i] ^ arr[k];
        arr[i] = arr[i] ^ arr[k];
    }

    public static void main(String[] args) {
//        int[] arr = {17,11,33,69,1,0,1,32,17,11,33,69};
        int[] arr = {1,3,4,2,5};
        System.out.println(sort(arr));
        System.out.println(JSONObject.toJSONString(arr));
    }
}
