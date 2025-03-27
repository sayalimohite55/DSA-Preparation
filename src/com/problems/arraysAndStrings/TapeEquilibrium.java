package com.problems.arraysAndStrings;

/*
* Question:
* A non-empty array A consisting of N integers is given. Array A represents numbers on a tape.
* Any integer P, such that 0 < P < N, splits this tape into two non-empty parts:
*       A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].
* The difference between the two parts is the value of:
*       |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
* In other words, it is the absolute difference between the sum of the first part and the sum of
* the second part. For example, consider array A such that: [3,1,2,4,3]
* We can split this tape in four places:
*   P = 1, difference = |3 − 10| = 7
*   P = 2, difference = |4 − 9| = 5
*   P = 3, difference = |6 − 7| = 1
*   P = 4, difference = |10 − 3| = 7
* */

public class TapeEquilibrium {
    private static int minDifferenceBruteForce(int[] arr) {
        int minDiff = Integer.MAX_VALUE, n = arr.length;

        for(int pIndex = 1; pIndex < n ; pIndex++) {
            int leftSum = 0, rightSum =0;
            for(int i=0;i<pIndex; i++)
                leftSum += arr[i];

            for(int i=pIndex; i<n; i++)
                rightSum += arr[i];

            minDiff = Math.min(minDiff, Math.abs(leftSum-rightSum));
        }
        return minDiff;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int minDifferenceOptimised(int[] arr) {
        int minDiff = Integer.MAX_VALUE, n = arr.length, totalSum = 0;

        for(int num : arr)
            totalSum += num;

        int leftSum =0;
        for(int i=0; i<n-1; i++) {
            leftSum += arr[i];
            int rightSum = totalSum - leftSum;
            minDiff = Math.min(minDiff, Math.abs(leftSum-rightSum));
        }
        return minDiff;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^2)
        System.out.println("Min Diff: "+minDifferenceBruteForce(new int[]{3,1,2,4,3}));
        System.out.println("Min Diff: "+minDifferenceBruteForce(new int[]{-2,-3,-4,-1}));
        System.out.println("Min Diff: "+minDifferenceBruteForce(new int[]{-1000,1000}));

        //Optimised Solution - o(n)
        System.out.println("\nMin Diff: "+minDifferenceOptimised(new int[]{3,1,2,4,3}));
        System.out.println("Min Diff: "+minDifferenceOptimised(new int[]{-2,-3,-4,-1}));
        System.out.println("Min Diff: "+minDifferenceOptimised(new int[]{-1000,1000}));
    }
}
