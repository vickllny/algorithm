package com.vickllny;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetcodeTestsOfTest {

    /**
     * https://leetcode.cn/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150
     * 合并2个数组
     */
    @Test
    public void test(){
        int[] nums1 = {-1,0,0,3,3,3,0,0,0};
        int[] nums2 = {1,2,2};

        int m = 6;
        int n = 3;
        mergeSortedArray(nums1, m, nums2, n);
    }

    static void mergeSortedArray(int[] nums1, int m, int[] nums2, int n){
        if(n == 0){
            return;
        }
        if(m == 0){
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i > -1 && j > -1){
            nums1[index--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        while (j > -1){
            nums1[index--] = nums2[j--];
        }
    }

    /**
     * 移除元素
     * https://leetcode.cn/problems/remove-element/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test1(){
//        int[] nums = {3,2,2,3};
//        int val = 3;
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(removeElement(nums, val));
    }

    static int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0] != val ? 1 : 0;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right){
            if(nums[left] == val){
                swap(nums, left, right--);
                continue;
            }
            left++;
        }
        return left;
    }

    /**
     * 删除有序数组中的重复项
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test2(){
        int[] nums = {1,2,2,3};
        System.out.println(removeDuplicates(nums));
    }

    static int removeDuplicates(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }
        int left = 0, right = 0;
        while (right < nums.length){
            if(nums[left] == nums[right]){
                right++;
                continue;
            }
            nums[++left] = nums[right++];
        }
        return left + 1;
    }


    /**
     * 删除有序数组中的重复项 II
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test3(){
        int[] nums = {1,1,1,2,2,3};
        System.out.println(removeDuplicates1(nums));
    }

    static int removeDuplicates1(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int left = 0, right = 1;
        while (right < nums.length){
            if(nums[left] == nums[right]){
                if(left == 0){
                    left++;
                    right++;
                    continue;
                }
                if(nums[left - 1] == nums[left]){
                    right++;
                    continue;
                }
            }
            nums[++left] =nums[right++];
        }
        return left + 1;
    }

    /**
     * 多数元素
     * https://leetcode.cn/problems/majority-element/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test4(){
//        int[] nums = {3,3,4};
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }

    static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int curVal = nums[0], maxValue = curVal, count = 1, maxCount = 1;
        for (int i = 1; i < nums.length; i++) {
            if(curVal == nums[i]){
                count++;
                continue;
            }
            if(count > maxCount){
                maxValue = curVal;
                maxCount = count;
            }
            count = 1;
            curVal = nums[i];
        }
        if(count > maxCount){
            maxValue = curVal;
        }
        return maxValue;
    }
















    /**
     * 文本左右对齐
     * https://leetcode.cn/problems/text-justification/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test23(){
//        String[] s = {"This", "is", "an", "example", "of", "text", "justification."};
//        String[] s = {"What","must","be","acknowledgment","shall","be"};
        String[] s = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        int maxWidth = 20;
        System.out.println(fullJustify(s, maxWidth));
    }

    static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        for (int i = 0, curWidth = 0; i < words.length; i++) {
            if(words[i].length() + curWidth + cur.size() <= maxWidth){
                curWidth += words[i].length();
                cur.add(words[i]);
            }else {
                list.add(fillSpace(cur, maxWidth, false));
                cur = new ArrayList<>();
                cur.add(words[i]);
                curWidth = words[i].length();
            }
        }
        if(!cur.isEmpty()){
            list.add(fillSpace(cur, maxWidth, true));
        }
        return list;
    }

    static String fillSpace(List<String> words, int maxWidth, boolean last){
        int curWidth = 0;
        final int lastIndex = words.size() - 1;
        for (int i = 0; i < words.size(); i++) {
            if(i != lastIndex){
                words.set(i, words.get(i) + " ");
            }
            curWidth += words.get(i).length();
        }
        if(last || lastIndex == 0){
            for (int i = curWidth; i < maxWidth; i++) {
                words.set(lastIndex, words.get(lastIndex) + " ");
            }
        }else {
            for (int i = 0; curWidth++ < maxWidth; i = (i + 1) % lastIndex) {
                words.set(i, words.get(i) + " ");
            }
        }
        return String.join("", words);
    }


    static void swap(int[] arr, int i, int j){
        if(i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
