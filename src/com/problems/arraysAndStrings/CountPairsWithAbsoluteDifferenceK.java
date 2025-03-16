package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

/*
* Question:
* Given an integer array nums and an integer k, return the number of pairs (i, j)
* where i < j such that |nums[i] - nums[j]| == k.
* The value of |x| is defined as:
* x if x >= 0.
* -x if x < 0.
* */
public class CountPairsWithAbsoluteDifferenceK {
    private static int countPairsBruteForceSolution(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                if(Math.abs(arr[i]-arr[j]) == k)
                    count++;
            }
        }
        return count;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int countPairsUsingHashMap(int[] arr, int k) {
        /*
         * Intent:
         * add every element to map with no of occurrences
         * count no of k+num and num-k
         * */
        int count = 0;
        Map<Integer,Integer> map = new HashMap<>();

        for(int num : arr){
            map.put(num,map.getOrDefault(num,0)+1);
            count += map.getOrDefault(num+k,0) + map.getOrDefault(num-k,0);
        }
        return count;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(n)
         * */
    }

    public static void main(String[] args) {
        //Brute force solution - o(n^2)
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[]{1,2,2,1},1));
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[]{1,3},3));
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[] {3,2,1,5,4}, 2));

        //Solution using sorting - o( n)
        System.out.println("\nNo of pairs: "+countPairsUsingHashMap(new int[]{1,2,2,1},1));
        System.out.println("No of pairs: "+countPairsUsingHashMap(new int[]{1,3},3));
        System.out.println("No of pairs: "+countPairsUsingHashMap(new int[] {3,2,1,5,4}, 2));
    }
}
