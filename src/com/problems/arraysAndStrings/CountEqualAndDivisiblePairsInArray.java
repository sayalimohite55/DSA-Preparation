package com.problems.arraysAndStrings;

/*
* Question:
* Given a 0-indexed integer array nums of length n and an integer k,
* return the number of pairs (i, j) where 0 <= i < j < n, such that nums[i] == nums[j]
* and (i * j) is divisible by k.
* */
public class CountEqualAndDivisiblePairsInArray {
    public static int countPairs(int[] nums, int k) {
        int n=nums.length, count =0;
        for(int i=0;i<n;i++) {
            for(int j= i+1; j<n; j++) {
                if(nums[i] == nums[j] && (i*j)%k == 0)
                    count++;
            }
        }
        return count;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Count: "+countPairs(new int[]{3,1,2,2,2,1,3}, 2));
        System.out.println("Count: "+countPairs(new int[]{1,2,3,4}, 1));
    }
}
