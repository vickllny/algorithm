package com.vickllny;

import org.junit.Test;

import java.util.*;

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
        int[] nums = {1,2,2,3};
        System.out.println(removeDuplicates(nums));
    }

    static int removeDuplicates(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int i = 0, j = 1;
        while (j < nums.length){
            if(nums[i] == nums[j]){
                j++;
                continue;
            }
            nums[++i] = nums[j++];
        }
        return i + 1;
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
        int i = 0, j = 1;
        while (j < nums.length){
            if(nums[i] == nums[j]){
                if(i == 0){
                    i++;
                    j++;
                    continue;
                }
                if(nums[i] == nums[i - 1]){
                    j++;
                    continue;
                }
            }
            nums[++i] = nums[j++];
        }
        return i + 1;
    }

    /**
     * 多数元素
     * https://leetcode.cn/problems/majority-element/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test4(){
        int[] nums = {3,3,4};
        System.out.println(majorityElement(nums));
    }

    static int majorityElement(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            if(count.containsKey(num)){
                count.put(num, count.get(num) + 1);
            }else {
                count.put(num, 1);
            }
        }
        Optional<Map.Entry<Integer, Integer>> first = count.entrySet().stream().sorted((entry, entry2) -> entry2.getValue().compareTo(entry.getValue())).findFirst();
        if(first.isPresent()){
            return first.get().getKey();
        }
        return -1;
    }

    /**
     * 轮转数组
     * https://leetcode.cn/problems/rotate-array/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test5(){
        int[] nums = {1,2,3};
        int k = 4;
        rotate(nums, k);
    }

    static void rotate(int[] nums, int k) {
        //1、新数组
        int[] arr = new int[nums.length];
        k = k % nums.length;
        for (int i = 0; i < nums.length; i++) {
            arr[i] =  nums[(Math.abs(nums.length - k + i)) % nums.length];
        }
        System.arraycopy(arr, 0, nums, 0, nums.length);
        System.out.println("nums = " + Arrays.toString(nums) + ", k = " + k);
    }

    /**
     * 买卖股票的最佳时机
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test6(){
        int[] nums = {2,4,1};
        System.out.println(maxProfit(nums));
    }

    static int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2){
            return 0;
        }
        int minPrice = prices[0];
        int val = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] < minPrice){
                minPrice = prices[i];
            }else if(prices[i] - minPrice > val){
                val = prices[i] - minPrice;
            }
        }
        return val;
    }

    /**
     * 买卖股票的最佳时机 II
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test7(){
        int[] nums = {7,6,4,3,1};
        System.out.println(maxProfit1(nums));
    }

    static int maxProfit1(int[] prices) {
        if(prices == null || prices.length < 2){
            return 0;
        }
        int minPrice = prices[0];
        int val = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= minPrice) {
                val += prices[i] - minPrice;
            }
            minPrice = prices[i];
        }
        return val;
    }

    /**
     * 跳跃游戏 - 贪心算法
     * https://leetcode.cn/problems/jump-game/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test8(){
        int[] nums = {3,2,1,0,4};
        System.out.println(canJump(nums));
    }

    static boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }
        if(nums.length == 1){
            return true;
        }
        //解法 1
        int index = 0;
        while (true){
            int jump = index + nums[index];
            if(jump == 0){
                return false;
            }
            int temp = 0;
            int jumpIndex = -1;
            for (int i = index + 1; i < jump + 1; i++) {
                if(i >= nums.length - 1){
                    return true;
                }
                if(i + nums[i] > temp){
                    temp = i + nums[i];
                    jumpIndex = i;
                }
            }
            if(jumpIndex == -1){
                return false;
            }
            index = jumpIndex;
        }
        //解法 2
//        int index = 1;
//        int maxJump = nums[0];
//        int tempJump;
//
//        while (index < nums.length && index <= maxJump){
//            if((tempJump = (index + nums[index])) > maxJump){
//                maxJump = tempJump;
//            }
//            index++;
//        }
//        return index == nums.length;
    }

    /**
     * 跳跃游戏 II
     * https://leetcode.cn/problems/jump-game-ii/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test9(){
        int[] nums = {7,0,9,6,9,6,1,7,9,0,1,2,9,0,3};
//        int[] nums = {2,3,1,1,4};
//        int[] nums = {2,3,0,1,4};
//        int[] nums = {1,2,3};
        System.out.println(jump(nums));
    }

    static int jump(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 0;
        }
        if(nums[0] >= nums.length){
            return 1;
        }
        int ans = 0, maxJump = 0, end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxJump = Math.max(maxJump, i + nums[i]);
            if(i == end){
                end = maxJump;
                ans++;
            }
        }
        return ans;
    }


    /**
     * H 指数
     * https://leetcode.cn/problems/h-index/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test10(){
        int[] nums = {7,0,9,6,9,6,1,7,9,0,1,2,9,0,3};
        System.out.println(hIndex(nums));
    }

    static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h){
            i--;
            h++;
        }
        return h;
    }

    /**
     * O(1) 时间插入、删除和获取随机元素
     * https://leetcode.cn/problems/insert-delete-getrandom-o1/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test11(){
        final RandomizedSet set = new RandomizedSet();
        //TODO
    }


    class RandomizedSet {

        public RandomizedSet() {

        }

        public boolean insert(int val) {

        }

        public boolean remove(int val) {

        }

        public int getRandom() {

        }
    }












}
