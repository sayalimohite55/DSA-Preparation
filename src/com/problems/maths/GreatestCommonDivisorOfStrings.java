package com.problems.maths;

/*
* Question:
* For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t
* (i.e., t is concatenated with itself one or more times).
* Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
*
* Examples:
* Input: str1 = "ABCABC", str2 = "ABC"      Output: "ABC"
* Input: str1 = "ABABAB", str2 = "ABAB"     Output: "AB"
* Input: str1 = "LEET", str2 = "CODE"       Output: ""
*
* Constraints:
* 1 <= str1.length, str2.length <= 1000
* str1 and str2 consist of English uppercase letters.
* */
public class GreatestCommonDivisorOfStrings {

    public static String gcdBruteForceSolution(String s1, String s2) {
        /*
        * Intent:
        * Fetch length of two strings
        * Find gcd of two lengths
        * */
        int l1 = s1.length(), l2 = s2.length();

        for(int i = Math.min(l1, l2); i>0; i--) {
            if(isValid(i, l1, l2, s1, s2)) {
                return s1.substring(0,i);
            }
        }
        return "";
        /*
        * Time Complexity = o(min(m,n) * (m+n))
        * Space Complexity = o(min(m,n)
        * where m and n is length of the two strings
        * */
    }

    public static boolean isValid(int n, int l1, int l2, String s1, String s2) {
        if(l1%n == 0 && l2%n == 0) {
            String baseString = s1.substring(0,n);
            return s1.replace(baseString,"").isEmpty() && s2.replace(baseString,"").isEmpty();
        }
        return false;
    }

    public static String gcdOptimisedSolution(String s1, String s2) {
        /*
        * Intent:
        * Check base condition that if a string A is gcd of string B, A+B should be equivalent to B+A
        * Otherwise, find the gcd of the two lengths and return substring
        * */
        if(!(s1+s2).equals(s2+s1)) {
            return "";
        }

        int gcdLength = gcd(s1.length(),s2.length());
        return s1.substring(0,gcdLength);
        /*
        * Time Complexity = o((m+n) + log(m*n) = o(m+n)
        * Space Complexity = o(m+n)
        * */
    }

    public static int gcd(int x, int y) {
        if(y==0)
            return x;
        return gcd(y,x%y);
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(min(m,n) * (m+n))
        System.out.println("Result : "+gcdBruteForceSolution("ABCABCABC","ABC"));
        System.out.println("Result : "+gcdBruteForceSolution("ABABABAB","ABAB"));

        //Optimised solution - o(m+n)
        System.out.println("\nResult : "+gcdOptimisedSolution("ABCABCABC","ABC"));
        System.out.println("Result : "+gcdOptimisedSolution("ABABABAB","ABAB"));
    }
}
