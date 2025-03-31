package com.problems.arraysAndStrings;

/*
* Question:
* A non-empty array A consisting of N integers is given.
* The consecutive elements of array A represent consecutive cars on a road.
* Array A contains only 0s and/or 1s:
*   0 represents a car traveling east,
*   1 represents a car traveling west.
* The goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P < Q < N,
* is passing when P is traveling to the east and Q is traveling to the west.
*
* For example, consider array A such that:
  A[0] = 0
  A[1] = 1
  A[2] = 0
  A[3] = 1
  A[4] = 1
* We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).
*
* Note: The function should return −1 if the number of pairs of passing cars exceeds 1,000,000,000.
*/
public class PassingCars {
    private static int countUsingBruteForceSolution(int[] arr) {
        int maxLimit = 1000000000;
        int n = arr.length;
        int count = 0;
        for(int i =0;i<n && count <= maxLimit;i++) {
            if(arr[i] == 0) {
                for (int j = i + 1; j < n && count <= maxLimit; j++) {
                    if(arr[j] == 1)
                        count++;
                }
            }
        }
        if(count > maxLimit)
            return -1;
        return count;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int countUsingOptimisedSolution(int[] arr) {
        int maxLimit = 1000000000;
        int n = arr.length;
        int[] zeros = new int[n];

        int count = 0;
        for(int i=0; i< n ; i++) {
            if(arr[i] == 0)
                count ++;
            zeros[i] = count;
        }

        count = 0;
        for(int i=0;i<n ; i++) {
            if(arr[i] == 1) {
                count += zeros[i];
                if(count > maxLimit)
                    return -1;
            }
        }

        if(count > maxLimit)
            return -1;
        return count;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        //Brute Force Solution- o(n^2)
        System.out.println("Count: "+countUsingBruteForceSolution(new int[]{0,1,0,1,1}));
        System.out.println("Count: "+countUsingBruteForceSolution(new int[]{1,1,0,0,1}));
        System.out.println("Count: "+countUsingBruteForceSolution(new int[]{1}));
        //optimised Solution - o(n)
        System.out.println("\nCount: "+countUsingOptimisedSolution(new int[]{0,1,0,1,1}));
        System.out.println("Count: "+countUsingOptimisedSolution(new int[]{1,1,0,0,1}));
        System.out.println("Count: "+countUsingOptimisedSolution(new int[]{1}));
    }
}
