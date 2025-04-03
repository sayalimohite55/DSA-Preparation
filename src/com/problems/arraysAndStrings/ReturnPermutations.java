package com.problems.arraysAndStrings;

import java.util.ArrayList;
import java.util.List;

/*
* Question:
* Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
* Examples:
*
* Input: nums = [1,2,3]
* Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
*
* Input: nums = [0,1]
* Output: [[0,1],[1,0]]
*
* Input: nums = [1]
* Output: [[1]]
* */
public class ReturnPermutations {
    private static List<List<Integer>> getAllPermuatations(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;
        backtrack(new ArrayList<Integer>(n), result, arr, n);
        return result;
        /*
        * Time Complexity = o(n * n!)
        * Space Complexity = o(n)
        * */
    }

    private static void backtrack(List<Integer> currentList, List<List<Integer>> result, int[] arr, int n) {
        if(currentList.size() == n) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        for(int num: arr) {
            if(!currentList.contains(num)) {
                currentList.add(num);
                backtrack(currentList, result, arr, n);
                currentList.removeLast();
            }
        }
    }

    public static void main(String[] args){
        //Solution using backtracking
        System.out.println("Result: "+getAllPermuatations(new int[]{1,2,3}));
        System.out.println("Result: "+getAllPermuatations(new int[]{0,1}));
        System.out.println("Result: "+getAllPermuatations(new int[]{1}));
    }
}
