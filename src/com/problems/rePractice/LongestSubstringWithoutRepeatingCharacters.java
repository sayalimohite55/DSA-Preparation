package com.problems.rePractice;

import java.util.HashMap;

/*
* Question:
* Given a string s, find the length of the longest substring without duplicate characters.
*
* Examples:
* Input: s = "abcabcbb"     Output: 3
* Explanation: The answer is "abc", with the length of 3.
*
* Input: s = "bbbbb"    Output: 1
* Explanation: The answer is "b", with the length of 1.
*
* Input: s = "pwwkew"   Output: 3
* Explanation: The answer is "wke", with the length of 3.
*
* Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
*
* Constraints:
* 0 <= s.length <= 5 * 104
* s consists of English letters, digits, symbols and spaces.
* */
public class LongestSubstringWithoutRepeatingCharacters {
    public static String longestSubstringWithoutRepeatingCharacters(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int n = s.length(), startIndex = 0;
        int maxLength = 0;
        String maxString = "";

        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if(map.containsKey(ch)) {
                int count = i-startIndex;
                if(count > maxLength) {
                    maxLength = count;
                    maxString = s.substring(startIndex,i);
                }
                int index = map.get(ch);
                int j = startIndex;
                while(j<=index) {
                    char c = s.charAt(j++);
                    map.remove(c);
                }
                startIndex = index+1;
            }
            map.put(ch,i);
        }
        int count = n-startIndex;
        if(count > maxLength) {
            maxLength = count;
            maxString = s.substring(startIndex,n);
        }

        return maxString;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Longest substring without repeating characters: "+
                longestSubstringWithoutRepeatingCharacters("abcabcbb"));
        System.out.println("Longest substring without repeating characters: "+
                longestSubstringWithoutRepeatingCharacters("bbbbb"));
        System.out.println("Longest substring without repeating characters: "+
                longestSubstringWithoutRepeatingCharacters("pwwkew"));
        System.out.println("Longest substring without repeating characters: "+
                longestSubstringWithoutRepeatingCharacters("pwke"));
    }
}
