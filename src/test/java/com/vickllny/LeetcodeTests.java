package com.vickllny;

import org.junit.Test;

/**
 * https://leetcode.cn/studyplan/top-interview-150/
 */
public class LeetcodeTests {

    @Test
    /**
     * https://leetcode.cn/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150
     * 合并2个数组
     */
    public void test(){
        int[] nums1 = {-1,0,0,3,3,3,0,0,0};
        int[] nums2 = {1,2,2};

        int m = 6;
        int n = 3;
        mergeSortedArray(nums1, m, nums2, n);
    }

    static void mergeSortedArray(int[] nums1, int m, int[] nums2, int n){
        if(m == 0){
            if(n != 0){
                System.arraycopy(nums2, 0, nums1, 0, n);
            }
            return;
        }else if(n == 0){
            return;
        }


//        int i = 0,j = 0, index = 0;
//        int[] arr = new int[m + n];
//        while (i < m && j < n){
//            arr[index++] = nums1[i] <= nums2[j]? nums1[i++] : nums2[j++];
//        }
//        while (i < m){
//            arr[index++] = nums1[i++];
//        }
//        while (j < n){
//            arr[index++] = nums2[j++];
//        }
//        System.arraycopy(arr, 0, nums1, 0, m + n);

        //从后往前
        int i = m - 1,j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0){
            nums1[index--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
        }
        while (j >= 0){
            nums1[index--] = nums2[j--];
        }
    }

    /**
     * 移除元素
     * https://leetcode.cn/problems/remove-element/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test1(){
        int[] nums = {3,2,2,3};
        int val = 3;
        System.out.println(removeElement(nums, val));
    }

    static int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int right = nums.length - 1;
        int i = 0;
        while (i <= right){
            if(nums[i] == val){
                if(nums[right] == val){
                    right--;
                    continue;
                }
                swap(nums, i, right);
            }
            i++;
        }
        return i;
    }

    static void swap(int[] arr ,int i, int j){
        if(i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 删除有序数组中的重复项
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test2(){

    }

    static int removeDuplicates(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int i = 0, j = 1;
        while (j < nums.length){
            //TODO

            if(nums[i] == nums[j]){
                if(i == 0){
                    i++;
                    j++;
                }else if(nums[i] == nums[i - 1]){
                    swap(nums, i++, j++);
                }else {
                    i++;
                    j++;
                }
            }else {

            }
        }
        return i + 1;
    }
}
