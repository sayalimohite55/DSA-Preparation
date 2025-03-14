package com.problems.arraysAndStrings;

import java.util.Arrays;

/*
* Question:
* Given a 0-indexed integer array nums of size n and two integers lower and upper,
* return the number of fair pairs.
* A pair (i, j) is fair if:
*   0 <= i < j < n, and lower <= nums[i] + nums[j] <= upper
* */
public class CountFairPairs {
    private static long countPairsBruteForceSolution(int[] arr, int lower, int upper) {
        int n = arr.length;
        long count = 0;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                long sum = arr[i]+arr[j];
                if(lower <= sum && sum <= upper)
                    count++;
            }
        }
        return count;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static long countPairsUsingSorting(int[] arr, int lower, int upper) {
        /*
         * Intent:
         * Sort the array and then check pairs using two pointers technique
         * */
        Arrays.sort(arr);
        return countPairsUsingSorting(arr,upper+1) - countPairsUsingSorting(arr, lower);
        /*
         * Time Complexity = o(n log n + n) = o(n log n)
         * Space Complexity = o(log n) or o(n)
         * */
    }

    private static long countPairsUsingSorting(int[] arr, int target) {
        long count = 0;
        int left =0, right = arr.length-1;
        while(left < right) {
            if(arr[left] + arr[right] < target) {
                count += right-left;
                left ++;
            } else
                right --;
        }
        return count;
    }

    public static void main(String[] args) {
        //Brute force solution - o(n^2)
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[]{0,1,7,4,4,5},3,6));
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[]{1,7,9,2,5},11,11));
        System.out.println("No of pairs: "+countPairsBruteForceSolution(new int[] {
                        33474,3048,55017,6445,83617,84128,58441,57814,84786,86049,44898,26268,80222,20088,85422,
                        69658, 11264, 42707,98979,36518,7115,2473,98444,83343,9084,28974,35940,50626,4047,11429,
                        60825,52544,71549, 85788,97562,94781,61129,24256,84078,96506,1713,68689,66780,7402,84546,
                        35689,31439,52205,1321, 41952, 23572,16451,71888,83674,76816,45874,2212,71139,47026,88645,
                        19009,55405,41234,82222,29163,46798,74165, 42649,82599,46134,89325,73976,53183,44410,40942,
                        64278,88121,3954,47355,10702,81698,43581,93360,40330,99394,20687,49350,15629,48940,72284,
                        17968,45866,88278,78187,80855,33380,68991,81071,15804,39683,67032,43727,75606,58340,92997,
                        55975,58158,38131,69301,54036,79546,73636,10819,36638,89004,26712,91985,48672,17742,41609,
                        72820,4642,19266,73952,27189,75638,4568,77468,5690,52909,94007,43838,42236,62253,27479,
                        70209, 34080,1415,67084,39878,62862,47911,19736,61135,36684,45790,47994,91521,31400,50075,
                        31435,37407, 27527,14336,58645,10027,45809,49161,17459,67896,19729,30472,24326,45698,1823,
                        42018,53045,18046, 33310}, -1000000000,1000000000));

        //Solution using sorting - o(n log n)
        System.out.println("\nNo of pairs: "+countPairsUsingSorting(new int[]{0,1,7,4,4,5},3,6));
        System.out.println("No of pairs: "+countPairsUsingSorting(new int[]{1,7,9,2,5},11,11));
        System.out.println("No of pairs: "+countPairsUsingSorting(new int[] {
                33474,3048,55017,6445,83617,84128,58441,57814,84786,86049,44898,26268,80222,20088,85422,
                69658, 11264, 42707,98979,36518,7115,2473,98444,83343,9084,28974,35940,50626,4047,11429,
                60825,52544,71549, 85788,97562,94781,61129,24256,84078,96506,1713,68689,66780,7402,84546,
                35689,31439,52205,1321, 41952, 23572,16451,71888,83674,76816,45874,2212,71139,47026,88645,
                19009,55405,41234,82222,29163,46798,74165, 42649,82599,46134,89325,73976,53183,44410,40942,
                64278,88121,3954,47355,10702,81698,43581,93360,40330,99394,20687,49350,15629,48940,72284,
                17968,45866,88278,78187,80855,33380,68991,81071,15804,39683,67032,43727,75606,58340,92997,
                55975,58158,38131,69301,54036,79546,73636,10819,36638,89004,26712,91985,48672,17742,41609,
                72820,4642,19266,73952,27189,75638,4568,77468,5690,52909,94007,43838,42236,62253,27479,
                70209, 34080,1415,67084,39878,62862,47911,19736,61135,36684,45790,47994,91521,31400,50075,
                31435,37407, 27527,14336,58645,10027,45809,49161,17459,67896,19729,30472,24326,45698,1823,
                42018,53045,18046, 33310}, -1000000000,1000000000));
    }
}
