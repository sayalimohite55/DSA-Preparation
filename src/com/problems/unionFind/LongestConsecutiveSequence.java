package com.problems.unionFind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
* Question
* Given an unsorted array of integers nums,
* return the length of the longest consecutive elements sequence.
* You must write an algorithm that runs in O(n) time.
*
* Constraints:
* 0 <= nums.length <= 105
* -109 <= nums[i] <= 109
*
* Input: nums = [100,4,200,1,3,2]
* Output: 4
* Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
* */
public class LongestConsecutiveSequence {

    private static int bruteForceSolution(int[] arr) {
        /*
        * Intent:
        * - Array is not sorted, hence we have to just look for current Element + 1 in rest of array
        * - and see if number exists in the array
        * */
        int maxCount = 0, i=0;
        for (int num : arr) {
            int count = 1;
            int currentNum = num+1;
            while(numberExists(arr,currentNum)) {
                count++;
                currentNum++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
        /*
        * Time Complexity = o(n^3)
        * Space Complexity = o(1)
        * */
    }

    private static boolean numberExists(int[] arr, int num) {
        for(int n: arr) {
            if(n == num)
                return true;
        }
        return false;
    }

    private static int solutionUsingSorting(int[] arr) {
        /*
         * Intent:
         * - Sort the array
         * - and check for consecutive sequence
         * */
        Arrays.sort(arr);
        int maxCount = 0, count = 1;
        for(int i=0; i<arr.length-1; i++) {
            if(arr[i]+1 == arr[i+1]) {
                count++;
            } else {
                maxCount = Math.max(maxCount,count);
                count = 1;
            }
        }
        return maxCount;
        /*
         * Time Complexity = o(n log n) + o(n) = o(n log n)
         * Space Complexity = o(log n)
         * */
    }

    private static int solutionUsingUnionFind(int[] arr) {
        /*
         * Intent:
         * - It turns out that our initial brute force solution was on the right track, but missing a
         *   few optimizations necessary to reach O(n) time complexity.
         * Approach :
         * - The numbers are stored in a HashSet to allow O(1) lookups, and we only attempt to build sequences
         *   from numbers that are not already part of a longer sequence.
         * - This is accomplished by first ensuring that the number that would immediately precede the current
         *   number in a sequence is not present, as that number would necessarily be part of a longer sequence
         * */
        int maxCount = 1;
        Set<Integer> set = new HashSet<Integer>();
        for (int num : arr) {
            set.add(num);
        }

        for(int num: set) {
            if(!set.contains(num-1)) {
                int count =1;
                int currentNum = num;
                while(set.contains(currentNum+1)) {
                    count++;
                    currentNum++;
                }
                maxCount = Math.max(maxCount,count);
            }
        }
        return maxCount;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(n)
         * */
    }

    public static void main(String[] args) {
        int[] arr = new int[]{100,4,200,1,3,2};

        //Brute Force Solution - o(n^3)
        System.out.println("Longest Consecutive Sequence: "+bruteForceSolution(arr));

        //Optimised solution - o(n log n)
        System.out.println("Longest Consecutive Sequence: "+solutionUsingSorting(arr));

        //Optimised solution using extra space- o(n)
        System.out.println("Longest Consecutive Sequence: "+solutionUsingUnionFind(arr));
    }
}
