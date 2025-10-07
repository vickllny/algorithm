package com.vickllny;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        int[] arr = new int[nums.length];
        k = k % nums.length;
        for (int i = 0; i < nums.length; i++) {
            int index = (i + k) % nums.length;
            arr[index] = nums[i];
        }
        System.arraycopy(arr, 0, nums, 0, nums.length);
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
        int minPrice = prices[0], val = 0;
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
        int cur = prices[0], val = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] > cur){
                val += prices[i] - cur;
            }
            cur = prices[i];
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
        if(nums.length == 1){
            return true;
        }
        int maxJump = nums[0], index = 1, tempJump;
        while (index < nums.length && index <= maxJump){
            if((tempJump = nums[index] + index) > maxJump){
                maxJump = tempJump;
            }
            index++;
        }
        return index == nums.length;
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
        if(nums.length == 1){
            return 0;
        }
        int maxJump = 0, end = 0, ans = 0;
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
//        int[] nums = {7,0,9,6,9,6,1,7,9,0,1,2,9,0,3};
//        int[] nums = {3,0,6,1,5};
        int[] nums = {1,3,1};
        System.out.println(hIndex(nums));
    }

    static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, index = citations.length - 1;
        while (index >= 0 && h < citations[index]){
            index--;
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

        set.insert(-20);
        set.insert(-47);
        set.remove(-20);
        set.remove(-47);

        set.insert(-119);
        set.insert(-119);
        set.remove(-119);

        set.insert(-99);
        set.remove(-99);
        set.insert(-121);

        System.out.println(set.getRandom());
    }


    class RandomizedSet {

        private List<Integer> values = new ArrayList<>();
        private Map<Integer, Integer> valueToIndex = new HashMap<>();

        public RandomizedSet() {

        }

        public boolean insert(int val) {
            if(valueToIndex.containsKey(val)){
                return false;
            }
            values.add(val);
            valueToIndex.put(val, values.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if(!valueToIndex.containsKey(val)){
                return false;
            }
            final int index = valueToIndex.get(val);
            if(index == values.size() - 1){
                values.remove((int)index);
                valueToIndex.remove(val);
                return true;
            }
            final Integer lastValue = values.get(values.size() - 1);
            values.set(index, lastValue);
            values.remove(values.size() - 1);
            valueToIndex.put(lastValue, index);
            valueToIndex.remove(val);
            return true;
        }

        public int getRandom() {
            final Random random = new Random();
            final int index = random.nextInt(values.size());
            return values.get(index);
        }
    }

    /**
     * 除自身以外数组的乘积
     * https://leetcode.cn/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test12(){
//        int[] nums = {1,2,3,4};
        int[] nums = {-1,1,0,-3,3};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = prefix;
            prefix *= nums[i];
        }
        int post = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = post * res[i];
            post *= nums[i];
        }
        return res;
    }

    /**
     * 加油站
     * https://leetcode.cn/problems/gas-station/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test13(){
//        int[] nums = {1,2,3,4};
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    static int canCompleteCircuit(int[] gas, int[] cost) {
        int size = gas.length;
        for (int i = 0; i < size; ) {
            int j = 0, left = 0;
            while (j < size){
                int k = (i + j) % size;
                left += gas[k] - cost[k];
                if(left < 0){
                    break;
                }
                j++;
            }
            if(j == size){
                return i;
            }
            i += j + 1;
        }
        return -1;
    }

    /**
     * 分发糖果
     * https://leetcode.cn/problems/candy/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test14(){
//        int[] ratings = {1,2,3,4};
        int[] ratings = {1,0,0,1,2,1,3,2};
//        2 1 1 2 3 1 3 2
        System.out.println(candy(ratings));
    }

    static int candy(int[] ratings) {
        final int length = ratings.length;
        int[] left = new int[length];
        for (int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        int[] right = new int[length];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]){
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans += Math.max(left[i], right[i]) + 1;
        }
        return ans;
    }

    /**
     * 接雨水
     * https://leetcode.cn/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-interview-150
     * 解法：前缀和、后缀和
     */
    @Test
    public void test15(){
//        int[] ratings = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] ratings = {4,2,0,3,2,5};
        System.out.println(trap(ratings));
    }

    static int trap(int[] height) {
        final int length = height.length;
        int[] left = new int[length];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        int[] right = new int[length];
        right[length - 1] = height[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans += Math.min(left[i], right[i]) - height[i];
        }
        return ans;
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

    /**
     * 验证回文串
     * https://leetcode.cn/problems/valid-palindrome/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test24(){
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }

    static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if(Character.isLetterOrDigit(c)){
                sb.append(c);
            }
        }
        int lengthed = sb.length();
        int left = 0, right = lengthed - 1;
        while (left < right){
            if(sb.charAt(left++) != sb.charAt(right--)){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断子序列
     * https://leetcode.cn/problems/is-subsequence/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test25(){
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }

    boolean isSubsequence(String s, String t) {
        if(s == null || s.isEmpty()){
            return true;
        }
        if(t == null || t.isEmpty()){
            return false;
        }
        int p1 = 0, p2 = 0;
        while (p1 < s.length() && p2 < t.length()){
            if(s.charAt(p1) == t.charAt(p2)){
                if(p1 == s.length() - 1){
                    return true;
                }
                p1++;
            }
            p2++;
        }
        return false;
    }

    /**
     * 两数之和 II - 输入有序数组
     * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test26(){
        int[] numbers = {-1, 0};
        int target = -1;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    static int[] twoSum(int[] numbers, int target) {
        int p1 = 0, p2 = numbers.length - 1;
        while (p1 < p2){
            int val = numbers[p1] + numbers[p2];
            if(val == target){
                return new int[]{p1 + 1, p2 + 1};
            }else if(val < target){
                p1++;
            }else {
                p2--;
            }
        }
        return null;
    }

    /**
     * 盛最多水的容器
     * https://leetcode.cn/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test27(){
//        int[] numbers = {1,8,6,2,5,4,8,3,7};
        int[] numbers = {1,1};
        System.out.println(maxArea(numbers));
    }

    static int maxArea(int[] height) {
        int p1 = 0, p2 = height.length - 1;
        int ans = 0;
        while (p1 < p2){
            ans = Math.max(Math.min(height[p1], height[p2]) * (p2 - p1), ans);
            if(height[p1] <= height[p2]){
                p1++;
            }else {
                p2--;
            }
        }
        return ans;
    }

    /**
     * 三数之和
     * https://leetcode.cn/problems/3sum/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test28(){
//        int[] numbers = {1,8,6,2,5,4,8,3,7};
        int[] numbers = {1,1};
        System.out.println(threeSum(numbers));
    }

    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];
            while (left < right){
                int val = nums[left] + nums[right];
                if(val == target){
                    list.add(Stream.of(nums[i], nums[left], nums[right]).collect(Collectors.toList()));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }else if(val < target){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return list;
    }

    /**
     * 长度最小的子数组
     * https://leetcode.cn/problems/minimum-size-subarray-sum/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test29(){
//        int[] numbers = {1,8,6,2,5,4,8,3,7};
        int[] numbers = {1,4,4};
        int target = 4;
        System.out.println(minSubArrayLen(target, numbers));
    }

    static int minSubArrayLen(int target, int[] nums) {
        int ans = 0, count = 0, p1 = 0, p2 = 0;
        while (p2 < nums.length){
            count += nums[p2];
            if(count >= target){
                ans = ans == 0 ? p2 - p1 + 1 : Math.min(p2 - p1 + 1, ans);
                //p1向右收敛
                while (p1 < p2 && count - nums[p1] >= target){
                    count -= nums[p1++];
                    ans = Math.min(ans, p2 - p1 + 1);
                }
            }
            p2++;
        }
        return ans;
    }
}
