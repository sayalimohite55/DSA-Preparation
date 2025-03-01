package com.problems.arraysAndStrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
* Question:
* Given an array of integers nums and an integer target,
* return indices of the two numbers such that they add up to target.
* You may assume that each input would have exactly one solution, and
* you may not use the same element twice. You can return the answer in any order.
* */
public class TwoSum {

    private static void bruteForceSolution(int[] arr, int target) {
        /*
        * Intent: Iterate through entire array to identify pair of numbers adding up to target
        * */

        for(int i=0;i<arr.length; i++) {
            for(int j=i+1; j<arr.length; j++) {
                if(arr[i]+arr[j] == target) {
                    System.out.println("Indices are: " + i + " and " + j);
                    return;
                }
            }
        }
        System.out.println("No such pair exists");
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static void solutionUsingSorting(int[] arr, int target) {
        /*
         * Intent:
         * 1. Sort the array
         * 2. Use two pointer technique
         * 3. one pointer starts with 0 and one from last element and check addition
         * */

        Arrays.sort(arr);
        int left =0, right=arr.length-1;
        while(left < right) {
            int sum = arr[left]+arr[right];
            if( sum < target) {
                left++;
            } else if( sum > target) {
                right --;
            } else {
                System.out.println("Indices are: " + left + " and " + right);
                return;
            }
        }
        System.out.println("No such pair exists");
        /*
         * Time Complexity = o(n log n) + o(n) --> o(nlogn) for sorting and o(n) for final traversal
         *                 = o(n log n)
         * Space Complexity = o(1)
         * */
    }

    private static void solutionUsingHashMap(int[] arr, int target) {
        /*
         * Intent:
         * 1. Add all elements with their in arr to hashmap and find if target-arr[i] exists.
         * */

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<arr.length; i++) {
            if(map.containsKey(target-arr[i])) {
                System.out.println("Indices are: " + map.get(target-arr[i]) + " and " + i);
                return;
            }
            map.put(arr[i], i);
        }
        System.out.println("No such pair exists");
        /*
         * Time Complexity = o(n) --> o(n) for traversal
         *                 = o(n)
         * Space Complexity = o(n)
         * */
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2,7,11,15};
        int target = 9;

        //Brute force solution - o(n^2)
        bruteForceSolution(arr, target);

        //Better Solution - using sorting and two pointer - o(n log n)
        solutionUsingSorting(arr, target);

        //optimal solution - using extra space - o(n)
        solutionUsingHashMap(arr, target);
    }
}
