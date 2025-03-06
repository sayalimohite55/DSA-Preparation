package com.problems.dp;

/*
* Question:
* You are climbing a staircase. It takes n steps to reach the top.
* Each time you can either climb 1 or 2 steps.
* In how many distinct ways can you climb to the top?
*/
public class ClimbingStairs {

    private static long numWaysRecursive(int n) {
        /*
        * Intent:
        * We count number of ways to climb stairs recursively for both 1 and 2 steps
        * n=5
        * */

        //Base case
        if(n == 1 || n == 0)
            return 1;

        return numWaysRecursive(n-1) + numWaysRecursive(n-2);
        /*
        * Time Complexity = o(2^n)
        * Space Complexity = o(1)
        * */
    }

    private static long numWaysUsingDP(int n) {
        /*
         * Intent:
         * We count number of ways to climb stairs recursively for both 1 and 2 steps
         * n=5
         * f(5) = f(4) + f(3)
         * f(3) = f(3) + f(2)
         * f(2) = f(1) + f(0)
         * Hence, we memoize solution
         * */

        long[] arr = new long[n+1];
        arr[0] = 1;
        arr[1] = 1;

        for(int i=2; i<=n; i++)
            arr[i] = arr[i-1] + arr[i-2];
        return arr[n];
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(n)
         * */
    }

    private static long numWaysUsingFibonacciSeries(int n) {
        /*
         * Intent:
         * eg: n=5
         * f(5) = f(4) + f(3)
         * f(3) = f(3) + f(2)
         * f(2) = f(1) + f(0)
         * Above sequence is essentially a fibonacci series
         * */
        long f1,f2,f3;
        f1 = 0;
        f2 = 1;
        f3 = f1 + f2;

        for(int i=1; i<=n; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution using recursion- o(2^n)
        System.out.println("Number of ways to climb stairs for n=5: "+numWaysRecursive(5));
        System.out.println("Number of ways to climb stairs for n=3: "+numWaysRecursive(3));
        System.out.println("Number of ways to climb stairs for n=20: "+numWaysRecursive(20));
        System.out.println("Number of ways to climb stairs for n=30: "+numWaysRecursive(30));

        //Better Solution using DP (using extra space)- o(n)
        System.out.println("\nNumber of ways to climb stairs for n=5: "+numWaysUsingDP(5));
        System.out.println("Number of ways to climb stairs for n=3: "+numWaysUsingDP(3));
        System.out.println("Number of ways to climb stairs for n=20: "+numWaysUsingDP(20));
        System.out.println("Number of ways to climb stairs for n=30: "+numWaysUsingDP(30));

        //Better Solution using Fibonacci Series- o(n)
        System.out.println("\nNumber of ways to climb stairs for n=5: "+numWaysUsingFibonacciSeries(5));
        System.out.println("Number of ways to climb stairs for n=3: "+numWaysUsingFibonacciSeries(3));
        System.out.println("Number of ways to climb stairs for n=20: "+numWaysUsingFibonacciSeries(20));
        System.out.println("Number of ways to climb stairs for n=30: "+numWaysUsingFibonacciSeries(30));
    }
}
