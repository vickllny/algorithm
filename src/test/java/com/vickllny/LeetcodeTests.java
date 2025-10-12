package com.vickllny;

import org.junit.Test;

import java.util.*;
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

    static void swap(int[][] arr ,int i, int j){
        if(i == j){
            return;
        }
        arr[i][j] = arr[i][j] ^ arr[j][i];
        arr[j][i] = arr[i][j] ^ arr[j][i];
        arr[i][j] = arr[i][j] ^ arr[j][i];
    }

    static void swap(int[][] arr ,int x1, int y1, int x2, int y2){
        if(x1 == x2 && y1 == y2){
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
            if(valueToIndex.containsKey(val)){
                return false;
            }
            values.add(val);
            valueToIndex.put(val, values.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if(valueToIndex.containsKey(val)){
                int indexToRemove = valueToIndex.get(val);
                if(indexToRemove == values.size() - 1 || values.size() == 1){
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
    public void test12(){
//        int[] nums = {1,2,3,4};
        int[] nums = {-1,1,0,-3,3};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    static int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 1){
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
    public void test13(){
//        int[] nums = {1,2,3,4};
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    static int canCompleteCircuit(int[] gas, int[] cost) {
        int size = gas.length;
        for (int i = 0; i < size; ) {
            int j = i, left = 0;
            while (j < size){
                int k = (i + j) % size;
                left += gas[k] - cost[k];
                if(left < 0 ){
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
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        if(ratings.length == 1){
            return 1;
        }else if(ratings.length == 2){
            return ratings[0] == ratings[1] ? 2 : 3;
        }

        // 1.给每个孩子默认分配ratings[i] + 1 个苹果
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        //左侧
        for (int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;
        }
        //右侧
        for (int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1;
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
    public void test15(){
//        int[] ratings = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] ratings = {4,2,0,3,2,5};
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
    public void test16(){
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
            char c =  charArray[i];
            final Integer val = symbolValues.get(c);
            if(val == null){
                continue;
            }
            if(i < charArray.length - 1){
                final Integer val2 = symbolValues.get(charArray[i + 1]);
                if(val < val2){
                    ans -= val;
                }else {
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
    public void test17(){
        int val = 500;
        System.out.println(intToRoman(val));
    }

    static String intToRoman(int num) {
        int tempVal = num;
        StringBuilder s = new StringBuilder();
        //M
        int c = tempVal / 1000;
        tempVal = tempVal % 1000;
        for(int i = 0; i < c; i++){
            s.append("M");
        }
        //D
        if(tempVal >= 900){
            s.append("CM");
            tempVal = tempVal % 900;
        }else if(tempVal >= 400 && tempVal < 500){
            s.append("CD");
            tempVal = tempVal % 400;
        }else {
            c = tempVal / 500;
            tempVal = tempVal % 500;
            for(int i = 0; i < c; i++){
                s.append("D");
            }
        }
        //C
        c = tempVal / 100;
        tempVal = tempVal % 100;
        for(int i = 0; i < c; i++){
            s.append("C");
        }
        //L
        if(tempVal >= 90){
            s.append("XC");
            tempVal = tempVal % 90;
        }else if(tempVal >= 40 && tempVal < 50){
            s.append("XL");
            tempVal = tempVal % 40;
        }else {
            c = tempVal / 50;
            tempVal = tempVal % 50;
            for(int i = 0; i < c; i++){
                s.append("L");
            }
        }
        //X
        c = tempVal / 10;
        tempVal = tempVal % 10;
        for(int i = 0; i < c; i++){
            s.append("X");
        }
        //V
        if(tempVal == 9){
            s.append("IX");
            tempVal = tempVal % 9;
        }else if(tempVal == 4){
            s.append("IV");
            tempVal = tempVal % 4;
        }else {
            c = tempVal / 5;
            tempVal = tempVal % 5;
            for(int i = 0; i < c; i++){
                s.append("V");
            }
        }
        //I
        for(int i = 0; i < tempVal; i++){
            s.append("I");
        }
        return s.toString();
    }

    /**
     * 最后一个单词的长度
     * https://leetcode.cn/problems/length-of-last-word/description/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test18(){
        String val = "Hello World";
        System.out.println(lengthOfLastWord(val));
    }

    static int lengthOfLastWord(String s) {
        if(!s.contains(" ")){
            return s.length();
        }
        final String[] s1 = s.split(" ");
        for (int i = s1.length - 1; i >= 0; i--) {
            if(s1[i].equals(" ")){
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
    public void test19(){
        String[] val = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(val));
    }

    static String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        final char[] charArray = strs[0].toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            for (int j = 1; j < strs.length; j++) {
                if(strs[j].length() -1 < i || strs[j].charAt(i) != c){
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
    public void test20(){
        String s = "  hello world  ";
        System.out.println(reverseWords(s));
    }

    static String reverseWords(String s) {
        if(s == null || s.isEmpty()){
            return s;
        }
        if(s.trim().isEmpty()){
            return s;
        }
        final String[] s1 = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = s1.length - 1; i >= 0; i--) {
            if(s1[i].isEmpty() || s1[i].equals(" ")){
                continue;
            }
            sb.append(s1[i]);
            if(i != 0){
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
    public void test21(){
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convert(s, numRows));
    }

    static String convert(String s, int numRows) {
        if(numRows < 2){
            return s;
        }
        boolean down = false; //向下
        String[] s1 = new String[numRows];
        final char[] charArray = s.toCharArray();
        for (int i = 0, row = 0; i < s.length(); i++) {
            s1[row] = s1[row] == null ? String.valueOf(charArray[i]): s1[row] + charArray[i];
            if(row == 0 || row == numRows - 1){
                down = !down;
            }
            row += down ? 1 : - 1;
        }

        StringBuilder sb = new StringBuilder();
        for (final String string : s1) {
            if(string != null){
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
    public void test22(){
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
        list.add(fillSpace(cur, maxWidth, true ));
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
        if(words.size() == 1 || last){
            for (int i = curWidth; i < maxWidth; i++) {
                words.set(lastIndex, words.get(lastIndex) + " ");
            }
        }else {
            for (int i = 0; curWidth++ < maxWidth ; i = (i + 1) % (lastIndex)) {
                words.set(i, words.get(i) + " ");
            }
        }
        return String.join("", words);
    }

    static List<String> fullJustify1(String[] words, int maxWidth) {
        List<String> cur = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0, curWidth = 0; i < words.length; i++) {
            if(words[i].length() + curWidth + cur.size() <= maxWidth){
                cur.add(words[i]);
                curWidth += words[i].length();
            }else {
                ans.add(fillSpace1(cur, maxWidth, false));
                cur = new ArrayList<>();
                cur.add(words[i]);
                curWidth = words[i].length();
            }
        }
        ans.add(fillSpace1(cur, maxWidth, true));
        return ans;
    }


    static String fillSpace1(List<String> words, int maxWidth, boolean last){
        int curWidth = 0;
        final int lastIndex = words.size() - 1;
        for (int i = 0; i < words.size(); i++) {
            if(i != lastIndex){
                words.set(i, words.get(i) + " ");
            }
            curWidth += words.get(i).length();
        }

        if(lastIndex == 0 || last){
            for (int i = curWidth; i < maxWidth; i++) {
                words.set(lastIndex, words.get(lastIndex) + " ");
            }
        }else {
            for (int i = 0; curWidth++ < maxWidth ; i = (i + 1) % lastIndex) {
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
    public void test24(){
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }

    static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (final char c : s.toCharArray()) {
            if(Character.isLetterOrDigit(c)){
                sb.append(c);
            }
        }
        final int length = sb.length();
        int left = 0, right = length - 1;
        while (left < right){
            if(Character.toLowerCase(sb.charAt(left++)) != Character.toLowerCase(sb.charAt(right--))){
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
        int p = 0;
        final int s_len = s.length();
        final int length = t.length();
        for (int i = 0; i < length; i++) {
            if(s.charAt(p) == t.charAt(i)){
                if(p == s_len - 1){
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
    public void test26(){
        int[] numbers = {-1, 0};
        int target = -1;
        System.out.println(Arrays.toString(twoSum2(numbers, target)));
    }

    static int[] twoSum2(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right){
            int val = numbers[left] + numbers[right];
            if(val == target){
                break;
            }else if(val < target){
                left++;
            }else {
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
    public void test27(){
//        int[] numbers = {1,8,6,2,5,4,8,3,7};
        int[] numbers = {1,1};
        System.out.println(maxArea(numbers));
    }

    static int maxArea(int[] height) {
        int maxArea = 0, left = 0, right = height.length - 1;
        while (left < right){
            maxArea = Math.max(Math.min(height[left], height[right]) * (right - left), maxArea);
            if(height[left] <= height[right]){
                left++;
            }else {
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
    public void test28(){
//        int[] numbers = {1,8,6,2,5,4,8,3,7};
        int[] numbers = {1,1};
        System.out.println(threeSum(numbers));
    }

    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];
            while (left < right){
                int tempVal = nums[left] + nums[right];
                if(tempVal == target){
                    final List<Integer> arrayList = Arrays.asList(nums[i], nums[left], nums[right]);
                    list.add(arrayList);
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }else if(tempVal < target){
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
        int ans = 0;
        int count = 0, p1 = 0, p2 = 0;
        while (p1 < nums.length && p2 < nums.length){
            count += nums[p2];
            if(count >= target){
                ans = ans == 0 ? p2 - p1 + 1 : Math.min(p2 - p1 + 1, ans);
                //向右收敛
                while (p1 <= p2 && count - nums[p1] >= target){
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
    public void test30(){
//        String s = "abcabcbb";
//        String s = "bbbb";
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    static int lengthOfLongestSubstring(String s) {
        int ans = 0, length = s.length(), p1 = 0, p2 = p1;
        Set<Character> set = new HashSet<>();
        while (p1 < length){
            while (p2 < length && set.add(s.charAt(p2))){
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
    public void test31(){
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
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
            if(map.containsKey(word)){
                map.put(word, map.get(word) + 1);
            }else {
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
                if(j - i >= words.length * w_len){
                    String word = s.substring(j - words.length * w_len, j - words.length * w_len + w_len);
                    if(window.containsKey(word)){
                        window.put(word, window.get(word) - 1);
                    }else {
                        window.put(word, 0);
                    }
                    if(map.get(word) != null && window.get(word) < map.get(word)){
                        count--;
                    }
                }

                String word = s.substring(j, j + w_len);
                if(window.containsKey(word)){
                    window.put(word, window.get(word) + 1);
                }else {
                    window.put(word, 1);
                }
                if(map.get(word) !=  null && window.get(word) <= map.get(word)){
                    count++;
                }
                if(count == words.length){
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
    public void test32(){
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
        if(s_len < t_len){
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t_len; i++) {
            char c = t.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }else {
                map.put(c, 1);
                window.put(c, 0);
            }
        }

        int p1 = 0, minWindow = 0, count = 0, targetP1 = -1, targetP2 = -1;
        for (int p2 = 0; p2 < s_len; p2++) {
            char c = s.charAt(p2);
            if(!window.containsKey(c)){
                continue;
            }
            window.put(c, window.get(c) + 1);
            if(window.get(c) <= map.get(c)){
                //有效字符
                count++;
            }

            if(count == t_len){
                int tempWindow = p2 - p1 + 1;
                if(minWindow == 0 || tempWindow < minWindow){
                    //第一次
                    minWindow = tempWindow;
                    targetP1 = p1;
                    targetP2 = p2;
                }
                //移除第一个count
                while (p1 <= p2 && count == t_len){
                    char c1 = s.charAt(p1);
                    if(window.containsKey(c1)){
                        if(window.get(c1) <= map.get(c1)){
                            count--;
                        }
                        window.put(c1, window.get(c1) - 1);
                    }
                    tempWindow = p2 - p1 + 1;
                    if(minWindow == 0 || tempWindow < minWindow){
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
    public void test33(){
//        char[][] board = new char[][]{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        char[][] board = new char[][]{{'8','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}};
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
                if(c == '.'){
                    continue;
                }
                Set<Character> set = map.computeIfAbsent(_key(i, j), v -> new HashSet<>());
                if(!set.add(c)){
                    return false;
                }
            }
        }

        return true;
    }

    static String _key(int i, int j){
        if(i <= 2 && j <= 2){
            return "1";
        }else if(i > 2 && i <= 5 && j <= 2){
            return "2";
        }else if(i > 5 && i <= 8 && j <= 2){
            return "3";
        }else if(i <= 2 && j <= 5){
            return "4";
        }else if(i > 2 && i <= 5 && j <= 5){
            return "5";
        }else if(i > 5 && i <= 8 && j <= 5){
            return "6";
        }else if(i <= 2 && j <= 8){
            return "7";
        }else if(i > 2 && i <= 5 && j <= 8){
            return "8";
        }else if(i > 5 && i <= 8 && j <= 8){
            return "9";
        }
        return "";
    }


    /**
     * 螺旋矩阵
     * https://leetcode.cn/problems/valid-sudoku/?envType=study-plan-v2&envId=top-interview-150
     */
    @Test
    public void test34(){
//        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
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
        while (count < m * n){
            //从左往右 - 横向
            while (y < y2){
                list.add(matrix[x][y]);
                count++;
                y++;
            }
            if(count == m * n){
                break;
            }
            y--;
            x++;
            //右下
            while (x < x2){
                list.add(matrix[x][y]);
                count++;
                x++;
            }
            if(count == m * n){
                break;
            }
            x--;
            y--;
            //从右往左
            while (y >= y1){
                list.add(matrix[x][y]);
                count++;
                y--;
            }
            if(count == m * n){
                break;
            }
            x--;
            y++;
            //从下往上
            while (x > x1){
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
    public void test35(){
//        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
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
    public void test36(){
//        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        int[][] matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
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
                if(matrix[i][j] == 0){
                    matrix[0][j] = matrix[i][0] = 0;
                    if(j == 0){
                        colZero = true;
                    }
                    if(i == 0){
                        rowZero = true;
                    }
                }
            }
        }

        for (int i = 1; i < x_len; i++) {
            for (int j = 1; j < y_len; j++) {
                if(matrix[0][j] == 0 || matrix[i][0] == 0){
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
    public void test37(){
//        int[][] board = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        int[][] board = new int[][] {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
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
                for (int x = Math.max(i - 1, 0); x < i + 2 && x >= 0 && x <rows; x++) {
                    for (int y = Math.max(j - 1, 0); y >= 0 && y < j + 2 && y < cols; y++) {
                        if(x == i && y == j){
                            continue;
                        }
                        if(board[x][y] > 0){
                            ln++;
                        }
                    }
                }

                if(board[i][j] == 1){
                    //活细胞
                    if(ln > 3 || ln < 2){
                        board[i][j] = 2;
                    }else {
                        board[i][j] = 3;
                    }
                }else if(ln == 3){
                    board[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(board[i][j] == 3 || board[i][j] == -1){
                    board[i][j] = 1;
                }else if(board[i][j] == 2){
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
    public void test38(){
        String ransomNote = "aa";
        String magazine  = "aab";
        System.out.println(canConstruct(ransomNote, magazine));
    }

    static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0){
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
    public void test39(){
        String s = "badc";
        String t  = "baba";
        System.out.println(isIsomorphic(s, t));
    }

    static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(map.containsKey(c1)){
                if(map.get(c1) != c2){
                    return false;
                }
            }else {
                if(map.containsValue(c2)){
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
    public void test40(){
//        String pattern = "abba";
//        String s  = "dog cat cat dog";
//        String pattern = "abba";
//        String s  = "dog cat cat fish";
        String pattern = "aaaa";
        String s  = "dog cat cat dog";
        System.out.println(wordPattern(pattern, s));
    }

    static boolean wordPattern(String pattern, String s) {
        Map<String, String> map = new HashMap<>();
        String[] strings = s.split(" ");
        if(pattern.length() != strings.length){
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            String s1 = pattern.substring(i, i + 1);
            String s2 = strings[i];
            if(map.containsKey(s1)){
                if(!map.get(s1).equals(s2)){
                    return false;
                }
            }else {
                if(map.containsValue(s2)){
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
    public void test41(){
        String s = "rat";
        String t  = "car";
        System.out.println(isAnagram(s, t));
    }

    static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char c = t.charAt(i);
            if(!map.containsKey(c)){
                return false;
            }
            map.put(c, map.get(c) - 1);
            if(map.get(c) == 0){
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
    public void test42(){
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
    public void test43(){
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
    public void test44(){
        int n = 19;
        System.out.println(isHappy(n));
    }

    static boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        }while (slow != fast);

        return slow == 1;
    }

    static int bitSquareSum(int n) {
        int sum = 0;
        while(n > 0)
        {
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
    public void test45(){
//        int[] nums = new int[] {1, 2, 3, 1};
//        int k = 3;
//        int[] nums = new int[] {1,0,1,1};
//        int k = 1;
        int[] nums = new int[] {1,2,3,1,2,3};
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
    public void test46(){
        int[] nums = new int[] {100,4,200,1,3,2};
//        int[] nums = new int[] {0,3,7,2,5,8,4,6,0,1};
//        int[] nums = new int[] {1,0,1,2};
        System.out.println(longestConsecutive(nums));
    }

    static int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        int p = 1, len = 1, cur = 1;
        while (p < nums.length){
            int val = nums[p] - nums[p - 1];
            if(val == 1){
                cur++;
            }else if(val > 1){
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
    public void test47(){
//        int[] nums = new int[] {0,1,2,4,5,7};
//        int[] nums = new int[] {0,2,3,4,6,8,9};
        int[] nums = new int[] {0, 1};
        System.out.println(summaryRanges(nums));
    }

    static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int length = nums.length;
        int p = 0;
        while (p < length){
            int low = p;
            do {
                p++;
            } while (p < length && nums[p] - nums[p - 1] == 1);
            int high = p - 1;
            String s = String.valueOf(nums[low]);
            if(low < high){
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
    public void test48(){
//        int[][] intervals = {{1,3},{15,18},{2,6},{8,10}};
//        int[][] intervals = {{1,4},{4,5}};
        int[][] intervals = {{4,7},{1,4}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    static int[][] merge(int[][] intervals) {
        //排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        //结果
        List<List<Integer>> list = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1], p = 1, len = intervals.length;
        while (p < len){
            int[] a = intervals[p];
            if(a[0] > end){
                list.add(Stream.of(start, end).collect(Collectors.toList()));
                start = a[0];
                end = a[1];
            }else {
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










}

