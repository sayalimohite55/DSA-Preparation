package com.problems.arraysAndStrings;
/*
* Question:
* There are three types of edits that can be performed on strings
* 1. Insert a character
* 2. Remove a character
* 3. Replace a character
*
* Given two strings write a function to check if they are one edit or zero edits away
* eg. pale, ple => true
*     pales, bale => false
* */

import java.util.HashMap;
import java.util.Map;

public class IsOneOrZeroEditsAway {
    private static boolean isOneOrZeroEditsAway(String s1, String s2) {
        /*
        * Intent: 1. For two strings to be zero or one edit away,
        *            difference of their lengths has to be <= 1
        *         2. Use hashmap to check difference of two strings
        * */

        //base case
        if(Math.abs(s1.length() - s2.length()) > 1)
            return false;
        Map<Character, Integer> map = new HashMap<>();

        int n = s1.length();
        for(int i=0;i<n ;i++) {
            char ch = s1.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        n = s2.length();
        int editCount = 0;
        for(int i=0; i < n && editCount <= 1 ;i++) {
            char ch = s2.charAt(i);
            if(map.containsKey(ch)) {
                int count = map.get(ch)-1;
                if(count == 0)
                    map.remove(ch);
                else
                    map.put(ch, count);
            } else
                editCount ++;
        }

        if(editCount <= 1 && map.isEmpty())
            return true;
        else if(editCount <= 1 && map.size() == 1) {
            for(Map.Entry<Character,Integer> entry: map.entrySet()) {
                if(entry.getValue() == 1)
                    return true;
            }
        }
        return false;

        /*
        * Time Complexity = o(n) + o(n) + o(1)
        *                 = o(2n) = o(n)
        * Space Complexity = o(n)
        * */
    }

    public static void main (String[] args) {
        System.out.println("output: " + isOneOrZeroEditsAway("pale", "ple"));
        System.out.println("output: " + isOneOrZeroEditsAway("pale","bale"));
        System.out.println("output: " + isOneOrZeroEditsAway("pales","bale"));
    }
}
