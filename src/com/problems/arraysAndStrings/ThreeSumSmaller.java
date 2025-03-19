package com.problems.arraysAndStrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
* Question:
* Given an array of n integers nums and an integer target,
* find the number of index triplets i, j, k with 0 <= i < j < k < n
* that satisfy the condition nums[i] + nums[j] + nums[k] < target.
* */
public class ThreeSumSmaller {
    private static int bruteForceSolution(int[] arr, int target) {
        int count =0, n= arr.length;
        for(int i=0;i<n;i++){
            for(int j = i+1; j<n; j++) {
                for(int k=j+1; k<n; k++) {
                    if(arr[i]+arr[j]+arr[k] < target)
                        count++;
                }
            }
        }
        return count;
        /*
        * Time Complexity = o(n^3)
        * Space Complexity = o(1)
        * */
    }

    private static int countThreeSumSmallerUsingTwoPointer(int[] arr, int target) {
        /*
        * Intent:
        * Conditions: 0<=i<j<k<n and arr[i] + arr[j] + arr[k] < target
        *                           => arr[i] + arr[j] < target - arr[k]
        * Break the problem statement into two parts threeSum Smaller is equivalent to
        *           arr[i] + twoSumSmaller
        * In order to optimise twoSumSmaller, sort array
        * */

        int count =0, n= arr.length;
        Arrays.sort(arr);
        for(int i=0;i<n;i++) {
            int left = i+1, right = n-1;
            while(left < right) {
                if(arr[left]+arr[right] < target-arr[i]) {
                    count += right - left;
                    left++;
                } else
                    right --;
            }
        }
        return count;
        /*
         * Time Complexity = o(n log n + n^2)
         * Space Complexity = o(1)
         * */
    }
    public static void main(String[] args) {
        //Brute Force Solution - o(n^3)
        System.out.println("Count: "+bruteForceSolution(new int[]{-2,0,1,3}, 2));
        System.out.println("Count: "+bruteForceSolution(new int[]{}, 0));
        System.out.println("Count: "+bruteForceSolution(new int[]{0}, 0));

        //Optimised Solution - o(n^2)
        System.out.println("Count: "+countThreeSumSmallerUsingTwoPointer(new int[]{-2,0,1,3}, 2));
        System.out.println("Count: "+countThreeSumSmallerUsingTwoPointer(new int[]{}, 0));
        System.out.println("Count: "+countThreeSumSmallerUsingTwoPointer(new int[]{0}, 0));
    }
}
