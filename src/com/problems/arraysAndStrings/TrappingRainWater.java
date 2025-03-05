package com.problems.arraysAndStrings;

/*
* Question:
* Given n non-negative integers representing an elevation map where the width of each bar is 1,
* compute how much water it can trap after raining.
* */
public class TrappingRainWater {
    private static int bruteForceSolution(int[] height) {
        /*
        * Intent:
        * 1. Iterate through complete array of heights
        * 2. For each height i, find max value on left
        * 3. For each height i, find max value on right
        * 4. take minimum of both values and subtract current value from it
        * */
        int maxWater = 0;
        for(int i=1; i<height.length-1; i++) {
            int lMax = 0, rMax = 0;

            //Identify max on left
            for(int j=i; j>=0; j--)
                if(lMax < height[j])
                    lMax = height[j];

            //Identify max on right
            for(int j=i; j<height.length; j++)
                if(rMax < height[j])
                    rMax = height[j];

            maxWater += Math.min(lMax,rMax) - height[i];
        }
        return Math.max(maxWater,0);
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int maxWaterTrappedUsingTwoPointers(int[] height) {
        /*
         * Intent:
         * 1. find maximum from left hand side and right hand side both
         * 2. At any given index, subtract the value from the max of left and right max
         *    to get amount of trapped water
         * */
        int maxWater = 0, lMax, rMax;
        int left = 0, right = height.length-1;

        lMax = height[left];
        rMax = height[right];

        while(left < right) {
            if(lMax <= rMax) {
                left++;
                lMax = Math.max(height[left], lMax);
                maxWater += lMax - height[left];
            } else {
                right--;
                rMax = Math.max(height[right], rMax);
                maxWater += rMax - height[right];
            }
        }
        return maxWater;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    private static int maxWaterTrappedUsingDP(int[] height) {
        /*
        * Intent: Use Dynamic Programming
        * 1. Maintain two arrays to identify max from left and right for every ith value
        * 2. At any given index, subtract the ith value from the max of left and right max
        *    to get amount of trapped water
        * */
        int maxWater =0;

        //Declare arrays to hold max information from both sides
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        leftMax[0] = height[0];
        for(int i=1; i<height.length; i++) {
            leftMax[i] = Math.max(leftMax[i-1],height[i]);
        }

        rightMax[height.length-1] = height[height.length-1];
        for(int i=height.length-2; i>=0; i--) {
            rightMax[i] = Math.max(rightMax[i+1],height[i]);
        }

        for(int i=1; i<height.length-1; i++) {
            maxWater += Math.min(leftMax[i],rightMax[i]) - height[i];
        }

        return maxWater;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        //brute force solution - o(n^2)
        System.out.println("Output: "+bruteForceSolution(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println("Output: "+bruteForceSolution(new int[]{4,2,0,3,2,5}));

        //Solution using DP (Extra Space)- o(n)
        System.out.println("\n\nOutput: "+maxWaterTrappedUsingDP(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println("Output: "+maxWaterTrappedUsingDP(new int[]{4,2,0,3,2,5}));

        //Optimised solution using Two pointers - o(n)
        System.out.println("\n\nOutput: "+maxWaterTrappedUsingTwoPointers(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println("Output: "+maxWaterTrappedUsingTwoPointers(new int[]{4,2,0,3,2,5}));
    }
}
