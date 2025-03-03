package com.problems.arraysAndStrings;

/*
* Question: Remove Occurences of a Number
* Given an integer array nums and an integer val,
* remove all occurrences of val in nums in-place.
* The order of the elements may be changed.
* Then return the number of elements in nums which are not equal to val.
*
* Consider the number of elements in nums which are not equal to val be k,
* to get accepted, you need to do the following things:
* Change the array nums such that the first k elements of nums contain the elements
* which are not equal to val. The remaining elements of nums are not important as well as
* the size of nums. Return k.
* */
public class RemoveNumOccurences {

    private static int removeNumOccurrences(int[] arr, int val) {
        /*
        * Intent:
        * Start from beginning of array, if element is equals to val, replace it with rightmost value
        * */
        if(arr.length == 1 && arr[0] == val)
            return 0;

        int left =0, right = arr.length-1;
        while(left <= right) {
            if(arr[left] == val) {
                //swap
                arr[left] = arr[right];
                arr[right] = val;
                right--;
            } else
                left++;
        }
        return left;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    private static void printOutput(int[] arr, int n) {
        System.out.println();
        for(int i =0; i<n; i++)
            System.out.print(" " + arr[i]);
    }

    public static void main(String[] args) {
        int[] arr = new int[] {0,1,2,2,3,0,4,2};
        int val = 2;
        int count = removeNumOccurrences(arr, val);
        printOutput(arr, count);

        arr = new int[] {3,2,2,3};
        val = 3;
        count = removeNumOccurrences(arr, val);
        printOutput(arr, count);

        arr = new int[] {3,3,4,2,3,3};
        val = 3;
        count = removeNumOccurrences(arr, val);
        printOutput(arr, count);
    }
}
