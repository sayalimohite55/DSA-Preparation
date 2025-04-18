package com.problems.arraysAndStrings;

/*
* Question:
* You are given an integer array nums and an integer k.
* In one operation, you can pick two numbers from the array whose sum equals k
* and remove them from the array. Return the maximum number of operations you can perform on the array.
*
* Examples:
*
* Input: nums = [1,2,3,4], k = 5        Output: 2
* Explanation: Starting with nums = [1,2,3,4]:
*   - Remove numbers 1 and 4, then nums = [2,3]
*   - Remove numbers 2 and 3, then nums = []
*   There are no more pairs that sum up to 5, hence a total of 2 operations.
*
* Input: nums = [3,1,3,4,3], k = 6      Output: 1
* Explanation: Starting with nums = [3,1,3,4,3]:
*   - Remove the first two 3's, then nums = [1,4,3]
*   There are no more pairs that sum up to 6, hence a total of 1 operation.
*
* Constraints:
* 1 <= nums.length <= 105
* 1 <= nums[i] <= 109
* 1 <= k <= 109
* */
import java.util.Arrays;

public class MaxNumberOfK_SumPairs {
    public static int maxOperations(int[] nums, int k) {
        /*
        * Intent:
        * Here, we could have used Hashmap, just the way we use it in twosum scenario,
        * but it may fail in cases of duplicate elements, as hashmap can only have unique keys
        * So,
        * Sort the array first and then use two pointers technique
        * */
        int count =0;

        Arrays.sort(nums);
        int i=0, j = nums.length-1;
        while(i<j) {
            int sum = nums[i] + nums[j];
            if(sum == k) {
                nums[i] = nums[j] = 0;
                count ++;
                i ++;
                j --;
            } else if( sum < k) {
                i ++;
            } else {
                j --;
            }
        }
        return count;
        /*
        * Time Complexity = o(n log n + n) = o(n log n)
        * Space Complexity = o(1) or o(n) --depends on whether in-place sorting is done or extra space is used
        * */
    }

    public static void main(String[] args) {
        System.out.println("Result : "+maxOperations(new int[]{1,2,3,4},5));
        System.out.println("Result : "+maxOperations(new int[]{3,1,3,4,3},6));
        System.out.println("Result : "+maxOperations(new int[]{2,5,4,4,1,3,4,4,1,4,4,1,2,1,2,2,3,2,4,2},3));
    }
}
