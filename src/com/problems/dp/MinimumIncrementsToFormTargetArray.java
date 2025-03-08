package com.problems.dp;

/*
* Question:
* You are given an integer array target.
* You have an integer array initial of the same size as target with all elements initially zeros.
* In one operation you can choose any subarray from initial and increment each value by one.
* Return the minimum number of operations to form a target array from initial.
* The test cases are generated so that the answer fits in a 32-bit integer.
*
* eg 1:
* Input: target = [1,2,3,2,1]
* Output: 3
* Explanation: We need at least 3 operations to form the target array from the initial array.
* [0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
* [1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
* [1,2,2,2,1] increment 1 at index 2.
* [1,2,3,2,1] target array is formed.
*
* eg 2:
* Input: target = [3,1,1,2]
* Output: 4
* Explanation: [0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2]
*
* eg. 3:
* Input: target = [3,1,5,4,2]
* Output: 7
* Explanation: [0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1] -> [3,1,2,2,2] -> [3,1,3,3,2] ->
*               [3,1,4,4,2] -> [3,1,5,4,2].
* */
public class MinimumIncrementsToFormTargetArray {

    private static int minimumIncrements(int[] target) {
        /*
        * Intent:
        * Think of the numbers like building heights and we are constructing layer by layer from scratch.
        * If last building built was bigger than the current building, then we don't need to do extra work.
        * If we want to build something even bigger, then we need to calculate how much extra work is needed.
        * Which we do by seeing what's the difference we need to make up for when compared to the building prior.
        * */
        int count =target[0], n= target.length;
        for(int i=1; i<n; i++) {
            if(target[i] > target[i-1])
                count += target[i] - target[i-1];
        }
        return count;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Minimum increments to form target array: "+minimumIncrements(new int[]{1,2,3,2,1}));
        System.out.println("Minimum increments to form target array: "+minimumIncrements(new int[]{3,1,1,2}));
        System.out.println("Minimum increments to form target array: "+minimumIncrements(new int[]{3,1,5,4,2}));
    }
}
