package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

/*
* Question:
* A small frog wants to get to the other side of a river. The frog is initially located on one
* bank of the river (position 0) and wants to get to the opposite bank (position X+1). Leaves
* fall from a tree onto the surface of the river. You are given an array A consisting of N
* integers representing the falling leaves. A[K] represents the position where one leaf falls
* at time K, measured in seconds. The goal is to find the earliest time when the frog can jump
* to the other side of the river. The frog can cross only when leaves appear at every position
* across the river from 1 to X (that is, we want to find the earliest moment when all the positions
* from 1 to X are covered by leaves). You may assume that the speed of the current in the river is
* negligibly small, i.e. the leaves do not change their positions once they fall in the river.
* For example, you are given integer X = 5 and array A such that:
  A[0] = 1
  A[1] = 3
  A[2] = 1
  A[3] = 4
  A[4] = 2
  A[5] = 3
  A[6] = 5
  A[7] = 4
* In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every
* position across the river.
* */
public class FrogRiverOne {
    private static int earliestTimeLeavesAppear_BruteForce(int x, int[] arr) {
        int minTime = -1, n = arr.length;
        for(int i=1; i<=x; i++) {
            boolean flag = false;
            for(int j =0;j<n; j++) {
                if(i == arr[j]) {
                    minTime = Math.max(minTime, j);
                    flag = true;
                    break;
                }
            }
            if(!flag)
                return -1;
        }
        return minTime;
        /*
        * Time Complexity = o(xn)
        * Space Complexity = o(1)
        * */
    }

    private static int earliestTimeLeavesAppear_Optimised(int x, int[] arr) {
        int minTime = -1, n = arr.length;
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<n; i++) {
            if(!map.containsKey(arr[i]))
                map.put(arr[i],i);
        }

        for(int i=1; i<=x; i++) {
            if(map.containsKey(i))
                minTime = Math.max(minTime, map.get(i));
            else
                return -1;
        }

        return minTime;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(n)
         * */
    }

    public static void main(String[] args) {
        //Brute force Solution - o(xn)
        System.out.println("Time: "+earliestTimeLeavesAppear_BruteForce(5, new int[]{1,3,1,4,2,3,5,4}));
        System.out.println("Time: "+earliestTimeLeavesAppear_BruteForce(5, new int[]{1,3,1,1,2,3,5,3,4}));
        System.out.println("Time: "+earliestTimeLeavesAppear_BruteForce(5, new int[]{1,3,1,1,3,5,3,4}));

        //Brute force Solution - o(n)
        System.out.println("\nTime: "+earliestTimeLeavesAppear_Optimised(5, new int[]{1,3,1,4,2,3,5,4}));
        System.out.println("Time: "+earliestTimeLeavesAppear_Optimised(5, new int[]{1,3,1,1,2,3,5,3,4}));
        System.out.println("Time: "+earliestTimeLeavesAppear_Optimised(5, new int[]{1,3,1,1,3,5,3,4}));
    }
}
