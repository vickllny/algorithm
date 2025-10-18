package com.vickllny;

import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数组字符串相关的题
 * https://leetcode.cn/studyplan/top-interview-150/
 */
public class LeetcodeTests {

    /**
     * https://leetcode.cn/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150
     * 合并2个数组
     */
    @Test
    public void test() {
        int[] nums1 = {-1, 0, 0, 3, 3, 3, 0, 0, 0};
        int[] nums2 = {1, 2, 2};

        int m = 6;
        int n = 3;
        mergeSortedArray(nums1, m, nums2, n);
    }

    static void mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            if (n != 0) {
                System.arraycopy(nums2, 0, nums1, 0, n);
            }
            return;
        } else if (n == 0) {
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
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[index--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
        }
        while (j >= 0) {
            nums1[index--] = nums2[j--];
        }
    }

    /**
     * 移除元素
     * https://leetcode.cn/problems/remove-element/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test1() {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        System.out.println(removeElement(nums, val));
    }

    static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == val) {
                if (nums[right] == val) {
                    right--;
                    continue;
                }
                swap(nums, i, right);
            }
            i++;
        }
        return i;
    }

    static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    static void swap(int[][] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i][j] = arr[i][j] ^ arr[j][i];
        arr[j][i] = arr[i][j] ^ arr[j][i];
        arr[i][j] = arr[i][j] ^ arr[j][i];
    }

    static void swap(int[][] arr, int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) {
            return;
        }
        arr[x1][y1] = arr[x1][y1] ^ arr[x2][y2];
        arr[x2][y2] = arr[x1][y1] ^ arr[x2][y2];
        arr[x1][y1] = arr[x1][y1] ^ arr[x2][y2];
    }

    /**
     * 删除有序数组中的重复项
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test2() {
        int[] nums = {1, 2, 2, 3};
        System.out.println(removeDuplicates(nums));
    }

    static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j]) {
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
    public void test3() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(removeDuplicates1(nums));
    }

    static int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                if (i == 0) {
                    i++;
                    j++;
                    continue;
                }
                if (nums[i] == nums[i - 1]) {
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
    public void test4() {
        int[] nums = {3, 3, 4};
        System.out.println(majorityElement(nums));
    }

    static int majorityElement(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            if (count.containsKey(num)) {
                count.put(num, count.get(num) + 1);
            } else {
                count.put(num, 1);
            }
        }
        Optional<Map.Entry<Integer, Integer>> first = count.entrySet().stream().sorted((entry, entry2) -> entry2.getValue().compareTo(entry.getValue())).findFirst();
        if (first.isPresent()) {
            return first.get().getKey();
        }
        return -1;
    }

    /**
     * 轮转数组
     * https://leetcode.cn/problems/rotate-array/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test5() {
        int[] nums = {1, 2, 3};
        int k = 4;
        rotate(nums, k);
    }

    static void rotate(int[] nums, int k) {
        //1、新数组
        int[] arr = new int[nums.length];
        k = k % nums.length;
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[(Math.abs(nums.length - k + i)) % nums.length];
        }
        System.arraycopy(arr, 0, nums, 0, nums.length);
        System.out.println("nums = " + Arrays.toString(nums) + ", k = " + k);
    }

    /**
     * 买卖股票的最佳时机
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test6() {
        int[] nums = {2, 4, 1};
        System.out.println(maxProfit(nums));
    }

    static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int minPrice = prices[0];
        int val = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > val) {
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
    public void test7() {
        int[] nums = {7, 6, 4, 3, 1};
        System.out.println(maxProfit1(nums));
    }

    static int maxProfit1(int[] prices) {
        if (prices == null || prices.length < 2) {
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
    public void test8() {
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
    }

    static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        //解法 1
        int index = 0;
        while (true) {
            int jump = index + nums[index];
            if (jump == 0) {
                return false;
            }
            int temp = 0;
            int jumpIndex = -1;
            for (int i = index + 1; i < jump + 1; i++) {
                if (i >= nums.length - 1) {
                    return true;
                }
                if (i + nums[i] > temp) {
                    temp = i + nums[i];
                    jumpIndex = i;
                }
            }
            if (jumpIndex == -1) {
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
    public void test9() {
        int[] nums = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
//        int[] nums = {2,3,1,1,4};
//        int[] nums = {2,3,0,1,4};
//        int[] nums = {1,2,3};
        System.out.println(jump(nums));
    }

    static int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] >= nums.length) {
            return 1;
        }
        int ans = 0, maxJump = 0, end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxJump = Math.max(maxJump, i + nums[i]);
            if (i == end) {
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
    public void test10() {
        int[] nums = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
        System.out.println(hIndex(nums));
    }

    static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
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
    public void test11() {
        final RandomizedSet set = new RandomizedSet();

        set.insert(1);
        set.insert(1);
        set.insert(2);
        set.insert(3);
        set.insert(4);
        set.insert(5);
        set.insert(6);
        System.out.println(set.remove(1));
        System.out.println(set.remove(7));
        System.out.println(set.remove(3));
    }


    class RandomizedSet {

        private List<Integer> values = new ArrayList<>();
        private Map<Integer, Integer> valueToIndex = new HashMap<>();

        public RandomizedSet() {

        }

        public boolean insert(int val) {
            if (valueToIndex.containsKey(val)) {
                return false;
            }
            values.add(val);
            valueToIndex.put(val, values.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if (valueToIndex.containsKey(val)) {
                int indexToRemove = valueToIndex.get(val);
                if (indexToRemove == values.size() - 1 || values.size() == 1) {
                    valueToIndex.remove(val);
                    values.remove(indexToRemove);
                    return true;
                }
                //最后的值
                int lastIndex = values.size() - 1;
                int lastVal = values.get(lastIndex);
                //交换
                values.set(indexToRemove, lastVal);
                values.remove(lastIndex);
                valueToIndex.remove(val);
                valueToIndex.put(lastVal, indexToRemove);
                return true;
            }
            return false;
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
    public void test12() {
//        int[] nums = {1,2,3,4};
        int[] nums = {-1, 1, 0, -3, 3};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 1) {
            return nums;
        }
        int[] res = new int[nums.length];
        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = prefix;
            prefix = prefix * nums[i];
        }
        int post = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = res[i] * post;
            post = post * nums[i];
        }
        return res;
    }


    /**
     * 加油站
     * https://leetcode.cn/problems/gas-station/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test13() {
//        int[] nums = {1,2,3,4};
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    static int canCompleteCircuit(int[] gas, int[] cost) {
        int size = gas.length;
        for (int i = 0; i < size; ) {
            int j = i, left = 0;
            while (j < size) {
                int k = (i + j) % size;
                left += gas[k] - cost[k];
                if (left < 0) {
                    break;
                }
                j++;
            }
            if (j == size) {
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
    public void test14() {
//        int[] ratings = {1,2,3,4};
        int[] ratings = {1, 0, 0, 1, 2, 1, 3, 2};
//        2 1 1 2 3 1 3 2
        System.out.println(candy(ratings));
    }

    static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        if (ratings.length == 1) {
            return 1;
        } else if (ratings.length == 2) {
            return ratings[0] == ratings[1] ? 2 : 3;
        }

        // 1.给每个孩子默认分配ratings[i] + 1 个苹果
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        //左侧
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;
        }
        //右侧
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1;
        }
        int res = 0;
        for (int i = 0; i < ratings.length; i++) {
            res += Math.max(left[i], right[i]) + 1;
        }
        return res;
    }

    /**
     * 接雨水
     * https://leetcode.cn/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-interview-150
     * 解法：前缀和、后缀和
     */
    @Test
    public void test15() {
//        int[] ratings = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] ratings = {4, 2, 0, 3, 2, 5};
        System.out.println(trap(ratings));
    }

    static int trap(int[] height) {
        int[] left = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        int[] right = new int[height.length];
        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            ans += Math.min(left[i], right[i]) - height[i];
        }
        return ans;
    }

    /**
     * 罗马数字转整数
     * https://leetcode.cn/problems/roman-to-integer/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test16() {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }

    static Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};


    static int romanToInt(String s) {
        //IV 4 IX 9
        //XL 40 XC 90
        //CD 400 CM 900

//        I             1
//        V             5
//        X             10
//        L             50
//        C             100
//        D             500
//        M             1000
        int ans = 0;
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            final Integer val = symbolValues.get(c);
            if (val == null) {
                continue;
            }
            if (i < charArray.length - 1) {
                final Integer val2 = symbolValues.get(charArray[i + 1]);
                if (val < val2) {
                    ans -= val;
                } else {
                    ans += val;
                }
                continue;
            }
            ans += val;
        }
        return ans;
    }

    /**
     * 整数转罗马数字
     * https://leetcode.cn/problems/integer-to-roman/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test17() {
        int val = 500;
        System.out.println(intToRoman(val));
    }

    static String intToRoman(int num) {
        int tempVal = num;
        StringBuilder s = new StringBuilder();
        //M
        int c = tempVal / 1000;
        tempVal = tempVal % 1000;
        for (int i = 0; i < c; i++) {
            s.append("M");
        }
        //D
        if (tempVal >= 900) {
            s.append("CM");
            tempVal = tempVal % 900;
        } else if (tempVal >= 400 && tempVal < 500) {
            s.append("CD");
            tempVal = tempVal % 400;
        } else {
            c = tempVal / 500;
            tempVal = tempVal % 500;
            for (int i = 0; i < c; i++) {
                s.append("D");
            }
        }
        //C
        c = tempVal / 100;
        tempVal = tempVal % 100;
        for (int i = 0; i < c; i++) {
            s.append("C");
        }
        //L
        if (tempVal >= 90) {
            s.append("XC");
            tempVal = tempVal % 90;
        } else if (tempVal >= 40 && tempVal < 50) {
            s.append("XL");
            tempVal = tempVal % 40;
        } else {
            c = tempVal / 50;
            tempVal = tempVal % 50;
            for (int i = 0; i < c; i++) {
                s.append("L");
            }
        }
        //X
        c = tempVal / 10;
        tempVal = tempVal % 10;
        for (int i = 0; i < c; i++) {
            s.append("X");
        }
        //V
        if (tempVal == 9) {
            s.append("IX");
            tempVal = tempVal % 9;
        } else if (tempVal == 4) {
            s.append("IV");
            tempVal = tempVal % 4;
        } else {
            c = tempVal / 5;
            tempVal = tempVal % 5;
            for (int i = 0; i < c; i++) {
                s.append("V");
            }
        }
        //I
        for (int i = 0; i < tempVal; i++) {
            s.append("I");
        }
        return s.toString();
    }

    /**
     * 最后一个单词的长度
     * https://leetcode.cn/problems/length-of-last-word/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test18() {
        String val = "Hello World";
        System.out.println(lengthOfLastWord(val));
    }

    static int lengthOfLastWord(String s) {
        if (!s.contains(" ")) {
            return s.length();
        }
        final String[] s1 = s.split(" ");
        for (int i = s1.length - 1; i >= 0; i--) {
            if (s1[i].equals(" ")) {
                continue;
            }
            return s1[i].length();
        }
        return 0;
    }

    /**
     * 最长公共前缀
     * https://leetcode.cn/problems/longest-common-prefix/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test19() {
        String[] val = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(val));
    }

    static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        final char[] charArray = strs[0].toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() - 1 < i || strs[j].charAt(i) != c) {
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /**
     * 反转字符串中的单词
     * https://leetcode.cn/problems/reverse-words-in-a-string/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test20() {
        String s = "  hello world  ";
        System.out.println(reverseWords(s));
    }

    static String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        if (s.trim().isEmpty()) {
            return s;
        }
        final String[] s1 = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = s1.length - 1; i >= 0; i--) {
            if (s1[i].isEmpty() || s1[i].equals(" ")) {
                continue;
            }
            sb.append(s1[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    /**
     * Z 字形变换
     * https://leetcode.cn/problems/zigzag-conversion/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test21() {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convert(s, numRows));
    }

    static String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        boolean down = false; //向下
        String[] s1 = new String[numRows];
        final char[] charArray = s.toCharArray();
        for (int i = 0, row = 0; i < s.length(); i++) {
            s1[row] = s1[row] == null ? String.valueOf(charArray[i]) : s1[row] + charArray[i];
            if (row == 0 || row == numRows - 1) {
                down = !down;
            }
            row += down ? 1 : -1;
        }

        StringBuilder sb = new StringBuilder();
        for (final String string : s1) {
            if (string != null) {
                sb.append(string);
            }
        }
        return sb.toString();
    }

    /**
     * 找出字符串中第一个匹配项的下标
     * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test22() {
        String s = "PAYPALISHIRING";
        String numRows = "aaa";
        System.out.println(strStr(s, numRows));
    }

    int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * 文本左右对齐
     * https://leetcode.cn/problems/text-justification/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test23() {
//        String[] s = {"This", "is", "an", "example", "of", "text", "justification."};
//        String[] s = {"What","must","be","acknowledgment","shall","be"};
        String[] s = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        int maxWidth = 20;
        System.out.println(fullJustify(s, maxWidth));
    }

    static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        for (int i = 0, curWidth = 0; i < words.length; i++) {
            if (words[i].length() + curWidth + cur.size() <= maxWidth) {
                curWidth += words[i].length();
                cur.add(words[i]);
            } else {
                list.add(fillSpace(cur, maxWidth, false));
                cur = new ArrayList<>();
                cur.add(words[i]);
                curWidth = words[i].length();
            }
        }
        list.add(fillSpace(cur, maxWidth, true));
        return list;
    }

    static String fillSpace(List<String> words, int maxWidth, boolean last) {
        int curWidth = 0;
        final int lastIndex = words.size() - 1;
        for (int i = 0; i < words.size(); i++) {
            if (i != lastIndex) {
                words.set(i, words.get(i) + " ");
            }
            curWidth += words.get(i).length();
        }
        if (words.size() == 1 || last) {
            for (int i = curWidth; i < maxWidth; i++) {
                words.set(lastIndex, words.get(lastIndex) + " ");
            }
        } else {
            for (int i = 0; curWidth++ < maxWidth; i = (i + 1) % (lastIndex)) {
                words.set(i, words.get(i) + " ");
            }
        }
        return String.join("", words);
    }

    static List<String> fullJustify1(String[] words, int maxWidth) {
        List<String> cur = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0, curWidth = 0; i < words.length; i++) {
            if (words[i].length() + curWidth + cur.size() <= maxWidth) {
                cur.add(words[i]);
                curWidth += words[i].length();
            } else {
                ans.add(fillSpace1(cur, maxWidth, false));
                cur = new ArrayList<>();
                cur.add(words[i]);
                curWidth = words[i].length();
            }
        }
        ans.add(fillSpace1(cur, maxWidth, true));
        return ans;
    }


    static String fillSpace1(List<String> words, int maxWidth, boolean last) {
        int curWidth = 0;
        final int lastIndex = words.size() - 1;
        for (int i = 0; i < words.size(); i++) {
            if (i != lastIndex) {
                words.set(i, words.get(i) + " ");
            }
            curWidth += words.get(i).length();
        }

        if (lastIndex == 0 || last) {
            for (int i = curWidth; i < maxWidth; i++) {
                words.set(lastIndex, words.get(lastIndex) + " ");
            }
        } else {
            for (int i = 0; curWidth++ < maxWidth; i = (i + 1) % lastIndex) {
                words.set(i, words.get(i) + " ");
            }
        }
        return String.join("", words);
    }


    /**
     * 验证回文串
     * https://leetcode.cn/problems/valid-palindrome/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test24() {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }

    static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (final char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        final int length = sb.length();
        int left = 0, right = length - 1;
        while (left < right) {
            if (Character.toLowerCase(sb.charAt(left++)) != Character.toLowerCase(sb.charAt(right--))) {
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
    public void test25() {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }

    boolean isSubsequence(String s, String t) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        if (t == null || t.isEmpty()) {
            return false;
        }
        int p = 0;
        final int s_len = s.length();
        final int length = t.length();
        for (int i = 0; i < length; i++) {
            if (s.charAt(p) == t.charAt(i)) {
                if (p == s_len - 1) {
                    return true;
                }
                p++;
            }
        }
        return false;
    }

    /**
     * 两数之和 II - 输入有序数组
     * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test26() {
        int[] numbers = {-1, 0};
        int target = -1;
        System.out.println(Arrays.toString(twoSum2(numbers, target)));
    }

    static int[] twoSum2(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int val = numbers[left] + numbers[right];
            if (val == target) {
                break;
            } else if (val < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{left + 1, right + 1};
    }


    /**
     * 盛最多水的容器
     * https://leetcode.cn/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test27() {
//        int[] numbers = {1,8,6,2,5,4,8,3,7};
        int[] numbers = {1, 1};
        System.out.println(maxArea(numbers));
    }

    static int maxArea(int[] height) {
        int maxArea = 0, left = 0, right = height.length - 1;
        while (left < right) {
            maxArea = Math.max(Math.min(height[left], height[right]) * (right - left), maxArea);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }


    /**
     * 三数之和
     * https://leetcode.cn/problems/3sum/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test28() {
//        int[] numbers = {1,8,6,2,5,4,8,3,7};
        int[] numbers = {1, 1};
        System.out.println(threeSum(numbers));
    }

    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];
            while (left < right) {
                int tempVal = nums[left] + nums[right];
                if (tempVal == target) {
                    final List<Integer> arrayList = Arrays.asList(nums[i], nums[left], nums[right]);
                    list.add(arrayList);
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (tempVal < target) {
                    left++;
                } else {
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
    public void test29() {
//        int[] numbers = {1,8,6,2,5,4,8,3,7};
        int[] numbers = {1, 4, 4};
        int target = 4;
        System.out.println(minSubArrayLen(target, numbers));
    }

    static int minSubArrayLen(int target, int[] nums) {
        int ans = 0;
        int count = 0, p1 = 0, p2 = 0;
        while (p1 < nums.length && p2 < nums.length) {
            count += nums[p2];
            if (count >= target) {
                ans = ans == 0 ? p2 - p1 + 1 : Math.min(p2 - p1 + 1, ans);
                //向右收敛
                while (p1 <= p2 && count - nums[p1] >= target) {
                    count -= nums[p1];
                    p1++;
                    ans = Math.min(p2 - p1 + 1, ans);
                }
            }
            p2++;
        }
        return ans;
    }

    /**
     * 无重复字符的最长子串
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test30() {
//        String s = "abcabcbb";
//        String s = "bbbb";
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    static int lengthOfLongestSubstring(String s) {
        int ans = 0, length = s.length(), p1 = 0, p2 = p1;
        Set<Character> set = new HashSet<>();
        while (p1 < length) {
            while (p2 < length && set.add(s.charAt(p2))) {
                p2++;
                ans = Math.max(ans, set.size());
            }
            set.remove(s.charAt(p1));
            p1++;
        }
        return ans;
    }

    /**
     * 串联所有单词的子串
     * https://leetcode.cn/problems/substring-with-concatenation-of-all-words/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test31() {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
//        String s = "wordgoodgoodgoodbestword";
//        String[] words = {"word","good","best","word"};
//        String s = "barfoofoobarthefoobarman";
//        String[] words = {"bar","foo","the"};
//        String s = "wordgoodgoodgoodbestword";
//        String[] words = {"word","good","best","good"};
        System.out.println(findSubstring(s, words));
    }

    static List<Integer> findSubstring(String s, String[] words) {
        //1.超时写法
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }

        int w_len = words[0].length();
        int v = words.length * w_len;
        int length = s.length();
        List<Integer> list = new ArrayList<>();
        //2.优化写法
        for (int i = 0; i < w_len; i++) {
            Map<String, Integer> window = new HashMap<>();
            int count = 0;
            for (int j = i; j + w_len <= length; j += w_len) {
                if (j - i >= words.length * w_len) {
                    String word = s.substring(j - words.length * w_len, j - words.length * w_len + w_len);
                    if (window.containsKey(word)) {
                        window.put(word, window.get(word) - 1);
                    } else {
                        window.put(word, 0);
                    }
                    if (map.get(word) != null && window.get(word) < map.get(word)) {
                        count--;
                    }
                }

                String word = s.substring(j, j + w_len);
                if (window.containsKey(word)) {
                    window.put(word, window.get(word) + 1);
                } else {
                    window.put(word, 1);
                }
                if (map.get(word) != null && window.get(word) <= map.get(word)) {
                    count++;
                }
                if (count == words.length) {
                    list.add(j - (words.length - 1) * w_len);
                }
            }
        }

        //1.超时写法
//        for (int i = 0, right = i + v; i <= length - v; i++, right++) {
//            int j = i;
//            int count = 0;
//            Map<String, Integer> tempMap = new HashMap<>();
//            while (j < right){
//                String word = s.substring(j, j + w_len);
//                if(map.containsKey(word)){
//                    if(tempMap.containsKey(word)){
//                        tempMap.put(word, tempMap.get(word) + 1);
//                    }else {
//                        tempMap.put(word, 1);
//                    }
//                    if(tempMap.get(word) <= map.get(word)){
//                        count++;
//                    }else {
//                        break;
//                    }
//                }else {
//                    break;
//                }
//                j += w_len;
//            }
//            if(count == words.length){
//                list.add(i);
//            }
//        }
        return list;
    }

    /**
     * 最小覆盖子串
     * https://leetcode.cn/problems/minimum-window-substring/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test32() {
//        String s = "ADOBECODEBANC";
//        String t = "ABC";
//        String s = "a";
//        String t = "aa";
        String s = "ab";
        String t = "b";
        System.out.println(minWindow(s, t));
    }

    static String minWindow(String s, String t) {
        int t_len = t.length();
        int s_len = s.length();
        if (s_len < t_len) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t_len; i++) {
            char c = t.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
                window.put(c, 0);
            }
        }

        int p1 = 0, minWindow = 0, count = 0, targetP1 = -1, targetP2 = -1;
        for (int p2 = 0; p2 < s_len; p2++) {
            char c = s.charAt(p2);
            if (!window.containsKey(c)) {
                continue;
            }
            window.put(c, window.get(c) + 1);
            if (window.get(c) <= map.get(c)) {
                //有效字符
                count++;
            }

            if (count == t_len) {
                int tempWindow = p2 - p1 + 1;
                if (minWindow == 0 || tempWindow < minWindow) {
                    //第一次
                    minWindow = tempWindow;
                    targetP1 = p1;
                    targetP2 = p2;
                }
                //移除第一个count
                while (p1 <= p2 && count == t_len) {
                    char c1 = s.charAt(p1);
                    if (window.containsKey(c1)) {
                        if (window.get(c1) <= map.get(c1)) {
                            count--;
                        }
                        window.put(c1, window.get(c1) - 1);
                    }
                    tempWindow = p2 - p1 + 1;
                    if (minWindow == 0 || tempWindow < minWindow) {
                        //第一次
                        minWindow = tempWindow;
                        targetP1 = p1;
                        targetP2 = p2;
                    }
                    p1++;
                }
            }
        }
        return targetP1 != -1 ? s.substring(targetP1, targetP2 + 1) : "";
    }


    /**
     * 有效的数独
     * https://leetcode.cn/problems/valid-sudoku/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test33() {
//        char[][] board = new char[][]{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        char[][] board = new char[][]{{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(isValidSudoku(board));
        char c = '9';
        System.out.println(c);
        System.out.println((int) c);
    }

    static boolean isValidSudoku(char[][] board) {
        boolean[] mark = new boolean[9];
        //横向
        for (char[] chars : board) {
            Arrays.fill(mark, false);
            for (int j = 0; j < 9; j++) {
                char c = chars[j];
                if (c == '.') {
                    continue;
                }
                int index = c - 49;
                if (mark[index]) {
                    return false;
                }
                mark[index] = true;
            }
        }
        //纵向
        for (int i = 0; i < 9; i++) {
            Arrays.fill(mark, false);
            for (int j = 0; j < 9; j++) {
                char c = board[j][i];
                if (c == '.') {
                    continue;
                }
                int index = c - 49;
                if (mark[index]) {
                    return false;
                }
                mark[index] = true;
            }
        }
        //九宫格
        Map<String, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            char[] chars = board[i];
            for (int j = 0; j < chars.length; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                Set<Character> set = map.computeIfAbsent(_key(i, j), v -> new HashSet<>());
                if (!set.add(c)) {
                    return false;
                }
            }
        }

        return true;
    }

    static String _key(int i, int j) {
        if (i <= 2 && j <= 2) {
            return "1";
        } else if (i > 2 && i <= 5 && j <= 2) {
            return "2";
        } else if (i > 5 && i <= 8 && j <= 2) {
            return "3";
        } else if (i <= 2 && j <= 5) {
            return "4";
        } else if (i > 2 && i <= 5 && j <= 5) {
            return "5";
        } else if (i > 5 && i <= 8 && j <= 5) {
            return "6";
        } else if (i <= 2 && j <= 8) {
            return "7";
        } else if (i > 2 && i <= 5 && j <= 8) {
            return "8";
        } else if (i > 5 && i <= 8 && j <= 8) {
            return "9";
        }
        return "";
    }


    /**
     * 螺旋矩阵
     * https://leetcode.cn/problems/valid-sudoku/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test34() {
//        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder(matrix));
    }

    static List<Integer> spiralOrder(int[][] matrix) {


        int m = matrix.length;
        int n = matrix[0].length;
        int x1 = 0, x2 = m, y1 = 0, y2 = n;
        int count = 0;
        //x 纵向， y 横向
        int x = 0, y = 0;
        List<Integer> list = new ArrayList<>();
        while (count < m * n) {
            //从左往右 - 横向
            while (y < y2) {
                list.add(matrix[x][y]);
                count++;
                y++;
            }
            if (count == m * n) {
                break;
            }
            y--;
            x++;
            //右下
            while (x < x2) {
                list.add(matrix[x][y]);
                count++;
                x++;
            }
            if (count == m * n) {
                break;
            }
            x--;
            y--;
            //从右往左
            while (y >= y1) {
                list.add(matrix[x][y]);
                count++;
                y--;
            }
            if (count == m * n) {
                break;
            }
            x--;
            y++;
            //从下往上
            while (x > x1) {
                list.add(matrix[x][y]);
                count++;
                x--;
            }
            y++;
            x++;
            //重新计算x1 x2 y1 y2
            x1++;
            x2--;
            y1++;
            y2--;

        }
        return list;
    }

    /**
     * 旋转图像
     * https://leetcode.cn/problems/rotate-image/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test35() {
//        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
//        System.out.println(3 >> 1);
    }

    static void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //对角线变换
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        //左右变换 [0, 0] - [0, n - 1]
        int nMid = n >> 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < nMid; j++) {
                swap(matrix, i, j, i, n - 1 - j);
            }
        }
    }

    /**
     * 矩阵置零
     * https://leetcode.cn/problems/set-matrix-zeroes/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test36() {
//        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        int[][] matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
//        System.out.println(3 >> 1);
    }

    static void setZeroes(int[][] matrix) {
        // 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
//        Set<Integer> ySet = new HashSet<>();
//        Set<Integer> xSet = new HashSet<>();
//
//        int x_len = matrix.length;
//        int y_len = matrix[0].length;
//        for (int i = 0; i < x_len; i++) {
//            for (int j = 0; j < y_len; j++) {
//                if(matrix[i][j] == 0){
//                    ySet.add(j);
//                    xSet.add(i);
//                }
//            }
//        }
//        for (int i = 0; i < x_len; i++) {
//            for (int j = 0; j < y_len; j++) {
//                if(ySet.contains(j) || xSet.contains(i)){
//                    matrix[i][j] = 0;
//                }
//            }
//        }

        //TODO 能想出一个仅使用常量空间的解决方案吗？
        boolean rowZero = false, colZero = false;
        int x_len = matrix.length;
        int y_len = matrix[0].length;
        for (int i = 0; i < x_len; i++) {
            for (int j = 0; j < y_len; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = matrix[i][0] = 0;
                    if (j == 0) {
                        colZero = true;
                    }
                    if (i == 0) {
                        rowZero = true;
                    }
                }
            }
        }

        for (int i = 1; i < x_len; i++) {
            for (int j = 1; j < y_len; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }


        for (int i = 0; rowZero && i < y_len; i++) {
            matrix[0][i] = 0;
        }
        for (int i = 0; colZero && i < x_len; i++) {
            matrix[i][0] = 0;
        }

    }

    /**
     * 生命游戏
     * https://leetcode.cn/problems/game-of-life/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test37() {
//        int[][] board = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
//        System.out.println(3 >> 1);
    }

    static void gameOfLife(int[][] board) {
        //标记 存活：2 - 0 死亡，3 - 1 存活
        int rows = board.length, cols = board[0].length, ln = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ln = 0;
                //获取存活的邻居数量
                for (int x = Math.max(i - 1, 0); x < i + 2 && x >= 0 && x < rows; x++) {
                    for (int y = Math.max(j - 1, 0); y >= 0 && y < j + 2 && y < cols; y++) {
                        if (x == i && y == j) {
                            continue;
                        }
                        if (board[x][y] > 0) {
                            ln++;
                        }
                    }
                }

                if (board[i][j] == 1) {
                    //活细胞
                    if (ln > 3 || ln < 2) {
                        board[i][j] = 2;
                    } else {
                        board[i][j] = 3;
                    }
                } else if (ln == 3) {
                    board[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 3 || board[i][j] == -1) {
                    board[i][j] = 1;
                } else if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
            }
        }
    }

    /**
     * 赎金信
     * https://leetcode.cn/problems/ransom-note/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test38() {
        String ransomNote = "aa";
        String magazine = "aab";
        System.out.println(canConstruct(ransomNote, magazine));
    }

    static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            }
        }

        return map.isEmpty();
    }

    /**
     * 同构字符串
     * https://leetcode.cn/problems/isomorphic-strings/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test39() {
        String s = "badc";
        String t = "baba";
        System.out.println(isIsomorphic(s, t));
    }

    static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                if (map.containsValue(c2)) {
                    return false;
                }
                map.put(c1, c2);
            }
        }
        return true;
    }

    /**
     * 单词规律
     * https://leetcode.cn/problems/word-pattern/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test40() {
//        String pattern = "abba";
//        String s  = "dog cat cat dog";
//        String pattern = "abba";
//        String s  = "dog cat cat fish";
        String pattern = "aaaa";
        String s = "dog cat cat dog";
        System.out.println(wordPattern(pattern, s));
    }

    static boolean wordPattern(String pattern, String s) {
        Map<String, String> map = new HashMap<>();
        String[] strings = s.split(" ");
        if (pattern.length() != strings.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            String s1 = pattern.substring(i, i + 1);
            String s2 = strings[i];
            if (map.containsKey(s1)) {
                if (!map.get(s1).equals(s2)) {
                    return false;
                }
            } else {
                if (map.containsValue(s2)) {
                    return false;
                }
                map.put(s1, s2);
            }
        }
        return true;
    }


    /**
     * 效的字母异位词
     * https://leetcode.cn/problems/valid-anagram/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test41() {
        String s = "rat";
        String t = "car";
        System.out.println(isAnagram(s, t));
    }

    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }

        return map.isEmpty();
    }

    /**
     * 字母异位词分组
     * https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test42() {
        String[] strs = {"rat"};
        System.out.println(groupAnagrams(strs));
    }

    static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, v -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 两数之和
     * https://leetcode.cn/problems/two-sum/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test43() {
//        int[] nums = new int[]{2,7,11,15};
//        int target = 9;
//        int[] nums = new int[]{3, 2, 4};
//        int target = 6;
        int[] nums = new int[]{3, 3};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 快乐数
     * https://leetcode.cn/problems/happy-number/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test44() {
        int n = 19;
        System.out.println(isHappy(n));
    }

    static boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        } while (slow != fast);

        return slow == 1;
    }

    static int bitSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
        return sum;
    }

    /**
     * 存在重复元素 II
     * https://leetcode.cn/problems/contains-duplicate-ii/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test45() {
//        int[] nums = new int[] {1, 2, 3, 1};
//        int k = 3;
//        int[] nums = new int[] {1,0,1,1};
//        int k = 1;
        int[] nums = new int[]{1, 2, 3, 1, 2, 3};
        int k = 2;
        System.out.println(containsNearbyDuplicate(nums, k));
    }

    static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
        //TODO  滑动窗口的实现?
    }


    /**
     * 最长连续序列
     * https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test46() {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
//        int[] nums = new int[] {0,3,7,2,5,8,4,6,0,1};
//        int[] nums = new int[] {1,0,1,2};
        System.out.println(longestConsecutive(nums));
    }

    static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int p = 1, len = 1, cur = 1;
        while (p < nums.length) {
            int val = nums[p] - nums[p - 1];
            if (val == 1) {
                cur++;
            } else if (val > 1) {
                len = Math.max(len, cur);
                cur = 1;
            }
            p++;
        }
        return Math.max(len, cur);
    }

    /**
     * 汇总区间
     * https://leetcode.cn/problems/summary-ranges/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test47() {
//        int[] nums = new int[] {0,1,2,4,5,7};
//        int[] nums = new int[] {0,2,3,4,6,8,9};
        int[] nums = new int[]{0, 1};
        System.out.println(summaryRanges(nums));
    }

    static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int length = nums.length;
        int p = 0;
        while (p < length) {
            int low = p;
            do {
                p++;
            } while (p < length && nums[p] - nums[p - 1] == 1);
            int high = p - 1;
            String s = String.valueOf(nums[low]);
            if (low < high) {
                s += "->" + nums[high];
            }
            list.add(s);
        }
        return list;
    }

    /**
     * 合并区间
     * https://leetcode.cn/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test48() {
//        int[][] intervals = {{1,3},{15,18},{2,6},{8,10}};
//        int[][] intervals = {{1,4},{4,5}};
        int[][] intervals = {{4, 7}, {1, 4}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    static int[][] merge(int[][] intervals) {
        //排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        //结果
        List<List<Integer>> list = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1], p = 1, len = intervals.length;
        while (p < len) {
            int[] a = intervals[p];
            if (a[0] > end) {
                list.add(Stream.of(start, end).collect(Collectors.toList()));
                start = a[0];
                end = a[1];
            } else {
                end = Math.max(a[1], end);
            }
            p++;
        }
        list.add(Stream.of(start, end).collect(Collectors.toList()));
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = new int[]{list.get(i).get(0), list.get(i).get(1)};
        }
        return ans;
    }

    /**
     * 插入区间
     * https://leetcode.cn/problems/insert-interval/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test49() {
//        int[][] intervals = {{1,3},{6,9}};
//        int[] newInterval  = {2,5};
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }

    static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<List<Integer>> list = new ArrayList<>();
        boolean merged = false; //0 - 未合并 1 - 半合并  2 - 已合并
        for (int[] a : intervals) {
            if (merged) {
                list.add(Stream.of(a[0], a[1]).collect(Collectors.toList()));
                continue;
            }
            //在a的右侧
            if (a[1] < newInterval[0]) {
                list.add(Stream.of(a[0], a[1]).collect(Collectors.toList()));
            } else if (newInterval[1] < a[0]) { //在a的左侧
                merged = true;
                list.add(Stream.of(newInterval[0], newInterval[1]).collect(Collectors.toList()));
                list.add(Stream.of(a[0], a[1]).collect(Collectors.toList()));
            } else if (a[0] <= newInterval[0] && a[1] >= newInterval[1]) {
                merged = true;
                list.add(Stream.of(a[0], a[1]).collect(Collectors.toList()));
            } else {
                newInterval[0] = Math.min(a[0], newInterval[0]);
                newInterval[1] = Math.max(a[1], newInterval[1]);
            }
        }
        if (!merged) {
            list.add(Stream.of(newInterval[0], newInterval[1]).collect(Collectors.toList()));
        }
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = new int[]{list.get(i).get(0), list.get(i).get(1)};
        }
        return ans;
    }

    /**
     * 用最少数量的箭引爆气球
     * https://leetcode.cn/problems/insert-interval/submissions/669999712/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test50() {
//        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
//        int[][] points = {{1,2},{3,4},{5,6},{7,8}};
        int[][] points = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println(findMinArrowShots(points));
    }

    static int findMinArrowShots(int[][] points) {
        //排序
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        //结果
        int ans = 0, start = points[0][0], end = points[0][1], p = 1, len = points.length;
        while (p < len) {
            int[] a = points[p];
            if (a[0] <= end && start <= a[0]) {
                //相交
                end = Math.min(end, a[1]);
            } else {
                //不相交
                end = a[1];
                ans++;
            }
            start = a[0];
            p++;
        }
        return ++ans;
    }

    /**
     * 有效的括号
     * https://leetcode.cn/problems/valid-parentheses/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test51() {
//        String s = "()";
//        String s = "()[]{}";
//        String s = "(]";
        String s = "([])";
//        String s = "([)]";
        System.out.println(isValid(s));
    }

    static boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> list = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                list.add(c);
                continue;
            }
            if (map.containsKey(c)) {
                if (list.isEmpty()) {
                    return false;
                }
                if (map.get(c) != list.pop()) {
                    return false;
                }
            }
        }
        return list.isEmpty();
    }

    /**
     * 简化路径
     * https://leetcode.cn/problems/simplify-path/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test52() {
//        String path = "/home/";
//        String path = "/home//foo/";
//        String path = "/home/user/Documents/../Pictures";
//        String path = "/../";
        String path = "/.../a/../b/c/../d/./";
        System.out.println(simplifyPath(path));
    }

    static String simplifyPath(String path) {
        if (path == null || path.isEmpty() || path.length() == 1) {
            return path;
        }
        Stack<String> stack = new Stack<>();

        String[] strings = path.split("/");
        for (String str : strings) {
            if (str.isEmpty()) {
                continue;
            }
            if (str.contains(".")) {
                if (str.equals("..")) {
                    if (stack.isEmpty()) {
                        continue;
                    }
                    stack.pop();
                } else if (!str.equals(".")) {
                    stack.add(str);
                }
            } else {
                stack.add(str);
            }
        }

        return stack.isEmpty() ? "/" : "/" + String.join("/", stack);
    }

    /**
     * 最小栈
     * https://leetcode.cn/problems/min-stack/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test53() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   // 返回 -3.
        minStack.pop();
        minStack.top();      // 返回 0.
        minStack.getMin();   // 返回 -2.
    }

    class MinStack {

        public MinStack() {
            minStack.push(Integer.MAX_VALUE);
        }

        Deque<Integer> xStack = new LinkedList<>();
        Deque<Integer> minStack = new LinkedList<>();

        public void push(int val) {
            xStack.push(val);
            minStack.push(Math.min(minStack.peek(), val));
        }

        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        public int top() {
            return xStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * 逆波兰表达式求值
     * https://leetcode.cn/problems/min-stack/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test54() {
//        String[] tokens = {"2","1","+","3","*"};
//        String[] tokens = {"4","13","5","/","+"};
//        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        String[] tokens = {"1", "4", "+", "5", "+", "2", "+", "3", "-", "6", "+", "8", "+"};
        System.out.println(evalRPN(tokens));
    }

    static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        Map<String, BiFunction<Integer, Integer, Integer>> map = new HashMap<>();
        map.put("+", Integer::sum);
        map.put("-", (a, b) -> a - b);
        map.put("*", (a, b) -> a * b);
        map.put("/", (a, b) -> a / b);
        BiFunction<Integer, Integer, Integer> function = null;
        for (String token : tokens) {
            if ((function = map.get(token)) != null) {
                Integer v2 = stack.pop();
                Integer v1 = stack.pop();
                stack.push(function.apply(v1, v2));
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }


    /**
     * 基本计算器
     * https://leetcode.cn/problems/basic-calculator/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test55() {
//        String[] tokens = {"2","1","+","3","*"};
//        String[] tokens = {"4","13","5","/","+"};
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};


        String s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(calculate(s));
    }

    static int calculate(String s) {

        int res = 0;       // 当前结果
        int sign = 1;      // 当前符号
        Deque<Integer> stack = new ArrayDeque<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--; // 回退一位
                res += sign * num;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                int prevSign = stack.pop();
                int prevRes = stack.pop();
                res = prevRes + prevSign * res;
            }
        }
        return res;
    }

    /**
     * 环形链表
     * https://leetcode.cn/problems/linked-list-cycle/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test56() {

    }


    boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode p1 = head, p2 = p1.next;
        while (p1 != null && p2 != null){
            if(p1 == p2){
                return true;
            }
            p1 = p1.next;
            p2 = p2.next;
            if(p2 == null){
                break;
            }
            p2 = p2.next;
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode cur = this;
            while (cur != null){
                sb.append(cur.val);
                if((cur = cur.next) != null){
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }

    static ListNode construct(int[] nums){
        ListNode node = new ListNode(nums[0]);
        ListNode root = node;
        for (int i = 1; i < nums.length; i++) {
            node.next = new ListNode(nums[i]);
            node = node.next;
        }
        return root;
    }

    /**
     * 两数相加
     * https://leetcode.cn/problems/add-two-numbers/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test57() {
//        int[] l1 = {2,4,3};
//        int[] l1 = {9,9,9,9,9,9,9};
        int[] l1 = {0};

//        int[] l2 = {5,6,4};
//        int[] l2 = {9,9,9,9};
        int[] l2 = {0};
        System.out.println(addTwoNumbers(construct(l1), construct(l2)));
    }

    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int j = 0;
        ListNode p1 = l1, p2 = l2;
        ListNode node = null, root = null;
        while (p1 != null && p2 != null){
            int sum = p1.val + p2.val + j;
            int val = sum % 10;
            j = sum / 10 > 0 ? 1 : 0;

            p1 = p1.next;
            p2 = p2.next;

            if(node == null){
                root = node = new ListNode(val);
                continue;
            }
            node.next = new ListNode(val);
            node = node.next;
        }

        while (p1 != null){
            int sum = p1.val + j;
            int val = sum % 10;
            j = sum / 10 > 0 ? 1 : 0;

            p1 = p1.next;

            if(node == null){
                node = new ListNode(val);
                continue;
            }

            node.next = new ListNode(val);
            node = node.next;
        }

        while (p2 != null){
            int sum = p2.val + j;
            int val = sum % 10;
            j = sum / 10 > 0 ? 1 : 0;

            p2 = p2.next;

            if(node == null){
                node = new ListNode(val);
                continue;
            }

            node.next = new ListNode(val);
            node = node.next;
        }
        if(j == 1){
            node.next = new ListNode(j);
        }
        return root;
    }

    /**
     * 合并两个有序链表
     * https://leetcode.cn/problems/merge-two-sorted-lists/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test58() {
        int[] l1 = {1,2,4};
        int[] l2 = {1,3,4};
        System.out.println(mergeTwoLists(construct(l1), construct(l2)));
    }

    static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode root = null, node = null, node1 = list1, node2 = list2;
        if(node1 == null && list2 == null){
            return null;
        }else if(node1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }

        while (node1 != null && node2 != null){
            int val1 = node1.val;
            int val2 = node2.val;
            if(val1 <= val2){
                if(node == null){
                    root = node = new ListNode(val1);
                }else {
                    node.next = new ListNode(val1);
                    node = node.next;
                }
                node1 = node1.next;
            }else {
                if(node == null){
                    root = node = new ListNode(val2);
                }else {
                    node.next = new ListNode(val2);
                    node = node.next;
                }
                node2 = node2.next;
            }
        }

        while (node1 != null){
            int val1 = node1.val;
            node.next = new ListNode(val1);
            node = node.next;
            node1 = node1.next;
        }

        while (node2 != null){
            int val1 = node2.val;
            node.next = new ListNode(val1);
            node = node.next;
            node2 = node2.next;
        }

        return root;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            Map<Node, Integer> map = new HashMap<>();
            Node cur = this;
            int cnt = 0;
            while (cur != null){
                map.put(cur, cnt++);
                cur = cur.next;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            cur = this;
            while (cur != null){
                sb.append("[").append(cur.val).append(",")
                        .append(cur.random == null ? null : map.get(cur.random))
                        .append("]");
                if((cur = cur.next) != null){
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    static Node constructNode(Integer[][] nums){

        List<Node> list = new ArrayList<>();
        Node node = new Node(nums[0][0]);
        list.add(node);
        Node root = node;
        for (int i = 1; i < nums.length; i++) {
            node.next = new Node(nums[i][0]);
            list.add(node.next);
            node = node.next;
        }
        node = root;
        int c = 0;
        while (node != null){
            Integer random = nums[c][1];
            if(random != null && random < list.size()){
                node.random = list.get(random);
            }
            c++;
            node = node.next;
        }


        return root;
    }

    /**
     * 随机链表的复制
     * https://leetcode.cn/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test59() {
        Integer[][] l1 = {{7,null},{13,0},{11,4},{10,2},{1,0}};
        Node node = constructNode(l1);
        System.out.println(copyRandomList(node));
    }


    static Map<Node, Node> cachedNode = new HashMap<Node, Node>();

    static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        //hash表方式
//        if (!cachedNode.containsKey(head)) {
//            Node headNew = new Node(head.val);
//            cachedNode.put(head, headNew);
//            headNew.next = copyRandomList(head.next);
//            headNew.random = copyRandomList(head.random);
//        }
//        return cachedNode.get(head);
        //不使用额外空间方式
        //1.将新增节点插入到原始链表中
        Node cur = head, curCopy = null, next = null;
        while (cur != null){
            next = cur.next;
            curCopy = new Node(cur.val);
            curCopy.next = next;
            cur.next = curCopy;
            cur = next;
        }
        //2.处理random
        cur = head;
        Node random = null;
        while (cur != null){
            random = cur.random;
            curCopy = cur.next;
            next = cur.next.next;
            curCopy.random = random == null ? null : random.next;
            cur = next;
        }

        //3.分离原链表
        Node ans = head.next;
        cur = head;
        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next == null ? null : next.next;
            cur = next;
        }

        return ans;
    }

    /**
     * 反转链表 II
     * https://leetcode.cn/problems/reverse-linked-list-ii/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test60() {
        int[] l1 = {1,2,3,4,5};
        int left = 2;
        int right = 4;
//        int[] l1 = {5};
//        int left = 1;
//        int right = 1;
        ListNode node = construct(l1);
        System.out.println(reverseBetween(node, left, right));
    }

    ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode vHead = new ListNode(-1);
        vHead.next = head;

        ListNode prev = vHead;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode cur = prev.next;

        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return vHead.next;
    }

    /**
     * K 个一组翻转链表
     * https://leetcode.cn/problems/reverse-nodes-in-k-group/description/?envType=study-plan-v2&envId=top-interview-150
     **/
    @Test
    public void test61() {
        int[] arr = {1,2,3,4,5};
        int k = 3;
        System.out.println(reverseKGroup(construct(arr), k));
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1){
            return head;
        }
        ListNode vHead = new ListNode();
        vHead.next = head;

        ListNode start = vHead, end = vHead;
        while (true){
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if(end == null){
                break;
            }
            ListNode startNext = start.next, endNext = end.next;

            end.next = null;
            start.next = reverse(start.next);
            startNext.next = endNext;

            start = end = startNext;
        }

        return vHead.next;
    }

    /**
     * 翻转链表
     * @param head
     * @return
     */
    static ListNode reverse(ListNode head){
        ListNode cur = head, prev = null;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 删除链表的倒数第 N 个结点
     * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-interview-150
     **/
    @Test
    public void test62() {
//        int[] arr = {1,2,3,4,5};
//        int k = 2;
        int[] arr = {1,2};
        int k = 1;
        System.out.println(removeNthFromEnd(construct(arr), k));
    }

    ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while(cur != null){
            list.add(cur);
            cur = cur.next;
        }

        int delIndex = list.size() - n;
        if(delIndex == 0){
            return head.next;
        }else {
            list.get(delIndex - 1).next = delIndex < list.size() - 1 ? list.get(delIndex + 1) : null;
        }
        return head;
    }

    /**
     * 删除排序链表中的重复元素 II
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/?envType=study-plan-v2&envId=top-interview-150
     **/
    @Test
    public void test63() {
//        int[] arr = {1,2,3,3,4,4,5};
//        int[] arr = {1,1,1,2,3};
        int[] arr = {1,1};
        System.out.println(deleteDuplicates(construct(arr)));
    }

    static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p1 = head, p2 = head.next, prev = dummy;
        while (p2 != null){
            if(p1.val != p2.val){
                if(p1.next == p2){
                    //相邻
                    prev = prev.next;
                    p1 = p1.next;
                }else {
                    prev.next = p1 = p2;
                }
            }else if(p2.next == null){
                prev.next = null;
            }
            p2 = p2.next;
        }
        return dummy.next;
    }

    /**
     * 旋转链表
     * https://leetcode.cn/problems/rotate-list/?envType=study-plan-v2&envId=top-interview-150
     **/
    @Test
    public void test64() {
//        int[] arr = {1,2,3,4,5};
//        int k  = 2;
        int[] arr = {0,1,2};
        int k  = 4;
        System.out.println(rotateRight(construct(arr), k));
    }

    static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        int count = 0;
        //TODO 是否有环?
        ListNode cur = head;
        while (true){
            count++;
            //将最后一个节点链接到头结点形成环
            if(cur.next == null){
                cur.next = head;
                break;
            }
            cur = cur.next;
        }
        int move = count - (k % count);
        for (int i = 0; i < move; i++) {
            cur = cur.next;
        }
        ListNode newHead = cur.next;
        cur.next = null;
        return newHead;
    }

    /**
     * 分隔链表
     * https://leetcode.cn/problems/partition-list/description/?envType=study-plan-v2&envId=top-interview-150
     **/
    @Test
    public void test65() {
//        int[] arr = {1,4,3,2,5,2};
//        int k  = 3;
//        int[] arr = {2,1};
//        int k  = 2;
//        int[] arr = {1,2,3};
//        int k  = 4;
        int[] arr = {2,0,4,1,3,1,4,0,3};
        int k  = 4;
        System.out.println(partition(construct(arr), k));
    }

    static ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(), p1 = head, p2 = head.next;
        dummy.next = head;
        if(head.val >= x){
            while (p2 != null){
                if(p2.val < x){
                    ListNode next = p2.next;
                    p1.next = null;

                    dummy.next = p2;
                    p2.next = head;
                    p1.next = next;
                    p2 = next;
                    break;
                }
                p1 = p1.next;
                p2 = p2.next;
            }
        }else {
            p1 = p1.next;
            p2 = p2.next;
        }

        ListNode end =  dummy.next;
        while (p2 != null){
            if(p2.val < x && p1.val >= x){
                ListNode next = p2.next, next1 = end.next;
                end.next = p2;
                p2.next = next1;
                end = end.next;
                p1.next = next;
                p2 = next;
                continue;
            }
            if(end.next.val < x){
                end = end.next;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return dummy.next;
    }

    /**
     * LRU 缓存
     * https://leetcode.cn/problems/lru-cache/description/?envType=study-plan-v2&envId=top-interview-150
     **/
    @Test
    public void test66() {

    }

    class LRUCache {


        private final LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
            map = new LinkedHashMap<Integer, Integer>(capacity, 1, true) {
                @Override
                protected boolean removeEldestEntry(final Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            return map.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            map.put(key, value);
        }
    }

    /**
     * 回文链表
     * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-interview-150
     **/
    @Test
    public void test_234() {
        int[] arr = {1,2,2,1};
        System.out.println(isPalindrome(construct(arr)));
    }

    boolean isPalindrome(ListNode head) {
//        Deque<ListNode> stack = new LinkedList<>();
//        ListNode cur = head;
        //简单做法
//        while (cur != null){
//            stack.push(cur);
//            cur = cur.next;
//        }

        //快慢指针
//        Deque<ListNode> stack = new LinkedList<>();
//        ListNode cur = head;
//        ListNode p1 = head, p2 = head;
//        while (p2 != null){
//            if((p2 = p2.next) == null || (p2 = p2.next) == null){
//                while (p1 != null){
//                    stack.push(p1);
//                    p1 = p1.next;
//                }
//                break;
//            }
//            p1 = p1.next;
//        }
//
//        cur = head;
//        while (cur != null && !stack.isEmpty()){
//            if(cur.val != stack.pop().val){
//                return false;
//            }
//            cur = cur.next;
//        }
//        return true;


        //空间复杂度 O(1) 写法
        ListNode p1 = head, p2 = head;

        while (p2.next != null){
            p2 = p2.next;
            if(p2.next != null){
                p2 = p2.next;
                p1 = p1.next;
            }
        }

        ListNode startNode = p1.next;


        p1.next = null;
        ListNode node = reverseNode(startNode);

        ListNode pp1 = head, pp2 = node;
        while (pp1 != null && pp2 != null && pp1 != pp2){
            if(pp1.val != pp2.val){
                return false;
            }
            pp1 = pp1.next;
            pp2 = pp2.next;
        }

        //还原
        p1.next = reverseNode(node);

        return true;
    }

    ListNode reverseNode(ListNode head){
        ListNode cur = head, prev = null;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 相交链表
     * https://leetcode.cn/problems/intersection-of-two-linked-lists/description/
     **/
    @Test
    public void test_160() {
        int[] arr = {4,1};
        int[] arr1 = {5,6,1,8,4,5};
        ListNode node = construct(arr);
        ListNode node1 = construct(arr1);
        node.next.next = node1.next.next.next;
        System.out.println(getIntersectionNode(node, node1));
    }

    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //hash表
//        Set<ListNode> hash = new HashSet<>();
//        ListNode cur = headA;
//        while (cur != null){
//            if (!hash.add(cur)) {
//                //有环
//                break;
//            }
//            cur = cur.next;
//        }
//        cur = headB;
//        while (cur != null){
//            if(hash.contains(cur)){
//                return cur;
//            }
//            cur = cur.next;
//        }
//        return null;

        //如果可能有环也可能没有环写法 时间: O(N) 空间 O(N)
        Set<ListNode> hash = new HashSet<>();
        ListNode cur = headA;
        while (cur != null){
            if (!hash.add(cur)) {
                //有环
                break;
            }
            cur = cur.next;
        }
        Set<ListNode> hash2 = new HashSet<>();
        cur = headB;
        while (cur != null){
            if(!hash2.add(cur)){
                //有环
                break;
            }
            if(hash.contains(cur)){
                return cur;
            }
            cur = cur.next;
        }

        return null;
        //TODO 时间 O(N)  空间 O(1)
        //1. 找出2个链表的入环节点，查找方式如下
        //1.1 使用hash表 空间: O(N) 时间 O(N)
        //1.2 使用快慢指针，如果有环，则快慢指针一定相遇，相遇时将快指针重新指向head节点，快慢指针同时向后遍历（快慢指针每次均走一步），再次相遇的节点即入环节点

        //2. 如果2个链表的入环节点都不存在，则首先判断2个链表的最后一个节点内存地址是否相同，如果不同直接返回，如果相同则计算出2个链表长度的差值，
        //   ，长链表从头结点跳到 差值 数量的节点作为开始节点，然后短链表的head节点作为开始节点，一次向后遍历，找到第一个相同的节点直接返回即可

        //3. 如果一个有环一个没有环则一定不相交
    }





















}

