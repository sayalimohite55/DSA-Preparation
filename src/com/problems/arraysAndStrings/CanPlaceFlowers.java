package com.problems.arraysAndStrings;

/*
* Question:
* You have a long flowerbed in which some of the plots are planted, and some are not.
* However, flowers cannot be planted in adjacent plots.
* Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1
* means not empty, and an integer n, return true if n new flowers can be planted in the
* flowerbed without violating the no-adjacent-flowers rule and false otherwise.
*
* Examples:
*
* Input: flowerbed = [1,0,0,0,1], n = 1      Output: true
* Input: flowerbed = [1,0,0,0,1], n = 2      Output: false
*
* Constraints:
* 1 <= flowerbed.length <= 2 * 104
* flowerbed[i] is 0 or 1.
* There are no two adjacent flowers in flowerbed.
* 0 <= n <= flowerbed.length
* */
public class CanPlaceFlowers {

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for(int i =0;i<flowerbed.length; i++) {
            if(flowerbed[i] == 0) {
                boolean leftPlot = (i==0) || (flowerbed[i-1] == 0);
                boolean rightPlot = (i == flowerbed.length-1) || (flowerbed[i+1] == 0);

                if(leftPlot && rightPlot) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }
        return count >= n;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Result: " + canPlaceFlowers(new int[]{1,0,0,0,1},1));
        System.out.println("Result: " + canPlaceFlowers(new int[]{1,0,0,0,1},2));
        System.out.println("Result: " + canPlaceFlowers(new int[]{1,0,0,0,1,0,0},2));
        System.out.println("Result: " + canPlaceFlowers(new int[]{1,0},1));
        System.out.println("Result: " + canPlaceFlowers(new int[]{0,1,0},1));
    }
}
