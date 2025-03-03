package com.problems.arraysAndStrings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
* Question: Remove Duplicates from Sorted Array
* Given an integer array nums sorted in non-decreasing order,
* remove the duplicates in-place such that each unique element appears only once.
* The relative order of the elements should be kept the same.
* Then return the number of unique elements in nums.
* Consider the number of unique elements of nums to be k, to get accepted,
* you need to do the following things:
* Change the array nums such that the first k elements of nums contain the unique
* elements in the order they were present in nums initially.
* The remaining elements of nums are not important as well as the size of nums. Return k.
* */
public class RemoveDuplicatesFromSortedArray {
    private static void bruteForceSolution(int[] arr) {
        /*
        * Intent: Store all elements to set. It will give list of unique elements preserving sequence
        * */
        Set<Integer> set = new HashSet<>();
        for (int j : arr)
            set.add(j);

        int i=0;
        for(int num : set)
            arr[i++] = num;

        printOutput(arr, i);
        /*
        * Time Complexity = o(n) + o(n) => o(n)
        * Space complexity = o(n)
        * */
    }

    private static void optimisedSolution(int[] arr) {
        /*
         * Intent: Store all elements to set. It will give list of unique elements preserving sequence
         * */
        int count=1, i;
        for(i=1;i<arr.length; i++) {
            if(arr[i] < arr[i-1])
                break;
            if(arr[i] != arr[count-1]) {
                arr[count++] = arr[i];
            }
        }

        printOutput(arr, count);
        /*
         * Time Complexity = o(n)
         * Space complexity = o(1)
         * */
    }

    private static void printOutput(int[] arr, int n) {
        System.out.println();
        for(int i =0; i<n; i++)
            System.out.print(" " + arr[i]);
    }
    public static void main(String[] args) {
        int[] arr = new int[] {0,0,1,1,1,2,2,3,3,4};

        //Brute force solution - using extra space  o(n)
        bruteForceSolution(arr);

        //Optimised solution - o(n)
        optimisedSolution(arr);
    }
}
