package com.problems.arraysAndStrings.stringPatterns;

/*
* Question:
* Find the number of occurrence of String s2 in string s1 and letters of s1 do not need to be consecutive.
* Complete the 'getSubsequenceCount' function below. The function is expected to return a LONG_INTEGER.
* The function accepts following parameters
* 1. STRING s1
* 2. STRING s2
*
* Example:
* INPUT s1 - AB,  s2 -  ABABABABABABABABABABABABABABABABABABABABABABC
* OUTPUT 0
*
* INPUT s2 - ABABA,  s1 - ABA
* OUTPUT 22
*
* INPUT s1 - ABABABABABABABABABABABABABABABABABABABABABABC  s2 - AB
* OUTPUT 253
* */
public class CountStringOccurrences {
    public static long getSubsequenceCountUsingBacktracking(String s1, String s2) {
        /*
        * Intent:
        * Identify how many times s2 appears in s1
        * Here, letters of s1 do not need to be consecutive
        * that is While finding occurrence of s2 in s1, characters can have gaps
        * but an original sequence in s1 should be preserved
        *
        * Example: s1 = ABABA s2 = ABA   OUTPUT = 4
        * Explanation:
        * Occurrence 1 : s1[0],s1[1],s1[2]
        * Occurrence 2 : s1[0], s1[1], s2[4]
        * Occurrence 3 : s1[2], s1[3], s1[4]
        * Occurrence 4 : s1[0], s1[3], s[4]
        *
        * To find such a solution, we need to use backtracking
        * */
        if(s1.length() < s2.length())
            return 0;
        return backtrack(s1,s2,0,0,new StringBuilder());
        /*
        * Time Complexity = o(m^n)
        * Space Complexity = o(n)
        * where --> m is length of s1 and n is length of s2
        * */
    }

    public static long backtrack(String s1, String s2, int s1Index, int s2Index, StringBuilder prefix) {
        if(prefix.length() == s2.length())
            return 1;

        if(s1Index == s1.length() || s2Index == s2.length())
            return 0;

        long count = 0;
        for(int i = s1Index; i<s1.length(); i++) {
            char ch = s1.charAt(i);
            if(ch == s2.charAt(s2Index)) {
                prefix.append(ch);
                count += backtrack(s1, s2, i+1, s2Index+1, prefix);
                prefix.deleteCharAt(prefix.length()-1);
            }
        }
        return count;
    }

    public static long getSubsequenceCountUsingDP(String s1, String s2) {
        /*
        * Intent:
        * Solution using backtracking has exponential runtime as we calculate counts for certain
        * parts repetitively
        * Hence we can use a memoize dp solution which stores count for repetitive part
        * which can directly referred further.
        * The idea is to use a DP table to store the number of ways to match the first j characters of s2
        * with the first i characters of s1
        * */
        if(s1.length() < s2.length())
            return 0;

        int m = s1.length();
        int n = s2.length();
        long[][] dp = new long[m+1][n+1];

        //Base Case: There is only one way to form empty string s2 from s1
        for(int i =0; i<m ;i++)
            dp[i][0] = 1;

        //Compute rest of dp table
        for(int i = 1; i<=m ; i++) {
            for (int j = 1; j<=n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    //Include the character s1[i-1]
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    //Exclude the character s1[i-1]
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][n];
        /*
        * Time Complexity = o(m * n)
        * Space Complexity = o(m * n)
        * */
    }

    public static void main(String[] args) {
        //Solution using backtracking - o(m^n)
        System.out.println("Count: "+getSubsequenceCountUsingBacktracking(
                "AB","ABABABABABABABABABABABABABABABABABABABABABABC"));
        System.out.println("Count: "+getSubsequenceCountUsingBacktracking(
                "ABABA","ABA"));
        System.out.println("Count: "+getSubsequenceCountUsingBacktracking(
                "ABABABABABABABABABABABABABABABABABABABABABABC","AB"));

        //Solution using dynamic programming - o(m * n)
        System.out.println("\nCount: "+getSubsequenceCountUsingDP(
                "AB","ABABABABABABABABABABABABABABABABABABABABABABC"));
        System.out.println("Count: "+getSubsequenceCountUsingDP(
                "ABABA","ABA"));
        System.out.println("Count: "+getSubsequenceCountUsingDP(
                "ABABABABABABABABABABABABABABABABABABABABABABC","AB"));
    }
}
