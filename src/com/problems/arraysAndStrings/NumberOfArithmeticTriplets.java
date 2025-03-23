package com.problems.arraysAndStrings;

/*
* Question:
* You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff.
* A triplet (i, j, k) is an arithmetic triplet if the following conditions are met:
*   i < j < k,
*   nums[j] - nums[i] == diff, and nums[k] - nums[j] == diff.
* Return the number of unique arithmetic triplets.
* */
public class NumberOfArithmeticTriplets {

    private static int arithmeticTriplets(int[] arr, int diff) {
        int n = arr.length, count = 0;
        for(int i=0;i<n-2; i++) {
            for(int j= i+1; j<n-1 ;j++) {
                if(arr[j] - arr[i] == diff) {
                    for(int k=j+1;k<n; k++) {
                        if(arr[k] - arr[j] == diff)
                            count++;
                    }
                }
            }
        }
        return count;
        /*
        * Time Complexity = o(n^3)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^3)
        System.out.println("Result: "+ arithmeticTriplets(new int[]{0,1,4,6,7,10},3));
        System.out.println("Result: "+ arithmeticTriplets(new int[]{4,5,6,7,8,9},2));
    }
}
