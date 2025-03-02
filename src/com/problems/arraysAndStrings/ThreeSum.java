package com.problems.arraysAndStrings;

import java.util.*;

/*
* Question
* Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
* such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
*
* Notice that the solution set must not contain duplicate triplets.
* */
public class ThreeSum {
    private static void bruteForceSolution(int arr[]) {
        /*
        * Intent: Go through every unique i,j,k combination to identify triplets that sum up to 0
        * */
        List<List<Integer>> triplets = new ArrayList<>();
        for(int i=0;i<arr.length; i++) {
            for(int j=i+1; j< arr.length; j++) {
                for(int k=j+1; k<arr.length; k++) {
                    if(arr[i]+arr[j]+arr[k] == 0) {
                        triplets.add(Arrays.asList(arr[i],arr[j],arr[k]));
                    }
                }
            }
        }
        printOutput(triplets);
        /*
        * Time Complexity = o(n^3)
        * Space Complexity = o (1)
        * Potential problem with this approach is, if arr contains duplicate numbers,
        * triplets summing up to 0 might be repetitive though i,j,k combination is unique
        * */
    }

    private static void threeSum(int arr[]) {
        /*
         * Intent: a+b+c = 0 => a+b = -c => this can be achieved with o(n^2)
         * */
        Arrays.sort(arr);

        List<List<Integer>> triplets = new ArrayList<>();
        for(int i=0; i<arr.length; i++) {
            if(i==0 || arr[i] != arr[i-1]) {
                int left = i + 1, right = arr.length - 1;
                while (left < right) {
                    int sum = arr[i] + arr[left] + arr[right];
                    if (sum == 0) {
                        triplets.add(Arrays.asList(arr[i], arr[left++], arr[right--]));
                        while (left < right && arr[left] == arr[left - 1])
                            left++;
                    } else if (sum < 0)
                        left++;
                    else
                        right--;
                }
            }
        }
        System.out.println("\n\n");
        printOutput(triplets);
        /*
         * Time Complexity = o(n log n) + o(n^2) = o(n^2)
         * Space Complexity = o (2)
         * */
    }

    private static void printOutput(List<List<Integer>> list) {
        for (List<Integer> triplet : list) {
            System.out.println("[" + triplet.get(0) + ", " + triplet.get(1) + ", " + triplet.get(2) + "]");
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-1,0,1,2,-1,-4};

        //Brute force solution - o(n^3)
        bruteForceSolution(arr);

        //optimised solution - o(n^2)
        threeSum(arr);
    }
}
