package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

/*
* Question:
* Given an array nums of size n, return the majority element.
* The majority element is the element that appears more than ⌊n / 2⌋ times.
* You may assume that the majority element always exists in the array.
* */
public class MajorityElement {

    private static int majorityElementBruteForceSolution(int[] arr) {
        /*
        * Intent:
        * For each element count occurrences
        * Element with more than n/2 occurrence should be returned
        * */
        int n= arr.length;
        for(int i=0; i<n; i++) {
            int count = 1;
            for(int j= i+1; j<n; j++) {
                if(arr[i] == arr[j])
                    count++;
                if(count > n/2)
                    return arr[i];
            }
        }
        return -1;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int majorityElementUsingHashMap(int[] arr) {
        /*
         * Intent:
         * For each element count occurrences and store it into hashtable
         * Element with more than n/2 occurrence should be returned
         * */
        int n= arr.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int j : arr) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }

        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if(entry.getValue() > n/2)
                return entry.getKey();
        }
        return -1;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    private static int majorityElementUsingBoyerMooreAlgo(int[] arr) {
        /*
         * Intent: Using Boyer Moore Voting Algorithm
         * Initially set first element as candidate
         * If candidate is equals to current number, increment count
         * and decrement otherwise
         * If count becomes 0, then choose next candidate.
         * This algo works here as it is confirmed that majority element exists more than n/2 times.
         * */
        int n= arr.length, count =0;
        Integer candidate = null;

        for (int num : arr) {
           if(count == 0)
               candidate = num;
           count += (candidate == num) ? 1 : -1;
        }
        return candidate;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^2)
        System.out.println("Majority element is : "+majorityElementBruteForceSolution(new int[] {3,2,3}));
        System.out.println("Majority element is : "+majorityElementBruteForceSolution(new int[] {2,2,1,1,1,2,2}));

        //Optimised Solution using extra space- o(n)
        System.out.println("\nMajority element is : "+majorityElementUsingHashMap(new int[] {3,2,3}));
        System.out.println("Majority element is : "+majorityElementUsingHashMap(new int[] {2,2,1,1,1,2,2}));

        //Optimised Solution without extra space- o(n)
        System.out.println("\nMajority element is : "+majorityElementUsingBoyerMooreAlgo(new int[] {3,2,3}));
        System.out.println("Majority element is : "+majorityElementUsingBoyerMooreAlgo(new int[] {2,2,1,1,1,2,2}));

    }
}
