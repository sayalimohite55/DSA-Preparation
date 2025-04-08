package com.problems.arraysAndStrings;

import java.util.HashMap;

/*
* Question:
* Given two strings s and t, return true if t is an anagram of s, and false otherwise.
*
* Examples:
*
* Input: s = "anagram", t = "nagaram"   Output: true
* Input: s = "rat", t = "car"   Output: false
* Constraints:
* 1 <= s.length, t.length <= 5 * 104
* s and t consist of lowercase English letters.
* Follow up: What if the inputs contain Unicode characters?
* How would you adapt your solution to such a case?
* */
public class ValidAnagrams {
    public static boolean isAnagramUsingHashMap(String s, String t) {
        if(s.length() != t.length())
            return false;

        HashMap<Character,Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }

        for(char ch : t.toCharArray()) {
            if(map.containsKey(ch)) {
                int count = map.get(ch)-1;
                if(count == 0)
                    map.remove(ch);
                else
                    map.put(ch,count);
            } else
                return false;
        }
        return map.isEmpty();
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        //Solution using hashMap - o(n)
        System.out.println("Result : "+isAnagramUsingHashMap("anagram","nagaram"));
        System.out.println("Result : "+isAnagramUsingHashMap("rat","car"));
        System.out.println("Result : "+isAnagramUsingHashMap("\\.","\\+"));

        /*
         * Brute Force approach could be to compare each alphabet with o(n^2) complexity
         *
         * Another approach could be to sort two strings and then compare
         * This will have complexities as follows:
         * Time Complexity = o(n log n) + o(n) = o(n log n)
         * Space Complexity = o(n)
         */
    }
}
