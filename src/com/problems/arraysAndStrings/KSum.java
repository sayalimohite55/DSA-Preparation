package com.problems.arraysAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Question: KSum
* Given an array nums of n integers, return an array of all the unique k tuples
* eg. quadruples [nums[a], nums[b], nums[c], nums[d]]
* such that: 0 <= a, b, c, d < n and a, b, c, and d are distinct.
* nums[a] + nums[b] + nums[c] + nums[d] == target
* */
public class KSum {

    private static List<List<Integer>> kSum(int[] arr, int target, int k) {
        /*
        * Intent:
        * Break the problem statement.
        * If its 3 sum => 1 + 2 sum
        * If its 4 sum => 1 + 3 sum => 1 + 1 + 2 sum
        * If its 5 sum => 1 + 4 Sum => 1 + 1 + 3 sum => 1 + 1 + 1 + 2 sum
        * */
        Arrays.sort(arr);
        return kSum(arr, target, 0, k);
        /*
        * Time Complexity = o(n^k-1)
        * Space Complexity = o(n)
        * */
    }

    private static List<List<Integer>> kSum(int[] arr, int target, int start, int k) {
        List<List<Integer>> result = new ArrayList<>();

        if(start == arr.length)
            return result;

        int average_target = target/k;
        if(arr[start] > average_target || average_target > arr[arr.length-1])
            return result;

        if(k == 2)
            return twoSum(arr, target, start);

        for(int i = start; i<arr.length-1; i++) {
            if(i==start || arr[i] != arr[i-1]) {
                for(List<Integer> list : kSum(arr, target-arr[i], i+1, k-1)) {
                    result.add(new ArrayList<>(Arrays.asList(arr[i])));
                    result.get(result.size()-1).addAll(list);
                }
            }
        }
        return result;
    }

    private static List<List<Integer>> twoSum(int[] arr, int target, int start) {
        List<List<Integer>> result = new ArrayList<>();

        int low = start, high = arr.length-1;
        while( low < high ) {
            int sum = arr[low] + arr[high];
            if(sum < target || (low > start && arr[low] == arr[low-1])) {
                low++;
            } else if (sum > target || (high < arr.length-1 && arr[high] == arr[high+1]))
            {
                high --;
            }else {
                result.add(Arrays.asList(arr[low++],arr[high--]));
            }
        }
        return result;
    }

    private static void printOutput(List<List<Integer>> kList) {
        for (List<Integer>  list: kList) {
            System.out.print("\n[");
            for(int val: list)
                System.out.print(" " + val);
            System.out.print("]");
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,0,-1,0,-2,2};
        int target = 0;

        List<List<Integer>> list = kSum(arr, target, 4);
        printOutput(list);
    }
}
