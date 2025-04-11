package com.problems.arraysAndStrings;

/*
* Question:
* Given a string s, return the number of palindromic substrings in it.
* A string is a palindrome when it reads the same backward as forward.
* A substring is a contiguous sequence of characters within the string.
*
* Example 1:
* Input: s = "abc"  Output: 3
* Explanation: Three palindromic strings: "a", "b", "c".
*
* Example 2:
* Input: s = "aaa"  Output: 6
* Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
*
* Constraints:
* 1 <= s.length <= 1000
* s consists of lowercase English letters.
* */
public class CountPalindromicSubstrings {
    public static int palindromicSubstrings(String str) {
        int n = str.length();
        boolean[][] dp = new boolean[n+1][n+1];
        int count = 0;

        //Base case I : Mark single alphabets as true
        for(int i=0; i<n;i++) {
            dp[i][i] = true;
            count++;
        }

        //Base Case II: Mark two alphabet string as palindrome if c1 == c2
        for(int i =0; i<n-1; i++) {
            dp[i][i+1] = str.charAt(i) == str.charAt(i+1);
            count += dp[i][i+1] ? 1 : 0;
        }

        //String with length more than equal to 3
        for(int len = 3; len <= n; ++len) {
            for(int i=0, j= i+len -1; j<n; ++i,++j){
                dp[i][j] = dp[i+1][j-1] && str.charAt(i) == str.charAt(j);
                count += dp[i][j] ? 1 : 0;
            }
        }
        return count;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(n^2)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Result: "+palindromicSubstrings("abc"));
        System.out.println("Result: "+palindromicSubstrings("aaa"));
    }
}
