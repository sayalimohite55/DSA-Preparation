package com.problems.arraysAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
* Question:
* Given an array of strings strs, group the anagrams together.
* You can return the answer in any order.
*
* An anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
* using all the original letters exactly once.
*
* Input: strs = ["eat","tea","tan","ate","nat","bat"]
* Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
* */
public class GroupAnagrams {
    private static void groupAnagrams(String[] strs) {
        /*
        * Intent:
        * Use hashtable to store a string and its anagrams
        * To make sure we create only one key for a set of characters,
        * use sorting for key creation
        * */
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s: strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            if(!map.containsKey(key))
                map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        printAnagrams(new ArrayList(map.values()));
        /*
        * Time Complexity = o(n * k * log k)
        *       where   n - length of strs
        *               k - max length of string in strs
        *       Outer loop has complexity o(n)
        *       Then fo each string say of size k we sort array -o(k log k)
        *
        * Space Complexity = o(nk)
        * */
    }

    private static void printAnagrams(List<List<String>> list) {
        System.out.println();
        for(List<String> subList : list) {
            System.out.print(" [");
            for(String s: subList) {
                System.out.print(s+",");
            }System.out.print("]");
        }
    }

    public static void main(String[] args) {
        groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
        groupAnagrams(new String[]{""});
        groupAnagrams(new String[]{"a"});
    }
}
