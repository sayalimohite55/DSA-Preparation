package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

/*
* Question : Longest Substring Without Repeating Characters
* Given a string s having lowercase characters,
* find the length of the longest substring without repeating characters.
* */
public class LongestSubstring_NoRepeatingCharacters {
    private static int bruteForceSolution(String str) {
        /*
        * Intent:
        * - Identify all substrings without repeating characters starting at every index
        * - return max length of substring
        *
        * substring - is sequence of characters in a string
        * */
        int MAX_CHAR = 26;
        int maxLength =0;
        int n = str.length();

        //substring starting at every index
        for(int i =0; i< n; i++ ) {
            boolean[] isCharVisited = new boolean[MAX_CHAR];

            int count = 0;
            for(int j=i; j < n; j++) {
                if(isCharVisited[str.charAt(j) - 'a']) {
                    //Character is already visited for current substring
                    break;
                } else {
                    //Mark current character as visited
                    isCharVisited[str.charAt(j) - 'a'] = true;
                }
                count ++;
            }
            maxLength = Math.max(maxLength, count);
        }
        return maxLength;
        /*
        * Time Complexity = o(n * MAX_CHAR) = o(n * MAX_CHAR)
        * Space Complexity = o(MAX_CHAR) = o(MAX_CHAR) --> here MAX_CHAR is 26 as lower case characters are 26
        * */
    }

    private static int solutionUsingSlidingWindow(String str) {
        /*
         * Intent:
         * - start with first character of string
         * - if a character is repeated at some index, identify first occurrence of that character
         * - And start new substring from the next character of first occurrence of repeated character
         *
         * substring - is sequence of characters in a string
         * */
        int maxLength = 0;
        int n = str.length();
        int i=0, start=0;

        Map<Character, Integer> map = new HashMap<>();
        while( i < n ) {
            char ch = str.charAt(i);
            //Map contains a character only in case of repetition
            if(map.containsKey(ch)) {
                maxLength = Math.max(maxLength, map.size());
                int index = map.get(ch);
                for(int j = start; j < index; j++)
                    map.remove(str.charAt(j));
                start = index+1;
            }

            map.put(ch, i);
            i++;
        }
        maxLength = Math.max(maxLength, map.size());
        return maxLength;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(n) = o(n)
         * */
    }

    public static void main(String[] args) {
        String s = "geeksforgeeks";

        //Brute Force Solution - o(n)
        System.out.println("Length of longest substring without repeating characters: "+bruteForceSolution(s));

        //solution using sliding window - o(n)
        System.out.println("Length of longest substring without repeating characters: "+solutionUsingSlidingWindow(s));
    }
}
