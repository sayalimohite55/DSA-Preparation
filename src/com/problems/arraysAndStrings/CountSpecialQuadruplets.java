package com.problems.arraysAndStrings;

import java.util.*;

/*
* Question:
* Given a 0-indexed integer array nums, return the number of distinct quadruplets (a, b, c, d) such that:
* nums[a] + nums[b] + nums[c] == nums[d], and a < b < c < d
*/
public class CountSpecialQuadruplets {
    private static int bruteForceSolution(int[] arr) {
        int count=0, n=arr.length;
        for(int i=0;i<n; i++) {
            for(int j=i+1;j<n;j++) {
                for(int k = j+1; k<n; k++) {
                    for(int l =k+1; l<n; l++) {
                        if(arr[i]+arr[j]+arr[k] == arr[l])
                            count++;
                    }
                }
            }
        }
        return count;
        /*
        * Time Complexity = o(n^4)
        * Space Complexity = o(1)
        * */
    }

    private static int optimisedSolution(int[] arr) {
        /*
        * Given condition: arr[a] + arr[b] + arr[c] = arr[d]
        *               => arr[a] + arr[b] = arr[d] - arr[c] such that a<b<c<d
        *
        * Store sum of each pair in array such that i < j and to that sum as key, add value j
        * For each i, j where i<j<n, check if map has arr[j] - arr[i]
        * */
        int count=0, n=arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0;i<n; i++) {
            for(int j=i+1;j<n;j++) {
                map.computeIfAbsent(arr[i]+arr[j], k->new ArrayList<>()).add(j);
            }
        }

        for(int i=0;i<n; i++) {
            for(int j=i+1;j<n;j++) {
                int rhs =  arr[j]-arr[i];
                if(map.containsKey(rhs)) {
                    List<Integer> list = map.get(rhs);
                    for(int index : list) {
                        if(index < i)
                            count ++;
                    }
                }
            }
        }
        return count;
        /*
         * Time Complexity = o(n^2)
         * Space Complexity = o(n)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^4)
        System.out.println("Count: "+ bruteForceSolution(new int[]{1,2,3,6}));
        System.out.println("Count: "+ bruteForceSolution(new int[]{3,3,6,4,5}));
        System.out.println("Count: "+ bruteForceSolution(new int[]{1,1,1,3,5}));

        //Optimised Solution - o(n^2)
        System.out.println("\nCount: "+ optimisedSolution(new int[]{1,2,3,6}));
        System.out.println("Count: "+ optimisedSolution(new int[]{3,3,6,4,5}));
        System.out.println("Count: "+ optimisedSolution(new int[]{1,1,1,3,5}));
    }
}
