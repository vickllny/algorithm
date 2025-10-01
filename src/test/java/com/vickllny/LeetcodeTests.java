package com.vickllny;

import org.junit.Test;

import java.util.*;

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




}
