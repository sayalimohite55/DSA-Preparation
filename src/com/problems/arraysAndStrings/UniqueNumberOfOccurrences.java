package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
* Question: Unique Number of Occurrences
* Given an array of integers arr, return true if the number of occurrences of each value
* in the array is unique or false otherwise.
* */
public class UniqueNumberOfOccurrences {

    private static boolean uniqueOccurrences(int[] arr) {
        /*
        * Intent:
        * - While trversing array, start maintaining a count of occurrences of each number using hashmap
        * - As set contains only unique entries, create a set of values from hashmap <k,V>
        * - If count of occurrences is unique for each number,
        *   size of set of values and hashmap will be same
        */
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }

        Set<Integer> set = new HashSet<>(map.values());
        /*
        * // Another way for above line:
        * Set<Integer> set = map.values().stream().collect(Collectors.toSet());
        * */
        return (set.size() == map.size());
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String args[]) {
        System.out.println("Output: " + uniqueOccurrences(new int[]{1,2,2,1,1,3}));
        System.out.println("Output: " + uniqueOccurrences(new int[]{1,2}));
        System.out.println("Output: " + uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,-3,10,0}));
    }
}
