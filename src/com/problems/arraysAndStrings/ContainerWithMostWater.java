package com.problems.arraysAndStrings;

/*
* Question: Container With Most Water
* You are given an integer array height of length n.
* There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0)
* and (i, height[i]).
* Find two lines that together with the x-axis form a container,
* such that the container contains the most water.
* Return the maximum amount of water a container can store.
* Notice that you may not slant the container.
* */
public class ContainerWithMostWater {
    private static int getMaxAreaBruteForceSolution(int height[]) {
        /*
         * Intent : Identify area for every pair of elements and take maxArea
         * */

        int maxArea = 0;
        for(int i=0;i<height.length; i++) {
            for(int j=i+1;j<height.length; j++) {
                int width = j-i;
                maxArea = Math.max(maxArea, Math.min(height[i],height[j]) * width);
            }
        }
        return maxArea;
        /*
         * Time Complexity = o(n^2)
         * Space Complexity = o(1)
         * */
    }

    private static int getMaxAreaOptimised(int height[]) {
        /*
        * Intent : Identify area using two pinter technique for any given nodes and take maxArea
        * */
        int left = 0, right = height.length-1;
        int maxArea = 0;
        while(left < right) {
            int width = right - left;
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * width);
            if(height[left] <= height[right])
                left++;
            else
                right--;
        }
        return maxArea;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {

        //brute force solution - o(n^2)
        System.out.println("Max Area: "+ getMaxAreaBruteForceSolution(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println("Max Area: "+ getMaxAreaBruteForceSolution(new int[]{1,1,6,2,7,4,8,1,7}));

        //optimised solution - o(n)
        System.out.println("\nMax Area: "+ getMaxAreaOptimised(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println("Max Area: "+ getMaxAreaBruteForceSolution(new int[]{1,1,6,2,7,4,8,1,7}));
    }
}
