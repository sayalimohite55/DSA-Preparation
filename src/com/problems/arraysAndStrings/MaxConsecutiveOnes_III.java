package com.problems.arraysAndStrings;

/*
* Question:
* Given a binary array nums and an integer k, return the maximum number of consecutive 1's
* in the array if you can flip at most k 0's.
*
* Examples:
*
* Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2      Output: 6
* Explanation: [1,1,1,0,0,1,1,1,1,1,1]
*
* Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3  Output: 10
* Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
*
* Constraints:
* 1 <= nums.length <= 105
* nums[i] is either 0 or 1.
* 0 <= k <= nums.length
* */
public class MaxConsecutiveOnes_III {
    public static int longestOnes(int[] nums, int k) {
        int i=0, j=0, n = nums.length;
        int ones = 0, zeros = 0, maxLength = 0;

        while(j<n) {
            if(nums[j] == 1) {
                ones++;
            } else {
                zeros++;
            }
            j++;

            if(k==0)
                maxLength = Math.max(maxLength, ones);
            else
                maxLength = Math.max(maxLength, ones + zeros);

            if(k==0 && j<n && nums[j] == 0) {
                while(j<n && nums[j] == 0)
                    j++;
                ones = 0;
            } else if(j < n && nums[j] == 0 && zeros == k) {
                while(zeros == k && i<j) {
                    if(nums[i] == 1)
                        ones--;
                    else
                        zeros --;
                    i++;
                }
            }
        }
        return maxLength;
    }
    public static void main(String[] args) {
        System.out.println("Result: "+longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0},2));
        System.out.println("Result: "+longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1},3));
        System.out.println("Result: "+longestOnes(new int[]{0,0,1,1,1,0,0},0));
        System.out.println("Result: "+longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1},0));
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }
}
