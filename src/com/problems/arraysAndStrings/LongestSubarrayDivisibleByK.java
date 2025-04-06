package com.problems.arraysAndStrings;

/*
* Question:
* Given an array of integers nums and an integer k, return the length of the longest subarray
* whose sum is divisible by k
* */
public class LongestSubarrayDivisibleByK {
    private static int lengthOfLongestSubarrayDivisbleByK(int[] arr, int k) {
        /*
        * Intent:
        * Using prefix sum to calaculate the sum for subarray and check if its divisible by k
        * If the prefix sum mod k is the same at two different indices i and j,
        * then the subarray between them has a sum divisible by k
        * */
        if(arr.length == 0)
            return 0;

        int n = arr.length;
        int[] prefixSum = new int[n];

        /*Calculate prefix sum for every index*/
        prefixSum[0] = arr[0];
        for(int i=1;i<n;i++)
            prefixSum[i] = prefixSum[i-1] + arr[i];

        int length = 0;
        int i =0, j=1;
        while(j<n) {
            if(prefixSum[i] % k == prefixSum[j] % k) {
                length = Math.max(length, j-i);
                j++;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println("Length: "+lengthOfLongestSubarrayDivisbleByK(new int[]{}, 5));
    }
}
