package com.problems.arraysAndStrings;

import java.util.HashSet;
import java.util.Set;

/*
* Question:
* Write a function that, given an array A of N integers, returns the
* smallest positive integer (greater than 0) that does not occur in A.
*
* For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
* Given A = [1, 2, 3], the function should return 4.
* Given A = [−1, −3], the function should return 1.
*
* Write an efficient algorithm for the following assumptions:
*   N is an integer within the range [1..100,000];
*   each element of array A is an integer within the range [−1,000,000..1,000,000].
* */
public class MissingInteger {
    private static int missingIntegerBruteForceSolution(int[] arr) {
        int n = arr.length;
        int missingNum = 1;

        for(int j = 0; j < n; j++) {
            for (int k : arr) {
                if (missingNum == k) {
                    missingNum++;
                    break;
                }
            }
        }
        return missingNum;
        /*
         * Time Complexity = o(n^2)
         * Space Complexity = o(1)
         * */
    }

    private static int missingIntegerOptimisedSolution(int[] arr) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>();
        for(int num : arr) {
            set.add(num);
        }

        int missingNum = 1;
        while(set.contains(missingNum))
                missingNum ++;
        return missingNum;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }
    public static void main(String[] args) {
        //Brute Force Solution - o(n^2)
        System.out.println("Output: "+missingIntegerBruteForceSolution(new int[]{4,1,3,2}));
        System.out.println("Output: "+missingIntegerBruteForceSolution(new int[]{1,4,1}));

        //Optimised Solution - o(n)
        System.out.println("\nOutput: "+missingIntegerOptimisedSolution(new int[]{4,1,3,2}));
        System.out.println("Output: "+missingIntegerOptimisedSolution(new int[]{1,4,1}));
    }
}
