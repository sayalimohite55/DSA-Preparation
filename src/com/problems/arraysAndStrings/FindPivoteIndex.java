package com.problems.arraysAndStrings;

/*
* Question:
* Given an array of integers nums, calculate the pivot index of this array. The pivot index is the
* index where the sum of all the numbers strictly to the left of the index is equal to the sum of
* all the numbers strictly to the index's right.
*
* If the index is on the left edge of the array, then the left sum is 0 because there are no elements
* to the left. This also applies to the right edge of the array.
* Return the leftmost pivot index. If no such index exists, return -1.
*
* Examples:
*
* Input: nums = [1,7,3,6,5,6]       Output: 3
* Explanation:
* The pivot index is 3.
* Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
* Right sum = nums[4] + nums[5] = 5 + 6 = 11
*
* Input: nums = [1,2,3]         Output: -1
* Explanation:
* There is no index that satisfies the conditions in the problem statement.
*
* Input: nums = [2,1,-1]        Output: 0
* Explanation:
* The pivot index is 0.
* Left sum = 0 (no elements to the left of index 0)
* Right sum = nums[1] + nums[2] = 1 + -1 = 0
* Constraints:
*  1 <= nums.length <= 104
* -1000 <= nums[i] <= 1000
*  Note: This question is the same as 1991: https://leetcode.com/problems/find-the-middle-index-in-array/
*/

public class FindPivoteIndex {

    public static int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];

        leftSum[0] = nums[0];
        rightSum[n-1] = nums[n-1];
        for(int i =1, j= n-2; i<n && j>=0 ; i++, j--) {
            leftSum[i] = leftSum[i-1] + nums[i];
            rightSum[j] = rightSum[j+1] + nums[j];
        }

        int pivote = -1;
        for(int i =0;i<n; i++) {
            if(leftSum[i] == rightSum[i]) {
                pivote = i;
                break;
            }
        }
        return pivote;
    }

    public static void main(String[] args) {
        System.out.println("Result: "+pivotIndex(new int[]{1,7,3,6,5,6})); //3
        System.out.println("Result: "+pivotIndex(new int[]{1,2,3})); //-1
        System.out.println("Result: "+pivotIndex(new int[]{2,1,-1})); //0
        /*
        * Intent: Use prefix sum
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }
}
