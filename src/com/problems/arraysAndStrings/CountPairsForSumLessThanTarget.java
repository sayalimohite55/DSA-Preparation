package com.problems.arraysAndStrings;

import java.util.Arrays;

/*
* Question:
* Given a 0-indexed integer array nums of length n and an integer target,
* return the number of pairs (i, j) where 0 <= i < j < n and nums[i] + nums[j] < target.
* */
public class CountPairsForSumLessThanTarget {
    private static int countPairsBruteForceSolution(int[] arr, int target) {
        int n = arr.length, count = 0;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                if(arr[i]+arr[j] < target)
                    count++;
            }
        }
        return count;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int countPairsUsingSorting(int[] arr, int target) {
        /*
         * Intent:
         * Sort the array and then check pairs using two pointers technique
         * */
        int n = arr.length, count = 0;
        Arrays.sort(arr);
        int i=0, j=n-1;
        while(i < j) {
            if(arr[i]+arr[j] < target) {
                count+= j-i;
                i++;
            } else {
                j--;
            }
        }
        return count;
        /*
         * Time Complexity = o(n log n + n) = o(n log n)
         * Space Complexity = o(log n) or o(n)
         * */
    }

    public static void main(String[] args) {
        //Brute force solution - o(n^2)
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[]{-1,1,2,3,1},2));
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[]{-6,2,5,-2,-7,-1,3},-2));

        //Solution using sorting - o(n log n)
        System.out.println("\nNo of pairs: "+countPairsUsingSorting(new int[]{-1,1,2,3,1},2));
        System.out.println("No of pairs: "+countPairsUsingSorting(new int[]{-6,2,5,-2,-7,-1,3}, -2));
    }
}
