package com.problems.maths;

/*
* Question:
* You have n coins and you want to build a staircase with these coins.
* The staircase consists of k rows where the ith row has exactly i coins.
* The last row of the staircase may be incomplete.
* Given the integer n, return the number of complete rows of the staircase you will build.
* */
public class ArrangeCoins {
    private static int arrangeCoinsBruteForce(int n) {
        int count = 0;
        int coins = n, i =1;
        while(coins > 0 && i <= coins) {
            coins -= i++;
            count++;
        }
        return count;
        /*
        * Time Complexity = o(sqrt(n))
        * Space Complexity = o(1)
        * */
    }

    private static int arrangeCoinsUsingBinarySearch(int n) {
        /*
        * Intent:
        * Assume that result is k i.e k rows can be complete for given n
        * such that 1 + 2 + 3 + 4 +...+ k = k(k+1)/2
        * Thus we have to identify k such that k(k+1)/2 <= n
        * We can use binary search here.
        * */
        int left = 1, right = n, k,value;
        while(left <= right) {
            k = (left + right)/2;
            value = k * (k + 1) / 2;

            if(value == n)
                return k;
            else if(n < value)
                right = k-1;
            else
                left = k+1;
        }
        return right;
        /*
         * Time Complexity = o(log n)
         * Space Complexity = o(1)
         * */
    }

    private static int arrangeCoinsUsingMaths(int n) {
        /*
         * Intent:
         * Assume that result is k i.e k rows can be complete for given n
         * such that 1 + 2 + 3 + 4 +...+ k = k(k+1)/2
         * Thus we have to identify k such that k(k+1)/2 <= n
         * Let's simplify this:
         * k(k+1)/2 <= n
         * k(k+1) <= 2n
         *
         *
         * k = sqrt(2 * n + 1/4) -1/2
         * */

        return (int)(Math.sqrt(2 * (long)n + 0.25) - 0.50);
        /*
         * Time Complexity = o(1)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(sqrt(n))
        System.out.println("Count: "+arrangeCoinsBruteForce(5));
        System.out.println("Count: "+arrangeCoinsBruteForce(17));

        //Optimised Solution - o(log n)
        System.out.println("\nCount: "+arrangeCoinsUsingBinarySearch(5));
        System.out.println("Count: "+arrangeCoinsUsingBinarySearch(17));

        //Optimised solution with constant run time - o(1)
        System.out.println("\nCount: "+arrangeCoinsUsingMaths(5));
        System.out.println("Count: "+arrangeCoinsUsingMaths(17));
    }
}
