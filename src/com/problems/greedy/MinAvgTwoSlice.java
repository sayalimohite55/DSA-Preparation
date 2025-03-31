package com.problems.greedy;

/*
* Question:
* A non-empty array A consisting of N integers is given.
* A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A
* (notice that the slice contains at least two elements).
* The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q]
* divided by the length of the slice. To be precise, the average equals
* (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
*
* For example, array A such that: [4,2,2,5,1,5,8] contains the following example slices:
*   slice (1, 2), whose average is (2 + 2) / 2 = 2;
*   slice (3, 4), whose average is (5 + 1) / 2 = 3;
*   slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
*   The goal is to find the starting position of a slice whose average is minimal.
* */
public class MinAvgTwoSlice {
    private static int minAvgTwoSliceBruteForce(int[] arr) {
        int n = arr.length;
        double minAvg = Double.MAX_VALUE;

        int minStartIndex = -1;
        for(int i=0; i<n ; i++) {
            int sum = arr[i];
            for(int j=i+1; j<n; j++) {
                sum += arr[j];

                double avg = (double) sum / (j-i+1);
                if(avg < minAvg) {
                    minAvg = avg;
                    minStartIndex = i;
                }
            }
        }
        return minStartIndex;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int minAvgTwoSliceOptimised(int[] arr) {
        /*
        * Intent:
        * Observation: The smallest average slice must be of size 2 or 3. Why?
        * Any longer slice can be broken into smaller 2-element or 3-element slices,
        * and if one of these smaller slices has a lower average, then the larger slice
        * can't be the minimal one.
        * */
        int n = arr.length;
        double minAverage = Double.MAX_VALUE;
        int minStartIndex = -1;

        for(int i=0;i<n-1; i++) {
            double avg2 = (arr[i] + arr[i+1])/2.0;
            if(avg2 < minAverage) {
                minAverage = avg2;
                minStartIndex = i;
            }

            if(i < n-2) {
                double avg3 = (arr[i]+arr[i+1]+arr[i+2])/3.0;
                if(avg3 < minAverage) {
                    minAverage = avg3;
                    minStartIndex = i;
                }
            }
        }
        return minStartIndex;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^2)
        System.out.println("\nResult: "+minAvgTwoSliceBruteForce(new int[]{4,2,2,5,1,5,8}));
        System.out.println("Result: "+minAvgTwoSliceBruteForce(new int[]{4,7,5,1,9,8}));
        System.out.println("Result: "+minAvgTwoSliceBruteForce(new int[]{4}));
        System.out.println("Result: "+minAvgTwoSliceBruteForce(new int[]{}));

        //Optimised Solution - o(n)
        System.out.println("\nResult: "+minAvgTwoSliceOptimised(new int[]{4,2,2,5,1,5,8}));
        System.out.println("Result: "+minAvgTwoSliceOptimised(new int[]{4,7,5,1,9,8}));
        System.out.println("Result: "+minAvgTwoSliceOptimised(new int[]{4}));
        System.out.println("Result: "+minAvgTwoSliceOptimised(new int[]{}));
    }
}
