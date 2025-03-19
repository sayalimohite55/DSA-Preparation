package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

/*
* Question:
* Given an array nums of n integers, return an array of all the unique quadruplets
* [nums[a], nums[b], nums[c], nums[d]] such that: 0 <= a, b, c, d < n
*                                                 a, b, c, and d are distinct.
* nums[a] + nums[b] + nums[c] + nums[d] == target
* You may return the answer in any order.
* */
public class FourSum {
    private static int fourSumBruteForceSolution(int[] nums, int target) {
        int count =0, n= nums.length;
        for (int j : nums) {
            for (int k : nums) {
                for (int value : nums) {
                    for (int num : nums) {
                        if (j + k + value + num == target)
                            count++;
                    }
                }
            }
        }
        return count;
        /*
        * Time Complexity = o(n^4)
        * Space Complexity = o(1)
        * */
    }

    private static int fourSumOptimisedSolution(int[] nums, int target) {
        /*
        * Intent:
        * condition: nums[a] + nums[b] + nums[c] + nums[d] = target
        *       =>  nums[a] + nums[b] = target - (nums[c] + nums[d])
        * */
        int count = 0, n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : nums) {
            for (int j : nums) {
                map.merge(i+j, 1, Integer::sum);
            }
        }

        for (int i : nums) {
            for (int j : nums) {
                count += map.getOrDefault(target - (i+j), 0);
            }
        }
        return count;
        /*
         * Time Complexity = o(n^2)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^4)
        System.out.println("Count: "+ fourSumBruteForceSolution(new int[]{1,0,-1,0,-2,2}, 0));
        System.out.println("Count: "+ fourSumBruteForceSolution(new int[]{2,2,2,2,2}, 8));

        //Optimised Solution - o(n^2)
        System.out.println("\nCount: "+ fourSumOptimisedSolution(new int[]{1,0,-1,0,-2,2}, 0));
        System.out.println("Count: "+ fourSumOptimisedSolution(new int[]{2,2,2,2,2}, 8));

    }
}
