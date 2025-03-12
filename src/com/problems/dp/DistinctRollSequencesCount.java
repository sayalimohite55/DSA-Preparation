package com.problems.dp;

/*
* Question:
* You are given an integer n.
* You roll a fair 6-sided dice n times.
* Determine the total number of distinct sequences of rolls possible such that the following conditions are satisfied:
* - The greatest common divisor of any adjacent values in the sequence is equal to 1.
* - There is at least a gap of 2 rolls between equal valued rolls.
*   More formally, if the value of the ith roll is equal to the value of the jth roll, then abs(i - j) > 2.
* Return the total number of distinct sequences possible. Since the answer may be very large,
* return it modulo 10^9 + 7.
* Two sequences are considered distinct if at least one element is different.
*
* eg.
* Input: n = 4
* Output: 184
* Explanation: Some of the possible sequences are (1, 2, 3, 4), (6, 1, 2, 3), (1, 2, 3, 1), etc.
* Some invalid sequences are (1, 2, 1, 3), (1, 2, 3, 6).
* (1, 2, 1, 3) is invalid since the first and third roll have an equal value
*   and abs(1 - 3) = 2 (i and j are 1-indexed).
* (1, 2, 3, 6) is invalid since the greatest common divisor of 3 and 6 = 3.
* There are a total of 184 distinct sequences possible, so we return 184.
* */
public class DistinctRollSequencesCount {
    public static int MOD = 1000000007;
    public static int distinctRollSequenceCountByRecursion(int n) {
        /*
        * Intent:
        * Different values that can be produced by dice roll: {1,2,3,4,5,6}
        * These values have to be generated n times
        *
        * Note:
        * The greatest common divisor of any adjacent values in the sequence is equal to 1
        * if the value of the ith roll is equal to the value of the jth roll, then abs(i - j) > 2
        * */

        int[][][] dp = new int[n+1][7][7];
        return distinctSequences(n, 0, 0, dp);

        /*
        * Time Complexity = o(n * 6 * 6) = o(n)
        * Space Complexity = o(n * 6 * 6) = o(n)
        * */
    }

    public static int distinctSequences(int n, int p, int pp, int[][][] dp) {
        if(n==0)
            return 1;

        if(dp[n][p][pp] != 0)
            return dp[n][p][pp];

        for(int i=1; i<7; i++) {
            if(p != i && pp != i && (p == 0 || gcd(p,i) == 1))
                dp[n][p][pp] = (dp[n][p][pp] + distinctSequences(n-1,i,p,dp)) % MOD;
        }
        return dp[n][p][pp];
    }

    // Helper method to calculate GCD using Euclidean algorithm
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int distinctSequencesNonRecursive(int n) {
        /*
         * Intent:
         * Different values that can be produced by dice roll: {1,2,3,4,5,6}
         * These values have to be generated n times
         *
         * Note:
         * The greatest common divisor of any adjacent values in the sequence is equal to 1
         * if the value of the ith roll is equal to the value of the jth roll, then abs(i - j) > 2
         * */
        if(n==1)
            return 6;
        /*
        * Reason: only six combinations can be formed for six here
        * */
        int[][][] dp = new int[n+1][7][7];

        // Initialize the base case, where dp[1][p][pp] = 1 for all p, pp
        for(int p =1; p<7; p++){
            for(int pp =1; pp<7; pp++) {
                if(p != pp && gcd(p, pp) == 1)
                    dp[2][p][pp] =1; // Sequences of length 2 can start with any valid (p, pp)
            }
        }

        // Fill the dp table for lengths 3 to n
        for(int i=3; i<=n; i++) {
            for(int p=1; p<7; p++) {
                for(int pp=1; pp<7; pp++) {
                    for(int num=1; num<7; num++){
                        if(num != p && num != pp && (gcd(p, num) == 1))
                            dp[i][p][pp] = (dp[i][p][pp] + dp[i-1][num][p]) % MOD;
                    }
                }
            }
        }

        // Calculate the final answer by summing up valid sequences of length n ending with any p and pp
        int result = 0;
        for(int p =1; p<7; p++){
            for(int pp =1; pp<7; pp++) {
                if(p != pp && gcd(p, pp) == 1)
                    result = (result + dp[n][p][pp]) % MOD;
            }
        }
        return result;
        /*
         * Time Complexity = o(n * 6 * 6) = o(n)
         * Space Complexity = o(n * 6 * 6) = o(n)
         * */
    }

    public static void main(String[] args) {
        // Top Down DP Recursive approach - o(n)
        System.out.println("Output: "+distinctRollSequenceCountByRecursion(4));
        System.out.println("Output: "+distinctRollSequenceCountByRecursion(2));
        System.out.println("Output: "+distinctRollSequenceCountByRecursion(9577));
        System.out.println("Output: "+distinctRollSequenceCountByRecursion(1));

        // Bottom Up DP Non-Recursive approach - o(n)
        System.out.println("Output: "+distinctSequencesNonRecursive(4));
        System.out.println("Output: "+distinctSequencesNonRecursive(2));
        System.out.println("Output: "+distinctSequencesNonRecursive(9577));
        System.out.println("Output: "+distinctSequencesNonRecursive(1));
    }
}
