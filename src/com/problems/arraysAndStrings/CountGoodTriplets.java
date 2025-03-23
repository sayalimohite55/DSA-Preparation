package com.problems.arraysAndStrings;

/*
* Question:
* Given an array of integers arr, and three integers a, b and c.
* You need to find the number of good triplets.
* A triplet (arr[i], arr[j], arr[k]) is good if the following conditions are true:
*   0 <= i < j < k < arr.length
*   |arr[i] - arr[j]| <= a
*   |arr[j] - arr[k]| <= b
*   |arr[i] - arr[k]| <= c
*   Where |x| denotes the absolute value of x.
* Return the number of good triplets.
* */
public class CountGoodTriplets {

    private static int countGoodTriplets1(int[] arr, int a, int b, int c) {
        int count = 0 , n = arr.length;
        for(int i=0; i<n ; i++) {
            for(int j= i+1; j<n; j++) {
                for(int k = j+1; k<n; k++) {
                    if(Math.abs(arr[i]- arr[j]) <= a
                            && Math.abs(arr[j]-arr[k]) <= b
                            && Math.abs(arr[i]-arr[k]) <= c)
                        count++;
                }
            }
        }
        return count;
        /*
        * Time Complexity = o(n^3)
        * Space Complexity = o(1)
        * */
    }

    private static int countGoodTriplets2(int[] arr, int a, int b, int c) {
        int count = 0 , n = arr.length;
        for(int i=0; i<n ; i++) {
            for(int j= i+1; j<n; j++) {
                if(Math.abs(arr[i]- arr[j]) <= a)
                    for(int k = j+1; k<n; k++) {
                        if( Math.abs(arr[j]-arr[k]) <= b && Math.abs(arr[i]-arr[k]) <= c)
                            count++;
                    }
            }
        }
        return count;
        /*
         * Time Complexity = o(n^3)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^3)
        System.out.println("Result: "+ countGoodTriplets1(new int[]{3,0,1,1,9,7},7,2,3));
        System.out.println("Result: "+ countGoodTriplets1(new int[]{1,1,2,2,3},0,0,1));

        //Slightly optimised Solution - o(n^3)
        System.out.println("\nResult: "+ countGoodTriplets2(new int[]{3,0,1,1,9,7},7,2,3));
        System.out.println("Result: "+ countGoodTriplets2(new int[]{1,1,2,2,3},0,0,1));
    }
}
