package com.problems.arraysAndStrings;

import java.util.Arrays;

/*
* Question:
* A non-empty array A consisting of N integers is given. The product of triplet (P, Q, R)
* equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N). For example, array A such that:[-3,1,2,-1,5,6]
* contains the following example triplets:
*   (0, 1, 2), product is −3 * 1 * 2 = −6
*   (1, 2, 4), product is 1 * 2 * 5 = 10
*   (2, 4, 5), product is 2 * 5 * 6 = 60
* Your goal is to find the maximal product of any triplet.
* */
public class MaxProductOfThree {
    private static int maxProductOfThreeBruteForce(int[] arr) {
        int maxProduct = Integer.MIN_VALUE;
        int n= arr.length;

        for(int i=0;i<n ;i++) {
            for(int j = i+1; j<n; j++) {
                for(int k = j+1; k<n; k++) {
                    maxProduct = Math.max(maxProduct,arr[i]*arr[j]*arr[k]);
                }
            }
        }
        return maxProduct;
        /*
        * Time Compelxity = o(n^3)
        * Space Complexity = o(1)
        * */
    }

    private static int maxProductOfThreeOptimised(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length-1;
        return Math.max(arr[0]*arr[1]*arr[n], arr[n]*arr[n-1]*arr[n-2]);
        /*
        * Time Complexity = o(n log n)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^3)
        System.out.println("MaxProduct: "+maxProductOfThreeBruteForce(new int[]{-3,1,2,-1,5,6}));
        System.out.println("MaxProduct: "+maxProductOfThreeBruteForce(new int[]{-3,1,2,0}));
        System.out.println("MaxProduct: "+maxProductOfThreeBruteForce(new int[]{-3,-1,-2,-1,-5,-6}));

        //Optimised Solution - o(n log n)
        System.out.println("MaxProduct: "+maxProductOfThreeOptimised(new int[]{-3,1,2,-1,5,6}));
        System.out.println("MaxProduct: "+maxProductOfThreeOptimised(new int[]{-3,1,2,0}));
        System.out.println("MaxProduct: "+maxProductOfThreeOptimised(new int[]{-3,-1,-2,-1,-5,-6}));
    }
}
