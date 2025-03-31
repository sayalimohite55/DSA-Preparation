package com.problems.maths;
/*
* Question:
* Write a function that, given three integers A, B and K, returns the number of integers
* within the range [A..B] that are divisible by K, i.e.: { i : A ≤ i ≤ B, i mod K = 0 }
* For example, for A = 6, B = 11 and K = 2, your function should return 3,
* because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
* Write an efficient algorithm for the following assumptions:
*   A and B are integers within the range [0..2,000,000,000];
*   K is an integer within the range [1..2,000,000,000]; A ≤ B.
* */
public class CountDivisiblesInRange {
    private static int countUsingBruteForceSolution(int A, int B, int K) {
        int count = 0;
        for(int i = A; i<= B; i++) {
            if(i%K == 0)
                count++;
        }
        return count;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    private static int countUsingOptimisedSolution(int A, int B, int K) {
        if(B==0)
            return 1;
        else if (A==0)
            return ( (B/K) - ((A-1)/K) +1);
        return ( (B/K) - ((A-1)/K) );
        /*
         * Time Complexity = o(1)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution- o(n)
        System.out.println("Count: "+countUsingBruteForceSolution(6,11,2));
        System.out.println("Count: "+countUsingBruteForceSolution(0,0,11));
        System.out.println("Count: "+countUsingBruteForceSolution(0,14,2));
        //System.out.println("Count: "+countUsingBruteForceSolution(0,Integer.MAX_VALUE,11)); //Time limit exceeds

        //optimised Solution - o(1)
        System.out.println("\nCount: "+countUsingOptimisedSolution(6,11,2));
        System.out.println("Count: "+countUsingOptimisedSolution(0,0,11));
        System.out.println("Count: "+countUsingOptimisedSolution(0,14,2));
        //System.out.println("Count: "+countUsingOptimisedSolution(0,Integer.MAX_VALUE,11));
    }
}
