package com.problems.arraysAndStrings;

/*
* Question:
* Given an array of integers nums and an integer k, return the number of contiguous subarrays
* where the product of all the elements in the subarray is strictly less than k.
*
* Constraints:
* 1 <= nums.length <= 3 * 104
* 1 <= nums[i] <= 1000
* 0 <= k <= 106
* */
public class SubarrayProductLessThanK {
    private static int solutionUsingSlidingWindow(int[] nums, int k) {
        /*
        * Intent:
        * We start at 0th index & keep multiplying nums[i]
        * If product >= k then we remove elements from left till product >= k
        * Increment count by distance between right and left
        * */
        if(k <= 1)
            return 0;

        int count  = 0, product =1, n = nums.length;
        for(int left=0, right=0; right< n; right ++) {
            product *= nums[right];

            while(product >= k) {
                product/= nums[left++];
            }

            count += right-left +1;
        }
        return count;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {

        //optimised solution : o(n)
        System.out.println("\nCount: "+ solutionUsingSlidingWindow(new int[]{10,5,2,6}, 100));
        System.out.println("Count: "+ solutionUsingSlidingWindow(new int[]{1,2,3}, 0));
        System.out.println("Count: "+ solutionUsingSlidingWindow(new int[]{10,9,10,4,3,8,3,3,6,2,10,10,9,3}, 19));
    }
}
