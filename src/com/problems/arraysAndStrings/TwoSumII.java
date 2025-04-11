package com.problems.arraysAndStrings;

import java.util.Arrays;

/*
* Question:
* Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
* find two numbers such that they add up to a specific target number. Let these two numbers be
* numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
* Return the indices of the two numbers, index1 and index2, added by one as an integer array
* [index1, index2] of length 2.
* The tests are generated such that there is exactly one solution. You may not use the same element twice.
* Your solution must use only constant extra space.
* */
public class TwoSumII {
    public static int[] twoSum(int[] numbers, int target) {
        int l=0, r=numbers.length-1;
        int sum = numbers[l] + numbers[r];
        while(sum != target) {
            if(sum < target)
                l++;
            else if(sum > target)
                r--;
            sum = numbers[l]+numbers[r];
        }
        return new int[]{l+1,r+1};
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }
    public static void main(String[] args) {
        int[] arr = new int[] {2,7,11,15};
        int target = 9;
        //solution - o(n)
        System.out.println("Result: "+ Arrays.toString(twoSum(arr, target)));
    }
}
