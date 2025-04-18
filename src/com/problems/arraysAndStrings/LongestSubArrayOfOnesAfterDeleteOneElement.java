package com.problems.arraysAndStrings;

/*
* Question:
* Given a binary array nums, you should delete one element from it.
* Return the size of the longest non-empty subarray containing only 1's in the resulting array.
* Return 0 if there is no such subarray.
*
* Examples:
*
*Input: nums = [1,1,0,1]        Output: 3
*Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
*
* Input: nums = [0,1,1,1,0,1,1,0,1]     Output: 5
* Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value
* of 1's is [1,1,1,1,1].
*
* Input: nums = [1,1,1]     Output: 2
* Explanation: You must delete one element.
*
* Constraints:
* 1 <= nums.length <= 105
* nums[i] is either 0 or 1.
* */
public class LongestSubArrayOfOnesAfterDeleteOneElement {
    public static int longestSubarray(int[] nums) {
        int i=0, j=0, n = nums.length;
        int zeroIndex = -1, count = 0, maxCount = 0;
        while(j < n) {
            if(nums[j] == 1)
                count++;
            else if(zeroIndex != -1) {
                count = j-zeroIndex-1;
                i = zeroIndex+1;
                zeroIndex = j;
            } else {
                zeroIndex = j;
            }
            j++;
            maxCount = Math.max(count, maxCount);
        }
        if(maxCount == n)
            return maxCount - 1;
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println("Result: "+longestSubarray(new int[]{1,1,0,1}));
        System.out.println("Result: "+longestSubarray(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}));
        System.out.println("Result: "+longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
        System.out.println("Result: "+longestSubarray(new int[]{1,1,1}));
        System.out.println("Result: "+longestSubarray(new int[]{0,0,0}));
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }
}
