package com.problems.arraysAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/*
* Question:
* You are given a 0-indexed integer array nums and an integer x.
* Find the minimum absolute difference between two elements in the array that are at least x indices apart.
* In other words, find two indices i and j such that abs(i - j) >= x and abs(nums[i] - nums[j]) is minimized.
* Return an integer denoting the minimum absolute difference between two elements that are
* at least x indices apart.
*
* eg.
* Input: nums = [4,3,2,4], x = 2
* Output: 0
* Explanation: We can select nums[0] = 4 and nums[3] = 4.
* They are at least 2 indices apart, and their absolute difference is the minimum, 0.
* It can be shown that 0 is the optimal answer.
* */
public class MinAbsoluteDifferenceWithConstraint {
    private static int minAbsoluteDifferenceBruteForce(List<Integer> nums, int x) {
        int min = Integer.MAX_VALUE;
        int n = nums.size();

        for(int i=0; i<n-x;i++) {
            for(int j=i+x; j<n; j++) {
                int diff = Math.abs(nums.get(i)-nums.get(j));
                if(min > diff)
                    min = diff;
            }
        }
        return min;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int minAbsoluteDifference(List<Integer> nums, int x) {
        int min = Integer.MAX_VALUE;
        int n = nums.size();
        TreeSet<Integer> set = new TreeSet<>();

        for(int i=n-1-x; i>=0; i--) {
            int nextIndex = i+x;
            set.add(nums.get(nextIndex));

            int elem = nums.get(i);
            Integer floor = set.floor(elem);
            Integer ceil = set.ceiling(elem);

            if(floor != null && min > elem - floor)
                min = elem - floor;

            if(ceil != null && min > ceil - elem)
                min = ceil - elem;
        }
        return min;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^2)
        System.out.println("Minimum absolute difference between elements with constraint: "
                +minAbsoluteDifferenceBruteForce(Arrays.asList(new Integer[]{4,3,2,4}), 2));
        System.out.println("Minimum absolute difference between elements with constraint: "
                +minAbsoluteDifferenceBruteForce(Arrays.asList(new Integer[]{5,3,2,10,15}), 1));
        System.out.println("Minimum absolute difference between elements with constraint: "
                +minAbsoluteDifferenceBruteForce(Arrays.asList(new Integer[]{1,2,3,4}), 3));

        //Solution using TreeSet - o(n)
        System.out.println("\nMinimum absolute difference between elements with constraint: "
                +minAbsoluteDifference(Arrays.asList(new Integer[]{4,3,2,4}), 2));
        System.out.println("Minimum absolute difference between elements with constraint: "
                +minAbsoluteDifference(Arrays.asList(new Integer[]{5,3,2,10,15}), 1));
        System.out.println("Minimum absolute difference between elements with constraint: "
                +minAbsoluteDifference(Arrays.asList(new Integer[]{1,2,3,4}), 3));
    }
}
