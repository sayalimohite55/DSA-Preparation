package com.problems.arraysAndStrings;

/*
* Question:
* Given a string s, return the longest palindromic substring in s.
*
* palindrome is a string that reads same from forward and backward.
* */
public class LongestPalindromicSubString {

    private static String bruteForceSolution(String str) {
        /*
        * Intent:
        * Start with full length of str
        * check if palindrome everytime
        * if yes return palindromic string
        * if no, reduce length
        * */
        int n = str.length();
        for(int length=n;length>0; length--) {
            //length is the length of string to be defined
            for(int i = 0; i<n-length; i++) {
                if(isPalindrome(str,i,i+length))
                    return str.substring(i, i+length+1);
            }
        }
        return "";
        /*
        * Time Complexity = o(n^2) * o(n) --> o(n) for checking palindrome every time
        *                 = o(n^3)
        * Space Complexity = o(1)
        * */
    }

    private static boolean isPalindrome(String str, int i, int j) {
        while(i <= j){
            if(str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    private static String solutionUsingDP(String str) {
        /*
        * Intent:
        * A palindrome is a string that reads same forward and backward
        *
        * eg. abbcdcb
        * here
        * - single characters a, b, c, d are all palindrome's
        * - double characters bb also is palindrome
        * - cdc, bcdcb, etc
        * */

        int n = str.length();
        if(n == 0)
            return "";

        boolean[][] dp = new boolean[n][n];
        int[] result = new int[]{0,0};

        //Base Case I: Every single character is a palindrome.
        for(int i=0; i<n; i++)
            dp[i][i] = true;

        //Base Case II: Same adjacent characters is a palindrome string
        for(int i=0; i<n-1 ; i++) {
            if(str.charAt(i) == str.charAt(i+1)) {
                dp[i][i+1] = true;
                result[0] = i;
                result[1] = i+1;
            }
        }

        //For all substrings with length >= 3
        for(int length=2; length<n; length++) {
            for(int i=0; i<n-length; i++) {
                int j = i+length;
                if(str.charAt(i) == str.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    result[0] = i;
                    result[1] =j;
                }
            }
        }

        return str.substring(result[0],result[1]+1);
        /*
        * Time Complexity = o(n) + o(n) + o(n^2)
        *                 = o(n^2)
        * Space Complexity = o(n^2)
        * */
    }
    public static void main(String[] args) {
        //Brute Force Solution - o(n^3)
        System.out.println("\nOutput: "+bruteForceSolution("babad"));
        System.out.println("Output: "+bruteForceSolution(""));
        System.out.println("Output: "+bruteForceSolution("cbbd"));
        System.out.println("Output: "+bruteForceSolution("abbcdcb"));

        //Better Solution using recursion
        System.out.println("\nOutput: "+solutionUsingDP("babad"));
        System.out.println("Output: "+solutionUsingDP(""));
        System.out.println("Output: "+solutionUsingDP("cbbd"));
        System.out.println("Output: "+solutionUsingDP("abbcdcb"));
    }
}
