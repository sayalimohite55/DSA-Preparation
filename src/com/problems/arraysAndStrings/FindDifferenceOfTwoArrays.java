package com.problems.arraysAndStrings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* Question:
* Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
* answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
* answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
* Note that the integers in the lists may be returned in any order.
* */
public class FindDifferenceOfTwoArrays {

    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int num : nums1) {
            set1.add(num);
        }

        for(int num : nums2) {
            set2.add(num);
            if(set1.contains(num))
                set1.remove(num);
        }

        for(int num : nums1) {
            if(set2.contains(num))
                set2.remove(num);
        }

        List<List<Integer>> result = new ArrayList<>(2);
        result.add(new ArrayList<>(set1));
        result.add(new ArrayList<>(set2));
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Result: "+ findDifference(new int[]{1,2,3}, new int[]{2,4,6}));
        System.out.println("Result: "+ findDifference(new int[]{1,2,3,3}, new int[]{1,1,2,2}));
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }
}
