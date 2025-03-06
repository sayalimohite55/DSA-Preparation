package com.problems.dp;

import java.util.Arrays;
import java.util.HashMap;

/*
* Question:
* A frog is crossing a river.
* The river is divided into some number of units, and at each unit, there may or may not exist a stone.
* The frog can jump on a stone, but it must not jump into the water.
*
* Given a list of stones positions (in units) in sorted ascending order,
* determine if the frog can cross the river by landing on the last stone.
* Initially, the frog is on the first stone and assumes the first jump must be 1 unit.
*
* If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units.
* The frog can only jump in the forward direction.
*
* eg:
* Input: stones = [0,1,3,5,6,8,12,17]
* Output: true
Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
* */
public class FrogJump {

    private static boolean canJumpRecursive(int[] stones) {
        int n= stones.length;

        //Add all stones to a map for easy access checking which all stones exists & to access their indexes
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n; i++) {
            map.put(stones[i],i);
        }

        //Create a memoize array to remember previous calculations
        int[][] dp = new int[n+1][n+1];
        for(int i=0;i<n;i++) {
            Arrays.fill(dp[i], -1);
        }
        //Starting with zero th index
        return canJump(stones, n, map, dp, 0, 0);
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(n^2)
        * */
    }

    private static boolean canJump(int[] stones,int n, HashMap<Integer, Integer> map,int[][] dp,int index, int prevJump) {
        //Output case
        if(index == n-1)
            return true;

        //If a jump was verified before, return result
        if(dp[index][prevJump] != -1)
            return ( dp[index][prevJump] == 1); //1 indicates true

        boolean flag = false;
        //Iterate over possibilities k-1, k, k+1 to check if a frog can jump
        for(int nextJump=prevJump-1; nextJump <= prevJump+1 ; nextJump++) {
            //check if next jump exists
            if(nextJump > 0 && map.containsKey(stones[index]+nextJump)) {
                flag = flag || canJump(stones, n, map, dp, map.get(stones[index]+nextJump), nextJump);
            }
        }
        dp[index][prevJump] = flag ? 1 : 0;
        return flag;
    }

    private static boolean canJumpIterative(int[] stones) {
        int n= stones.length;

        //Add all stones to a map for easy access checking which all stones exists & to access their indexes
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n; i++) {
            map.put(stones[i],i);
        }

        //Create a memoize array to remember previous calculations
        boolean[][] dp = new boolean[n+1][n+1];

        dp[0][0] = true;
        //Let's explore possibilities k-1, k, k+1
        for(int i=0;i<n;i++) {
            for(int prevJump=0; prevJump <= n ; prevJump ++) {
                if(dp[i][prevJump]) {
                    // If stone exists, mark the value with position and jump as 1.
                    if(map.containsKey(stones[i]+prevJump)) {
                        dp[map.get(stones[i]+prevJump)][prevJump] = true;
                    }
                    if(map.containsKey(stones[i]+prevJump+1)) {
                        dp[map.get(stones[i]+prevJump+1)][prevJump+1] = true;
                    }
                    if(map.containsKey(stones[i]+prevJump-1)) {
                        dp[map.get(stones[i]+prevJump-1)][prevJump-1] = true;
                    }
                }
            }
        }
        // If any value with index as n - 1 is true, return true.
        for (int index = 0; index <= n; index++) {
            if (dp[n - 1][index]) {
                return true;
            }
        }
        return false;
        /*
         * Time Complexity = o(n^2)
         * Space Complexity = o(n^2)
         * */
    }

    public static void main(String[] args){

        //Recursive Solution using DP (Top Down) - o(n^2)
        System.out.println("Can Jump using recursive solution: " + canJumpRecursive(new int[]{0,1,3,5,6,8,12,17}));
        System.out.println("Can Jump using recursive solution: " + canJumpRecursive(new int[]{0,1,2,3,4,8,9,11}));

        //Iterative Solution using DP (Bottom Up) - o(n^2)
        System.out.println("\nCan Jump using iterative solution: " + canJumpIterative(new int[]{0,1,3,5,6,8,12,17}));
        System.out.println("Can Jump using iterative solution: " + canJumpIterative(new int[]{0,1,2,3,4,8,9,11}));
    }
}
