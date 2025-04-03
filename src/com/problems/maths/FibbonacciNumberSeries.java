package com.problems.maths;

import java.util.Arrays;

/*
* Question:
* The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
* such that each number is the sum of the two preceding ones, starting from 0 and 1.
* That is,
*   F(0) = 0, F(1) = 1
*   F(n) = F(n - 1) + F(n - 2), for n > 1.
* Given n, calculate F(n).
* */
public class FibbonacciNumberSeries {
    public static int fibRecursive(int n) {
        if(n == 0 || n == 1)
            return n;
        return fibRecursive(n-1) + fibRecursive(n-2);
        /*
        * Time Complexity = o(2^n)
        * Space Complexity = o(1)
        * */
    }

    public static int fibRecursiveMemoise(int n) {
        int[] memo = new int[n+1];
        memo[n] = fibRecursiveMemoise(n,memo);
        return memo[n];
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(n)
         * */
    }

    public static int fibRecursiveMemoise(int n,int[] memo) {
        if(n==0 || n==1)
            return n;
        if(memo[n] > 0)
            return memo[n];
        memo[n] = fibRecursiveMemoise(n-1,memo) + fibRecursiveMemoise(n-2,memo);
        return memo[n];
    }

    public static int fibOptimised(int n) {
        if(n==0)
            return 0;

        int f1 =0, f2 = 1;
        int f3 = f1 + f2;
        while(n>=2) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
            n--;
        }
        return f3;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        //Recursive Solution - o(2^n)
        System.out.println("Result: "+fibRecursive(0));
        System.out.println("Result: "+fibRecursive(1));
        System.out.println("Result: "+fibRecursive(2));
        System.out.println("Result: "+fibRecursive(3));
        System.out.println("Result: "+fibRecursive(4));
        System.out.println("Result: "+fibRecursive(10));

        //Recursive Memoize Solution - o(n)
        System.out.println("\nResult: "+fibRecursiveMemoise(0));
        System.out.println("Result: "+fibRecursiveMemoise(1));
        System.out.println("Result: "+fibRecursiveMemoise(2));
        System.out.println("Result: "+fibRecursiveMemoise(3));
        System.out.println("Result: "+fibRecursiveMemoise(4));
        System.out.println("Result: "+fibRecursiveMemoise(10));

        //Dynamic Programming/ Optimised Solution - o(n)
        System.out.println("\nResult: "+fibOptimised(0));
        System.out.println("Result: "+fibOptimised(1));
        System.out.println("Result: "+fibOptimised(2));
        System.out.println("Result: "+fibOptimised(3));
        System.out.println("Result: "+fibOptimised(4));
        System.out.println("Result: "+fibOptimised(10));
    }
}
