package com.problems.arraysAndStrings;

import java.util.*;

/*
* Question:
* Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
* A k-diff pair is an integer pair (nums[i], nums[j]),
* where the following are true:
* 0 <= i, j < nums.length
* i != j
* |nums[i] - nums[j]| == k
* Notice that |val| denotes the absolute value of val
* */
public class CountUniquePairsWithDifferenceK {
    private static int countPairsBruteForceSolution(int[] arr, int k) {
        /*
        * Intent:
        * 1. Sort the array
        * 2. Iterate through each pair to check if difference is k
        * */
        int n = arr.length;
        int count = 0;
        Arrays.sort(arr);
        for(int i=0; i<n; i++) {
            if(i>0 && arr[i] == arr[i-1])
                continue;

            for(int j=i+1; j<n; j++) {
                if(j>i+1 && arr[j] == arr[j-1])
                    continue;
                if(Math.abs(arr[j]-arr[i]) == k)
                    count++;
            }
        }
        return count;
        /*
        * Time Complexity = o(n^2 + n log n) = o(n^2)
        * Space Complexity = o(n)
        * */
    }

    private static int countPairsUsingTwoPointers(int[] arr, int k) {
        /*
         * Intent:
         * 1. sort the array
         * 2. Use two pointers technique to check id pair difference is k
         * */
        int count = 0;
        Arrays.sort(arr);
        int i=0, j=1, n= arr.length;
        while(i<n && j<n) {
            if(i == j || arr[j] - arr[i] < k)
                j++;
            else if(arr[j] - arr[i] > k)
                i++;
            else {
                count++;
                i++;
                while(i<n && arr[i] == arr[i-1])
                    i++;
            }
        }
        return count;
        /*
         * Time Complexity = o(n log n)
         * Space Complexity = o(n)
         * */
    }

    private static int countPairsUsingHashMap(int[] arr, int k) {
        /*
         * Intent:
         * 1. sort all array elements into hash map
         * 2. check for k-num pair and increment the counter
         * */
        int count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int num: arr)
            map.put(num, map.getOrDefault(num,0)+1);

        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue(); //count of occurrences
            if(k>0 && map.containsKey(key+k))
                count++;
            else if(k==0 && val > 1)
                count++;
        }
        return count;
        /*
         * Time Complexity = o(n + n) = o(n)
         * Space Complexity = o(n)
         * */
    }

    public static void main(String[] args) {
        //Brute force solution - o(n^2)
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[]{3,1,4,1,5},2));
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[]{1,2,3,4,5},1));
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[] {1,3,1,5,4}, 0));

        //Solution using sorting - o(n log n)
        System.out.println("\nNo of pairs: "+countPairsUsingTwoPointers(new int[]{3,1,4,1,5},2));
        System.out.println("No of pairs: "+countPairsUsingTwoPointers(new int[]{1,2,3,4,5},1));
        System.out.println("No of pairs: "+countPairsUsingTwoPointers(new int[] {1,3,1,5,4}, 0));

        //Solution using extra space - o(n)
        System.out.println("\nNo of pairs: "+countPairsUsingHashMap(new int[]{3,1,4,1,5},2));
        System.out.println("No of pairs: "+countPairsUsingHashMap(new int[]{1,2,3,4,5},1));
        System.out.println("No of pairs: "+countPairsUsingHashMap(new int[] {1,3,1,5,4}, 0));
    }
}
