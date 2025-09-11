package com.vickllny;

public class BinarySearch {

    /**
     * 普通二分查找
     * @param arr
     * @param member
     * @return
     */
    public static int search(int[] arr, int member){
        if(arr == null || arr.length == 0){
            return -1;
        }
        if(arr.length == 2){
            return arr[0] == member ? 0 : (arr[1] == member ? 1 : -1);
        }
        int begin = 0, end = arr.length - 1;
        if(arr[begin] > member || arr[end] < member){
            return -1;
        }
        while (true){
            if(begin == end){
                return arr[begin] == member ? begin : -1;
            }else if(begin == end - 1){
                return arr[begin] == member ? begin :(arr[end] == member ? end : -1);
            }
            int c = (begin + end) / 2;
            if(arr[c] == member){
                return c;
            }else if(arr[c] > member){
                end = c - 1;
            }else {
                begin = c + 1;
            }
        }
    }

    /**
     * 基于二分查找最左边界
     * @param arr
     * @param member
     * @return
     */
    public static int searchLowerBound(int[] arr, int member){
        if(arr == null || arr.length == 0){
            return -1;
        }
        if(arr.length == 2){
            return arr[0] == member ? 0 : (arr[1] == member ? 1 : -1);
        }
        int begin = 0, end = arr.length - 1;
        if(arr[begin] > member){
            return 0;
        }else if(arr[end] < member){
            return end + 1;
        }
        int r = -1;
        while (true){
            if(begin == end){
                final int tempR = arr[begin] == member ? begin : -1;
                if(tempR != -1){
                    r = r != -1 ? Math.min(r, tempR) : tempR;
                }else if(r == -1){
                    r = arr[begin] < member ? end - 1 : end + 1;
                }
                break;
            }else if(begin == end - 1){
                final int tempR = arr[begin] == member ? begin :(arr[end] == member ? end : -1);
                if(tempR != -1){
                    r = r != -1 ? Math.min(r, tempR) : tempR;
                }else if(r == -1){
                    r = arr[begin] < member ? end : begin;
                }
                break;
            }
            int c = (begin + end) / 2;
            if(arr[c] == member){
                r = r != -1 ? Math.min(r, c) : c;
                end = c - 1;
            }else if(arr[c] > member){
                end = c - 1;
            }else {
                begin = c + 1;
            }
        }
        return r;
    }

    /**
     * 局部最小值问题
     * @param arr
     * @return
     */
    public static int localMinimum(int[] arr){
        if(arr == null){
            return -1;
        }else if(arr.length == 2){
            return arr[0] < arr[1] ? 0 : 1;
        }
        int n = arr.length - 1;
        if(arr[0] < arr[1]){
            return 0;
        }else if(arr[n] < arr[n - 1]){
            return n;
        }
        int begin = 0, end = n;

        while (true){
            int m = (begin + end) / 2;
            int m1 = m - 1;
            int m2 = m + 1;
            if(arr[m] < arr[m1] && arr[m] < arr[m2]){
                return m;
            }else if(arr[m] > arr[m - 1]){
                end = m;
            }else {
                begin = m;
            }
        }
    }

    public static void main(String[] args) {
        //search
//        int[] arr = {1, 3, 5, 7, 9}; int member = 5;
//        int[] arr = {2, 4, 6, 8, 10}; int member = 2;
//        int[] arr = {1, 2, 3, 4, 5}; int member = 5;
//        int[] arr = {10, 20, 30, 40}; int member = 5;
//        int[] arr = {5, 15, 25, 35}; int member = 50;
//        int[] arr = {1, 3, 5, 7, 9}; int member = 6;
//        int[] arr = {100}; int member = 100;
//        int[] arr = {42}; int member = 99;
//        int[] arr = {2, 4, 4, 4, 6, 8}; int member = 4;
//        int[] arr = {}; int member = 4;


        //searchLowerBound
//        int[] arr = {1, 2, 2, 2, 3, 4}; int member = 2;
//        int[] arr = {5, 6, 7, 8}; int member = 1;
//        int[] arr = {10, 20, 30, 40}; int member = 100;
//        int[] arr = {2, 4, 6, 8, 10}; int member = 6;
//        int[] arr = {1, 3, 5, 7, 9}; int member = 6;
//        int[] arr = {2, 2, 2, 2, 2}; int member = 2;
//        int[] arr = {7, 7, 7, 7, 7}; int member = 5;
//        int[] arr = {7, 7, 7, 7, 7}; int member = 9;
//        int[] arr = {1, 2, 4, 6, 8}; int member = 5;


        //localMinimum
//        int[] arr = {9, 6, 3, 14, 5, 7, 4};
//        int[] arr = {1, 3, 5, 7, 9};
//        int[] arr = {10, 8, 6, 4, 2};
//        int[] arr = {10, 5, 8, 2, 6, 1, 7};
        int[] arr = {4, 9};
        System.out.println(localMinimum(arr));
    }
}
