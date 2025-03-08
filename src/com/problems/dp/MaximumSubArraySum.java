package com.problems.dp;

import java.util.HashMap;
import java.util.Map;

/*
* Question:
* Given an integer array, find the subarray with the largest sum, and return its sum.
* */
public class MaximumSubArraySum {
    private static int bruteForceSolution(int[] arr) {
        /*
        * Intent:
        * For each i start calculating sum
        * While adding to sum, every time check max
        * */
        int maxSubArraySum = 0;
        for(int i=0;i<arr.length; i++) {
            int sum = 0;
            for(int j= i; j<arr.length; j++) {
                sum += arr[j];
                maxSubArraySum = Math.max(maxSubArraySum, sum);
            }
        }
        return maxSubArraySum;
        /*
         * Time Complexity = o(n^2)
         * Space Complexity = o(1)
         */
    }

    private static int  maxSubArraySum(int[] arr) {
        /*
        * Intent: Using Kadane's Algorithm
        * Initialise to first element
        * While traversing rest of array, at each element
        * if addition is greater than the no to be added, take sum
        * else drop that sum and start with the current number
        * */
        int n = arr.length, sum =arr[0], maxSum =arr[0];
        for(int i =1;i<n; i++) {
            sum = Math.max(arr[i], sum+arr[i]);
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    private static int maxSubArraySumRecursive(int[] arr, int i) {
        if(i == arr.length)
            return 0;
        return Math.max(maxSubArraySumRecursive(arr,i+1), arr[i] + maxSubArraySumRecursive(arr,i+1));
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^2)
        System.out.println("Output: "+bruteForceSolution(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("Output: "+bruteForceSolution(new int[]{1}));
        System.out.println("Output: "+bruteForceSolution(new int[]{5,4,-1,7,8}));

        //optimised Solution - o(n)
        System.out.println("Output: "+maxSubArraySum(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("Output: "+maxSubArraySum(new int[]{1}));
        System.out.println("Output: "+maxSubArraySum(new int[]{5,4,-1,7,8}));
    }

}
