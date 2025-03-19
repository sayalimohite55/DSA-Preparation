package com.problems.arraysAndStrings;

import java.util.*;

/*
* Question:
* You have an array of floating point numbers averages which is initially empty.
* You are given an array nums of n integers where n is even.
* You repeat the following procedure n / 2 times:
* Remove the smallest element, minElement, and the largest element maxElement, from nums.
* Add (minElement + maxElement) / 2 to averages.
* Return the minimum element in averages.
* */
public class MinAvgOfSmallestAndLargestElements {

    private static double distinctAveragesUsingSortedSet(int[] arr) {
        /*
        * Intent:
        * Sort the array
        * Start calculating average for left and right most element
        * */

        Arrays.sort(arr);
        int left =0 , right = arr.length-1;
        SortedSet<Double> set = new TreeSet<>();
        while(left<right) {
            set.add((arr[left]+arr[right])/2.0);
            left++;
            right--;
        }
        return set.first();
        /*
        * Time Complexity = o(n log n) + o(n) = o(n log n)
        * Space Complexity = o(n)
        * */
    }

    private static double distinctAverages(int[] arr) {
        /*
         * Intent:
         * Sort the array
         * Start calculating average for left and right most element and return min
         * */

        Arrays.sort(arr);
        int left =0 , right = arr.length-1;
        double min = Double.MAX_VALUE;

        while(left<right) {
            double avg = (arr[left]+arr[right])/2.0;
            if(min > avg)
                min = avg;
            left++;
            right--;
        }
        return min;
        /*
         * Time Complexity = o(n log n) + o(n) = o(n log n)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Solution Using Sorted Set - o(n log n)
        System.out.println("Min Average: "+distinctAveragesUsingSortedSet(new int[] {4,1,4,0,3,5}));
        System.out.println("Min Average: "+distinctAveragesUsingSortedSet(new int[] {1,100}));
        System.out.println("Min Average: "+distinctAveragesUsingSortedSet(new int[] {9,5,7,8,7,9,8,2,0,7}));

        //Optimised solution without extra space- o(n log n)
        System.out.println("\nMin Average: "+distinctAverages(new int[] {4,1,4,0,3,5}));
        System.out.println("Min Average: "+distinctAverages(new int[] {1,100}));
        System.out.println("Min Average: "+distinctAverages(new int[] {9,5,7,8,7,9,8,2,0,7}));
    }
}
