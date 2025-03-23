package com.problems.arraysAndStrings;

import java.util.*;

/*
* Question: KSum II
* Given four integer arrays nums1, nums2, nums3, and nums4 all of length n,
* return the number of tuples (i, j, k, l) such that:
* 0 <= i, j, k, l < n
* nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
* */
public class FourSumII {

    private static int kSumBruteForceSolution(int[] a, int[] b, int[] c, int[] d) {
        int count = 0, n = a.length;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                for(int k=0;k<n;k++) {
                    for(int l=0; l<n; l++){
                        if(a[i]+b[j]+c[k]+d[l] == 0)
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

    private static int kSumOptimisedSolution(int[] a, int[] b, int[] c, int[] d) {
        /*
        * Intent:
        * Condition:    a[i] + b[j] + c[k] + d[l] =0
        *           =>  a[i] + b[j] = -1 * ( c[k] + d[l] )
        * */
        int count = 0, n = a.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                int sum = a[i]+b[j];
                map.put(sum, map.getOrDefault(sum,0)+1);
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                int sum = -1 * (c[i]+d[j]);
                if(map.containsKey(sum))
                    count += map.get(sum);
            }
        }

        return count;
        /*
         * Time Complexity = o(n^2)
         * Space Complexity = o(n)
         * */
    }

    private static int kSumOptimisedSolutionUsingVar(int[] a, int[] b, int[] c, int[] d) {
        /*
         * Intent:
         * Condition:    a[i] + b[j] + c[k] + d[l] =0
         *           =>  a[i] + b[j] = -1 * ( c[k] + d[l] )
         * */
        int count = 0, n = a.length;
        Map<Integer, Integer> map = new HashMap<>(n*n);
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                int sum = a[i]+b[j];
                map.merge(sum,1, Integer::sum);
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                count += map.getOrDefault(-1 * (c[i] + d[j]), 0);
            }
        }

        return count;
        /*
         * Time Complexity = o(n^2)
         * Space Complexity = o(n)
         * */
    }

    public static void main(String[] args) {
        int[] a = new int[]{-80,92,66,-48,-37,-35,84,54,77,-34,-30,33,70,17,46,-72,-64,-80,-6,9,55,-71,-27,
                -89,-60,-67, -80,34,0,-15,56,-2,75,67,-88,30,-38,-41,55,7,-73,79,83,-30,-16,8,84,-70,4,-72,
                55,-99,-5,19,88, 70,66,65,51,-78,18,57,-8,61,-38,91,19,-30,-94,-23,54,-73,-23,-35,96,80,-69,
                38,39,-53,-75,-62, -88,10,33,76,-22,-48,99,83,41,45,-88,-41,17,-1,86,39,-99,61,-11,68,-53,-3,
                63,-82,-15,14,-64,49, -4,16,-14,17,22,62,74,-71,11,-69,58,17,90,37,55,53,45,57,-63,47,53,83,
                -16,-46,92};

        int[] b = new int[]{-86,66,-16,-93,-7,46,57,-23,-74,-35,28,-48,-85,7,-6,15,-41,72,-27,99,-39,-88,
                93, -67, 14,24,29, 36,62,4,20,-51,-77,61,-81,8,-58,43,-40,52,40,42,15,68,89,61,12,-44,14,
                -26,-87,74,17,-24,41,100, -48,-95,87,-1,53,60,41,47,14,-6,91,-84,68,4,87,64,9,90,-36,-71,
                -24,51,46,15,12,-54,-35,91,81,86, -74,-73,53,-46,66,-67,7,74,64,-1,80,14,50,85,-54,-84,-56,
                -93,30,61,47,69,-34,-37,-48,36,24,93, -39,75,95,18,-98,56,24,-45,76,-60,50,-1,68,33,-39,-23,
                3,14,70,62,60};

        int[] c = new int[]{40,36,46,-84,86,-55,79,-56,49,-7,40,-97,-20,80,-47,-58,-68,-76,64,-89,-40,49,91,80,
                -86,35,-23, 86,-16,91,94,58,-92,34,-54,25,-68,-64,-88,-77,62,-28,10,-68,44,17,48,-98,-20,73,-64,
                10,94,71,22, -63,-70,-3,43,30,14,48,85,-9,77,37,-24,84,-20,70,-26,7,80,28,84,-35,-95,-15,-96,63,
                67,-21,-10,43, 48,-17,-30,-78,90,24,60,-7,49,29,2,-52,-4,-16,42,31,-92,55,52,-50,96,34,-57,80,
                -88,-56,-84,-63, -41,12,-31,-28,74,-3,37,70,-63,-42,-94,79,33,-25,-11,-22,-8,71,56,82,14,77,81};

        int[] d = new int[]{-66,-62,81,100,-40,51,77,-100,88,-81,17,-78,45,0,-33,74,21,-74,44,-27,24,56,7,38,41,
                61,97,67, 8,2,-46,-91,41,76,-75,83,7,27,83,-47,-42,-19,-75,-38,-89,-16,-40,-56,-76,15,81,-42,0,
                99,7,65,30, -40,-27,74,93,-60,-2,-21,75,-55,58,-11,34,92,-11,-88,47,88,-56,-1,46,36,92,-59,73,4,
                54,-87,-70, -93,-57,92,96,-79,89,63,-27,94,-59,82,-48,-27,-58,78,-40,-88,21,-54,54,-83,-3,54,97,
                -86,-39,-99, 11,62,18,-18,-100,-38,8,24,-86,-80,30,25,-67,-96,-64,-4,-59,-79,-89,86,-39,-8,69};

        //Brute Force Solution - o(n^4)
        System.out.println("Count : "+kSumBruteForceSolution(
                new int[]{1,2}, new int[]{-2,-1}, new int[]{-1,2}, new int[]{0,2}));
        System.out.println("Count : "+kSumBruteForceSolution(
                new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}));
        System.out.println("Count : "+kSumBruteForceSolution(a,b,c,d));

        //Optimised Solution - o(n^2)
        System.out.println("\nCount : "+kSumOptimisedSolution(
                new int[]{1,2}, new int[]{-2,-1}, new int[]{-1,2}, new int[]{0,2}));
        System.out.println("Count : "+kSumOptimisedSolution(
                new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}));
        System.out.println("Count : "+kSumOptimisedSolution(
                a,b,c,d));

        //Better Optimised Solution using var keyword and :: operator and sum function introduced in Java 10- o(n^2)
        System.out.println("\nCount : "+kSumOptimisedSolutionUsingVar(
                new int[]{1,2}, new int[]{-2,-1}, new int[]{-1,2}, new int[]{0,2}));
        System.out.println("Count : "+kSumOptimisedSolutionUsingVar(
                new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}));
        System.out.println("Count : "+kSumOptimisedSolutionUsingVar(a,b,c,d));
    }
}
