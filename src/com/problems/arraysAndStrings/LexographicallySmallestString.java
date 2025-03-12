package com.problems.arraysAndStrings;

/*
* Question:
* You are given a string s and an integer k.
* Define a function distance(s1, s2) between two strings s1 and s2 of the same length n as:
* The sum of the minimum distance between s1[i] and s2[i] when the characters from 'a' to 'z' are placed in a cyclic order, for all i in the range [0, n - 1].
*
* For example, distance("ab", "cd") == 4, and distance("a", "z") == 1.
* You can change any letter of s to any other lowercase English letter, any number of times.
*
* Return a string denoting the lexicographically smallest string t you can get after some changes,
* such that distance(s, t) <= k.
* */
public class LexographicallySmallestString {
    private static String getSmallestString(String s, int k) {
        /*
        * Intent:
        * while k != 0
        * for each character, find min distance with 'a' and 'z'
        * if min <= k replace that char with 'a'
        * else reduce k from ch and mark k as 0
        * */
        if(k <= 1)
            return s;

        char[] arr = s.toCharArray();
        for(int i=0;i<arr.length; i++) {
            int left = arr[i] - 'a';
            int right = 'z' - arr[i] + 1;
            int min = Math.min(left,right);
            if(min <= k) {
                arr[i] = 'a';
                k -= min;
            } else {
                arr[i] = (char) (arr[i] - k);
                k = 0;
            }
        }
        return new String(arr);
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }
    public static void main(String[] args) {
        System.out.println("Output: "+getSmallestString("zbbz",3)); //o/p => aaaz
        System.out.println("Output: "+getSmallestString("xaxcd",4)); //o/p => aawcd
        System.out.println("Output: "+getSmallestString("lol",0)); //o/p => lol
    }
}
