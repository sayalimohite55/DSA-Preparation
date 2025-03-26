package com.problems.arraysAndStrings;

import java.util.Arrays;

/*
* Question:
* An array A consisting of N different integers is given.
* The array contains integers in the range [1..(N + 1)],
* which means that exactly one element is missing.
* Your goal is to find that missing element.
* */
public class MissingNumber {
    private static int missingNumberBruteForce(int[] arr) {
        int n = arr.length+1;
        int num = 1;
        while(num <= n) {
            boolean flag = false;
            for(int val : arr) {
                if(val == num) {
                    num++;
                    flag = true;
                }
            }
            if(!flag)
                return num;
        }
        return num;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int missingNumberOptimised(int[] arr) {
        int n = arr.length+1, sum =0;
        int sumWithoutMissingNum = Arrays.stream(arr).sum();
        for(int i=0;i<=n;i++)
            sum += i;

        return sum - sumWithoutMissingNum;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution- o(n^2)
        System.out.println("Missing number: "+ missingNumberBruteForce(new int[]{2,3,1,5}));

        //Optimised Solution- o(n)
        System.out.println("Missing number: "+ missingNumberOptimised(new int[]{2,3,1,5}));
    }
}
