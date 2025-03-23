package com.problems.arraysAndStrings;
/*
* Question:
* You are given a 0-indexed array of positive integers nums.
* Find the number of triplets (i, j, k) that meet the following conditions:
*   0 <= i < j < k < nums.length
*   nums[i], nums[j], and nums[k] are pairwise distinct.
*   In other words, nums[i] != nums[j], nums[i] != nums[k], and nums[j] != nums[k].
*   Return the number of triplets that meet the conditions.
* */
public class CountUnEqualTriplets {

    private static int unequalTriplets(int[] nums) {
        int count =0, n= nums.length;
        for(int i=0;i<n; i++) {
            for(int j=i+1;j<n; j++) {
                if(nums[i] != nums[j]) {
                    for(int k= j+1; k<n; k++) {
                        if(nums[i]!= nums[k] && nums[j] != nums[k])
                            count++;
                    }
                }
            }
        }
        return count;
        /*
        * Time Complexity = o(n^3)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        //Solution - o(n^3)
        System.out.println("\nResult: "+ unequalTriplets(new int[]{3,0,1,1,9,7}));
        System.out.println("Result: "+ unequalTriplets(new int[]{1,1,2,2,3}));
        System.out.println("Result: "+ unequalTriplets(new int[]{4,4,2,4,3}));
        System.out.println("Result: "+ unequalTriplets(new int[]{1,1,1,1,1}));
    }
}
