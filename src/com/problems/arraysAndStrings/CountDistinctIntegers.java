package com.problems.arraysAndStrings;

import java.util.HashSet;
import java.util.Set;

/*
* Question:
* Write a function that, given an array A consisting of N integers, returns the number of
* distinct values in array A.
* For example, given array A consisting of six elements such that: [2,1,1,2,3,1]
* the function should return 3, because there are 3 distinct values appearing in array A,
* namely 1, 2 and 3. Write an efficient algorithm for the following assumptions:
* N is an integer within the range [0..100,000];
* each element of array A is an integer within the range [−1,000,000..1,000,000].
* */
public class CountDistinctIntegers {
    private static int countDistinctBruteForce(int[] arr) {
        int count = 0, n= arr.length;
        int numOutOfRange = 1000001;
        for(int i=0; i<n; i++) {
            if(arr[i] == numOutOfRange)
                continue;
            for(int j=0; j<n; j++) {
                if(i != j && arr[i]==arr[j]) {
                    arr[j] = numOutOfRange;
                }
            }
            count++;
            arr[i] = numOutOfRange;
        }
        return count;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int countDistinctOptimised(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for(int num : arr)
            set.add(num);

        return set.size();
        /*
        * time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^2)
        System.out.println("\nCount: "+countDistinctBruteForce(new int[]{2,1,1,2,3,1}));
        System.out.println("Count: "+countDistinctBruteForce(new int[]{1}));
        System.out.println("Count: "+countDistinctBruteForce(new int[]{}));

        //Optimised Solution - o(n)
        System.out.println("\nCount: "+countDistinctOptimised(new int[]{2,1,1,2,3,1}));
        System.out.println("Count: "+countDistinctOptimised(new int[]{1}));
        System.out.println("Count: "+countDistinctOptimised(new int[]{}));
    }
}
