package com.problems.arraysAndStrings;

import java.util.*;

/*
 * Question
 * Given an integer array nums of length n and an integer target,
 * find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 * */
public class ClosestThreeSum {
    private static int bruteForceSolution(int arr[], int target) {
        /*
         * Intent: Go through every unique i,j,k combination to identify triplet
         *         that sum up to closest of target
         * */

        int diff = Integer.MAX_VALUE;

        for(int i=0;i<arr.length; i++) {
            for(int j=i+1; j< arr.length; j++) {
                for(int k=j+1; k<arr.length; k++) {
                    int sum = arr[i]+arr[j]+arr[k];
                    if(sum == target) {
                        return target;
                    }

                    if(Math.abs(target-sum) < diff)
                        diff = target-sum;
                }
            }
        }
        return target - diff;
        /*
         * Time Complexity = o(n^3)
         * Space Complexity = o (1)
         * */
    }

    private static int closestThreeSum(int arr[], int target) {
        /*
         * Intent: a+b+c = 0 => a+b = -c => this can be achieved with o(n^2)
         * */
        Arrays.sort(arr);
        int diff =Integer.MAX_VALUE;

        for(int i=0; i<arr.length; i++) {
            if(i==0 || arr[i] != arr[i-1]) {
                int left = i + 1, right = arr.length - 1;
                while (left < right) {
                    int sum = arr[i] + arr[left] + arr[right];
                    if (sum == target) {
                        return sum;
                    }

                    if(Math.abs(target-sum) < diff)
                        diff = target-sum;

                    if (sum < 0)
                        left++;
                    else
                        right--;
                }
            }
        }
        return target-diff;
        /*
         * Time Complexity = o(n log n) + o(n^2) = o(n^2)
         * Space Complexity = o (2)
         * */
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-1,2,1,-4};
        int target = 1;

        //Brute force solution - o(n^3)
        System.out.println("Output: " + bruteForceSolution(arr, target));

        //optimised solution - o(n^2)
        System.out.println("Output: "+closestThreeSum(arr, target));
    }
}
