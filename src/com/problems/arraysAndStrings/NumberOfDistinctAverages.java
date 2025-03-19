package com.problems.arraysAndStrings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
* Question:
* You are given a 0-indexed integer array nums of even length.
* As long as nums is not empty, you must repetitively:
*   Find the minimum number in nums and remove it.
*   Find the maximum number in nums and remove it.
*   Calculate the average of the two removed numbers.
*   The average of two numbers a and b is (a + b) / 2.
*
* For example, the average of 2 and 3 is (2 + 3) / 2 = 2.5.
* Return the number of distinct averages calculated using the above process.
* Note that when there is a tie for a minimum or maximum number, any can be removed.
* */
public class NumberOfDistinctAverages {

    private static int distinctAverages(int[] arr) {
        /*
        * Intent:
        * Sort the array
        * Start calculating average for left and right most element
        * */

        Arrays.sort(arr);
        int left =0 , right = arr.length-1;
        Set<Double> set = new HashSet<>();
        while(left<right) {
            set.add((arr[left]+arr[right])/2.0);
            left++;
            right--;
        }
        return set.size();
        /*
        * Time Complexity = o(n log n) + o(n) = o(n log n)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Count: "+distinctAverages(new int[] {4,1,4,0,3,5}));
        System.out.println("Count: "+distinctAverages(new int[] {1,100}));
        System.out.println("Count: "+distinctAverages(new int[] {9,5,7,8,7,9,8,2,0,7}));
    }
}
