package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

/*
* Question:
* Given two strings write a function to check of one string is a permutation of another
* permutation of a string means a string where sequence of all characters may be different using same set of characters as string1
* */
public class checkPermutation {
    private static boolean bruteForceSolution(String s1, String s2) {
        /*
        * Intent : check if all characters in string one is present in another string
        *          To handle duplicate character issue, remove occurrence of character
        * */

        if(s1.length() != s2.length())
            return false;

        for(int i=0; i<s1.length(); i++) {
            char ch = s1.charAt(i);
            if(s2.indexOf(ch) == -1) {
                return false;
            }
            s2 = s2.replaceFirst(""+ch, "");
        }
        return s2.isEmpty();
        /*
        * Time Complexity = o(n) * o(n) --> o(n) for traversal and o(n) for search in list
        *                 = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static boolean solutionUsingHashMap(String s1, String s2) {
        /*
        * Intent : Add all characters in string 1 into hashmap with their occurrence count
        *          Traverse string 2 and remove characters from hashmap.
        * */

        if(s1.length() != s2.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();
        int length = s1.length();
        for(int i=0; i<length; i++) {
            char ch = s1.charAt(i);
            map.put(ch, map.getOrDefault(ch,0)+1);
        }

        for(int i=0; i<length; i++) {
            char ch = s2.charAt(i);
            if(map.containsKey(ch)) {
                int count = map.get(ch)-1;
                if(count == 0)
                    map.remove(ch);
                else
                    map.put(ch, count);
            } else
                return false;
        }

        return map.isEmpty();
        /*
        * Time Complexity = o(n) + o(n) --> o(n) for first traversal and o(n) for second traversal
        *                 = o(2n) = o(n)
        * Space Complexity = o(n)
        * */
    }
    public static void main (String[] args) {
        //O(n^2) Solution
        System.out.println("Test case I: "+ bruteForceSolution("adefghacb", "abdefghac"));
        System.out.println("Test case I: "+ bruteForceSolution("abcdefgh", "s"));

        //O(n) Solution
        System.out.println("\nTest case I: "+ bruteForceSolution("adefghacb", "abdefghac"));
        System.out.println("Test case I: "+ bruteForceSolution("abcdefgh", "s"));
    }
}
