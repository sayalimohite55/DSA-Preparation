package com.problems.arraysAndStrings;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/*
* Question:
* Given an integer array nums, return true if there exists a triple of indices (i, j, k)
* such that i < j < k and nums[i] < nums[j] < nums[k].
* If no such indices exists, return false.
* Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
* */
public class IncreasingTripletSequence {

    private static boolean doesIncreasingSeqExistsBruteForce(int[] arr) {
        /*
         * Intent:
         * Iterate through each triplet to check if it meets given condition
         */
        int n= arr.length;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n; j++) {
                if(arr[i] < arr[j]) {
                    for(int k = j+1; k<n; k++) {
                        if(arr[j] < arr[k])
                            return true;
                    }
                }
            }
        }
        return false;
        /*
        * Time Complexity = o(n^3)
        * Space Complexity = o(1)
        * */
    }

    private static boolean doesIncreasingSeqExists(int[] arr) {
        /*
        * Intent : Use Linear Scan
        * we only need to identify 3 numbers having increasing sequence
        * So we will maintain two variables firstNum and secondNum intialised with Integer.MAX_VALUE
        *
        * While traversing array, we will get firstNum and secondNum satisfying condition
        * IF third also exists return true;
        * */
        int firstNum= Integer.MAX_VALUE, secondNum = Integer.MAX_VALUE;
        for(int num : arr) {
            if(num <= firstNum)
                firstNum = num;
            else if(num <= secondNum)
                secondNum = num;
            else // third number satisfying sequence condition exists
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^3)
        System.out.println("Output: "+doesIncreasingSeqExistsBruteForce(new int[]{1,2,3,4,5}));
        System.out.println("Output: "+doesIncreasingSeqExistsBruteForce(new int[]{5,4,3,2,1}));
        System.out.println("Output: "+doesIncreasingSeqExistsBruteForce(new int[]{2,1,5,0,4,6}));
        System.out.println("Output: "+doesIncreasingSeqExistsBruteForce(new int[]{1,1,1,1,5}));

        //Optimised Solution using linear scan- o(n)
        System.out.println("\nOutput: "+doesIncreasingSeqExists(new int[]{1,2,3,4,5}));
        System.out.println("Output: "+doesIncreasingSeqExists(new int[]{5,4,3,2,1}));
        System.out.println("Output: "+doesIncreasingSeqExists(new int[]{2,1,5,0,4,6}));
        System.out.println("Output: "+doesIncreasingSeqExists(new int[]{1,1,1,1,5}));
    }
}
