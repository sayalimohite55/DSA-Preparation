package com.problems.arraysAndStrings;

/*
* Question:
* You are given an integer array nums consisting of n elements, and an integer k.
* Find a contiguous subarray whose length is equal to k that has the maximum average value
* and return this value. Any answer with a calculation error less than 10-5 will be accepted.
*
* Examples:
*
* Input: nums = [1,12,-5,-6,50,3], k = 4        Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
*
* Input: nums = [5], k = 1                      Output: 5.00000
*
* Constraints:
* n == nums.length
* 1 <= k <= n <= 105
* -104 <= nums[i] <= 104
* */
public class MaximumAverageSubarray_I {
    public static double findMaxAverage(int[] nums, int k) {
        /*
        * Intent:
        * Use sliding window of k to calculate avg and then maxAvg out of it.
        * Note: initializing maxAvg = (double)Integer.MIN_VALUE
        * as Double.MIN_VALUE is not really a min value. its a double of negative infinity
        * Refer: https://stackoverflow.com/questions/3884793/why-is-double-min-value-in-not-negative
        * */
        int i = 0, j = 0, n = nums.length;
        double maxAvg = (double)Integer.MIN_VALUE;
        double avg;
        int sum = 0;

        while(j < n) {
            if(i == 0) {
                sum = nums[i];
                for(j = i+1; j < i+k && j < n ; j++) {
                    sum += nums[j];
                }
            } else {
                sum += nums[j];
                j++;
            }
            avg = (double)sum / k;
            maxAvg = Math.max(avg, maxAvg);
            sum -= nums[i];
            i++;
        }
        return maxAvg;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }
    public static void main(String[] args) {
        System.out.println("Result: "+findMaxAverage(new int[]{1,12,-5,-6,50,3},4));//output -> 12.75
        System.out.println("Result: "+findMaxAverage(new int[]{5},1));//output -> 5.0
        System.out.println("Result: "+findMaxAverage(new int[]{-1},1));//output -> -1.0
        System.out.println("Result: "+findMaxAverage(new int[]{-1,-2, -4},1));//output -> -1.0
    }
}
