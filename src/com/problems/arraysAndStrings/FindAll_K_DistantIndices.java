package com.problems.arraysAndStrings;

import java.util.Arrays;

/*
* Question:
* You are given a 0-indexed integer array nums and two integers key and k.
* A k-distant index is an index i of nums for which there exists at least one index j
* such that |i - j| <= k and nums[j] == key.
* Return a list of all k-distant indices sorted in increasing order.
*
* eg. :
* Input: nums = [3,4,9,1,3,9,5], key = 9, k = 1
* Output: [1,2,3,4,5,6]
* Explanation: Here, nums[2] == key and nums[5] == key.
* - For index 0, |0 - 2| > k and |0 - 5| > k, so there is no j where |0 - j| <= k and nums[j] == key.
*   Thus, 0 is not a k-distant index.
* - For index 1, |1 - 2| <= k and nums[2] == key, so 1 is a k-distant index.
* - For index 2, |2 - 2| <= k and nums[2] == key, so 2 is a k-distant index.
* - For index 3, |3 - 2| <= k and nums[2] == key, so 3 is a k-distant index.
* - For index 4, |4 - 5| <= k and nums[5] == key, so 4 is a k-distant index.
* - For index 5, |5 - 5| <= k and nums[5] == key, so 5 is a k-distant index.
* - For index 6, |6 - 5| <= k and nums[5] == key, so 6 is a k-distant index.
*   Thus, we return [1,2,3,4,5,6] which is sorted in increasing order.
* */

public class FindAll_K_DistantIndices {
    private static int countPairsBruteForceSolution(int[] arr, int target) {
        int n = arr.length, count = 0;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                if(arr[i]+arr[j] < target)
                    count++;
            }
        }
        return count;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int countPairsUsingSorting(int[] arr, int target) {
        /*
         * Intent:
         * Sort the array and then check pairs using two pointers technique
         * */
        int n = arr.length, count = 0;
        Arrays.sort(arr);
        int i=0, j=n-1;
        while(i < j) {
            if(arr[i]+arr[j] < target) {
                count+= j-i;
                i++;
            } else {
                j--;
            }
        }
        return count;
        /*
         * Time Complexity = o(n log n + n) = o(n log n)
         * Space Complexity = o(log n) or o(n)
         * */
    }

    public static void main(String[] args) {
        //Brute force solution - o(n^2)
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[]{-1,1,2,3,1},2));
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[]{-6,2,5,-2,-7,-1,3},-2));

        //Solution using sorting - o(n log n)
        System.out.println("No of pairs: "+countPairsUsingSorting(new int[]{-1,1,2,3,1},2));
        System.out.println("No of pairs: "+countPairsUsingSorting(new int[]{-6,2,5,-2,-7,-1,3}, -2));
    }
}
