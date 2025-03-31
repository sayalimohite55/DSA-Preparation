package com.problems.maths;

import java.util.Arrays;

/*
* Question:
* An array A consisting of N integers is given. A triplet (P, Q, R) is triangular
* if 0 ≤ P < Q < R < N and: A[P] + A[Q] > A[R], A[Q] + A[R] > A[P], A[R] + A[P] > A[Q].
* For example, consider array A such that: [10,2,5,1,8,20]
  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 20
* Triplet (0, 2, 4) is triangular.
* Write a function that, given an array A consisting of N integers,
* returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.
*
* Write an efficient algorithm for the following assumptions:
* N is an integer within the range [0..100,000];
* each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
* */
public class TriangleExists {
    private static int isTriangleExistsBruteForce(int[] arr) {
        int n = arr.length;
        if (n < 3) return 0;

        for(int i =0;i<n; i++) {
            for(int j=i+1; j<n ; j++) {
                for (int k=j+1; k<n; k++) {
                    if( (arr[i]+arr[j] > arr[k]) && (arr[i]+arr[k] > arr[j]) && (arr[j]+arr[k] > arr[i]))
                        return 1;
                }
            }
        }
        return 0;
        /*
        * Time Complexity = o(n^3)
        * Space Complexity = o(1)
        * */
    }

    private static int isTriangleExistsOptimised(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        if (n < 3) return 0;

        for(int i=0;i<n-2;i++) {
            if((long)arr[i]+(long)arr[i+1] > arr[i+2]) // Check only A+B > C as sorting takes care of rest two conditions
                return 1;
        }
        return 0;
        /*
        * Time Complexity = o(n log n) + o(n) = o(n log n)
        * Space Complexity = o(1) or o(n) --> Based on sorting type.
        * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^3)
        System.out.println("Result: "+isTriangleExistsBruteForce(new int[]{10,2,5,1,8,20}));
        System.out.println("Result: "+isTriangleExistsBruteForce(new int[]{10,50,5,1}));
        //Optimised Solution - o(n log n)
        System.out.println("\nResult: "+isTriangleExistsOptimised(new int[]{10,2,5,1,8,20}));
        System.out.println("Result: "+isTriangleExistsOptimised(new int[]{10,50,5,1}));
    }
}
