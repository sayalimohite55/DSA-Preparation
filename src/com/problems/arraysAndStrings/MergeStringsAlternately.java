package com.problems.arraysAndStrings;

/*
* Question:
* You are given two strings word1 and word2.
* Merge the strings by adding letters in alternating order, starting with word1.
* If a string is longer than the other, append the additional letters onto the end of the merged string.
* Return the merged string.
* */
public class MergeStringsAlternately {

    private static String mergeStrings(String s1, String s2) {
        int i=0, j=0;

        StringBuilder s3 = new StringBuilder();
        while(i<s1.length() || j < s2.length()) {
            if(i != s1.length() && j != s2.length()) {
                s3.append(s1.charAt(i++));
                s3.append(s2.charAt(j++));
            } else if(i == s1.length()) {
                s3.append(s2, j, s2.length());
                break;
            } else {
                s3.append(s1, i, s1.length());
                break;
            }
        }
        return s3.toString();
        /*
        * Time Complexity = o((m+n)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Output: " + mergeStrings("abc","pqr"));
        System.out.println("Output: " + mergeStrings("ab","pqrs"));
        System.out.println("Output: " + mergeStrings("abcd","pq"));
        System.out.println("Output: " + mergeStrings("abcd",""));
    }
}
