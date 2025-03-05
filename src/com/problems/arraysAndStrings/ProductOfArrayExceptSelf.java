package com.problems.arraysAndStrings;

/*
* Question:
* Given an integer array nums, return an array answer
* such that answer[i] is equal to the product of all the elements of nums except nums[i].
* The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
* You must write an algorithm that runs in O(n) time and without using the division operation.
* */
public class ProductOfArrayExceptSelf {

    private static void bruteForceSolution(int[] arr) {
        /*
        * Intent:
        * For each ith value iterate through array to calculate product excluding ith value
        * */
        int[] result = new int[arr.length];
        for(int i=0;i<arr.length; i++) {
            int product = 1;
            for(int j=0;j<arr.length; j++) {
                if(i!=j)
                    product *= arr[j];
            }
            result[i] = product;
        }
        printSolution(result);
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static void productExceptSelf(int[] arr) {
        /*
         * Intent:
         * Calculate total product using all values in arr
         * Maintain count of zeros as multiplication with zero will yield zero
         * Note division not possible if ith value is 0
         * */
        int[] result = new int[arr.length];
        int zeroCount = 0;
        int product = 1;

        for(int i=0;i<arr.length; i++) {
            if(arr[i] == 0)
                zeroCount ++;
            else
                product *= arr[i];
        }

        for(int i = 0; i<arr.length ; i++) {
            if (zeroCount == 0)
                result[i] = product / arr[i];
            else if (zeroCount == 1 && arr[i] == 0)
                result[i] = product;
            else
                result[i] = 0;
        }
        printSolution(result);
        /*
        * Time Complexity = o(n) +o(n) = o(2n) = o(n)
        * Space Complexity = o(1)
        * */
    }

    private static void printSolution(int[] arr) {
        System.out.print("\n[");
        for(int i =0; i<arr.length; i++)
            System.out.print(" "+arr[i]);
        System.out.print(" ]");
    }

    public static void main(String[] args){
        //Brute force solution - o(n^2)
        bruteForceSolution(new int[]{1,2,3,4});
        bruteForceSolution(new int[]{-1,1,0,-3,3});
        bruteForceSolution(new int[]{0,0});
        bruteForceSolution(new int[]{-1,1,0,-3,0});

        //optimised solution - o(n)
        System.out.println();
        productExceptSelf(new int[]{1,2,3,4});
        productExceptSelf(new int[]{-1,1,0,-3,3});
        productExceptSelf(new int[]{0,0});
        productExceptSelf(new int[]{-1,1,0,-3,0});
    }
}
