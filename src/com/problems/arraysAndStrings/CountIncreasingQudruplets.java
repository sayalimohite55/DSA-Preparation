package com.problems.arraysAndStrings;

/*
* Question:
* Given a 0-indexed integer array nums of size n containing all numbers from 1 to n,
* return the number of increasing quadruplets.
* A quadruplet (i, j, k, l) is increasing if:
* 0 <= i < j < k < l < n, and nums[i] < nums[k] < nums[j] < nums[l].
* */
public class CountIncreasingQudruplets {

    private static int bruteForceSolution(int[] arr) {
        int count = 0, n= arr.length;
        for(int i=0;i<n ;i++) {
            for(int j=i+1; j<n; j++) {
                if (arr[i] < arr[j]) {
                    for (int k = j + 1; k < n; k++) {
                        if (arr[i] < arr[k] && arr[k] < arr[j]) {
                            for (int l = k + 1; l < n; l++) {
                                if (arr[j] < arr[l])
                                    count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
        /*
        * Time Complexity = o(n^4)
        * Space Complexity = o(1)
        * */
    }

    private static int optimisedSolution(int[] arr) {
        /*
        * Intent:
        * Let's break problem:
        *   - A triplet (i, j, k) that satisfies the condition i < j < k and nums[i] < nums[k] < nums[j]
        *   - A quadruplet (i, j, k, l) that meets the criteria i < j < k < l and nums[i] < nums[k] < nums[j] < nums[l]
        * */
        int count = 0, n= arr.length;
        boolean[][] isValid = new boolean[n][n];

        for(int i=0;i<n ;i++) {
            for(int j=i+1; j<n; j++) {
                isValid[i][j] = arr[i] < arr[j] ? true : false;
            }
        }



        return count;
        /*
         * Time Complexity = o(n^4)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^4)
        System.out.println("Count: "+bruteForceSolution(new int[]{1,3,2,4,5}));
        System.out.println("Count: "+bruteForceSolution(new int[]{1,2,3,4,5}));

        //Optimised Solution - o( )
        System.out.println("Count: "+optimisedSolution(new int[]{1,3,2,4,5}));
        System.out.println("Count: "+optimisedSolution(new int[]{1,2,3,4,5}));
    }
}
