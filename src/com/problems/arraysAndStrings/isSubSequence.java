package com.problems.arraysAndStrings;

/*
* Question:
* Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
* A subsequence of a string is a new string that is formed from the original string by deleting some
* (can be none) of the characters without disturbing the relative positions of the remaining characters.
* (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
*
* Examples:
*
* Input: s = "abc", t = "ahbgdc"        Output: true
* Input: s = "axc", t = "ahbgdc"        Output: false
*
* Constraints:
* 0 <= s.length <= 100
* 0 <= t.length <= 104
* s and t consist only of lowercase English letters.
*
* Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109,
* and you want to check one by one to see if t has its subsequence.
* In this scenario, how would you change your code?
* */
public class isSubSequence {

    public static boolean isSubsequence(String s, String t) {
        /*
        * Intent:
        * Use two pointers technique
        * */
        int i=0, j=0;
        int n1 = s.length(), n2 = t.length();
        StringBuilder sb = new StringBuilder();
        while(i < n1 && j < n2) {
            char ch = s.charAt(i);

            while(j < n2 && t.charAt(j) != ch)
                j++;
            if(j < n2 && ch == t.charAt(j)) {
                sb.append(ch);
                i++;
                j++;
            }
        }
        return (sb.toString().equals(s));
        /*
        * Time Complexity = o( length(t) )
        * Space Complexity = o( length(t) )
        * */
    }

    public static void main(String[] args) {
        System.out.println("Result: "+ isSubsequence("abc","ahbgdc"));
        System.out.println("Result: "+ isSubsequence("axc","ahbgdc"));
    }
}
