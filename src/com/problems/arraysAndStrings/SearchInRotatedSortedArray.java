package com.problems.arraysAndStrings;

/*
* Question:
* There is an integer array nums sorted in ascending order (with distinct values).
* Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
* such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed)
* For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
* Given the array nums after the possible rotation and an integer target,
* return the index of target if it is in nums, or -1 if it is not in nums.
* Follow up - You must write an algorithm with O(log n) runtime complexity.
*
* */
public class SearchInRotatedSortedArray {

    private static int linearSearch(int[] nums, int target) {
       for(int i=0; i<nums.length; i++)
           if(target == nums[i])
               return i;
       return -1;
       /*
       * Time Complexity = o(n)
       * Space Complexity = o(1)
       * */
    }

    private static int binarySearch(int[] nums, int target) {
        /*
        * Intent:
        * 1. Find Pivot element using binary search
        * 2. Use binary search to find target
        * */
        int n = nums.length, left = 0, right = n-1, mid = 0;

        while(left <= right) {
            mid = (left+right)/2;
            if(nums[mid] > nums[n-1]) {
                left = mid + 1;
            } else
                right = mid - 1;
        }

        int result = binarySearch(nums, target, 0, left-1);
        if(result != -1)
            return result;

        return binarySearch(nums, target, left , n-1);
        /*
         * Time Complexity = o(log n)
         * Space Complexity = o(1)
         * */
    }

    private static int binarySearch(int[] nums, int target,int left , int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //Brute Force Solution using linear search - o(n)
        System.out.println("Index: "+linearSearch(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println("Index: "+linearSearch(new int[]{4,5,6,7,0,1,2}, 3));
        System.out.println("Index: "+linearSearch(new int[]{1}, 0));

        //Optimal Solution - o(log n)
        System.out.println("Index: "+binarySearch(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println("Index: "+binarySearch(new int[]{4,5,6,7,0,1,2}, 3));
        System.out.println("Index: "+binarySearch(new int[]{1}, 0));
    }
}
