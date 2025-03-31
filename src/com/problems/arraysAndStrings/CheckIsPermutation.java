package com.problems.arraysAndStrings;

import java.util.HashSet;
import java.util.Set;

/*
* Question:
* A non-empty array A consisting of N integers is given.
* A permutation is a sequence containing each element from 1 to N once, and only once.
* For example, array A such that:
    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2
* is a permutation, but array A such that:
    A[0] = 4
    A[1] = 1
    A[2] = 3
* is not a permutation, because value 2 is missing.
* The goal is to check whether array A is a permutation.
* */
public class CheckIsPermutation {
    private static int isPermBruteForceSolution(int[] arr) {
        int n = arr.length;
        for(int i=1; i<=n;i++) {
            boolean flag = false;
            for(int j=0;j<n; j++){
                if(i == arr[j]) {
                    flag = true;
                    break;
                }
            }
            if(!flag)
                return 0;
        }
        return 1;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int isPermOptimisedSolution(int[] arr) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>();
        for(int num : arr) {
            set.add(num);
        }

        if(set.size() == n) {
            for (int i = 1; i <= n; i++) {
                if (!set.contains(i))
                    return 0;
            }
            return 1;
        }
        return 0;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^2)
        System.out.println("Output: "+isPermBruteForceSolution(new int[]{4,1,3,2}));
        System.out.println("Output: "+isPermBruteForceSolution(new int[]{1,4,1}));
        System.out.println("Output: "+isPermBruteForceSolution(new int[]{4,1,3,1}));
        System.out.println("Output: "+isPermBruteForceSolution(new int[]{2}));

        //Optimised Solution - o(n)
        System.out.println("\nOutput: "+isPermOptimisedSolution(new int[]{4,1,3,2}));
        System.out.println("Output: "+isPermOptimisedSolution(new int[]{1,4,1}));
        System.out.println("Output: "+isPermOptimisedSolution(new int[]{4,1,3,1}));
        System.out.println("Output: "+isPermOptimisedSolution(new int[]{2}));
    }
}
