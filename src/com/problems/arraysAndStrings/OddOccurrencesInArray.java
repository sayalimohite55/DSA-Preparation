package com.problems.arraysAndStrings;

import java.util.HashSet;
import java.util.Set;

/*
* Question:
* A non-empty array A consisting of N integers is given.
* The array contains an odd number of elements, and each element of the array can be paired
* with another element that has the same value, except for one element that is left unpaired.
*
* For example, in array A such that:

  A[0] = 9  A[1] = 3  A[2] = 9
  A[3] = 3  A[4] = 9  A[5] = 7
  A[6] = 9
* the elements at indexes 0 and 2 have value 9,
* the elements at indexes 1 and 3 have value 3,
* the elements at indexes 4 and 6 have value 9,
* the element at index 5 has value 7 and is unpaired.
* */
public class OddOccurrencesInArray {
    private static int oddOccurrencesBruteForce(int[] arr) {
        int n= arr.length;
        boolean flag = false;
        for(int i=0;i<n ;i++) {
            flag = false;
            for(int j=0; j<n ; j++) {
                if( i!=j && arr[i] == arr[j]) {
                    flag = true;
                    break;
                }
            }
            if(!flag)
                return arr[i];
        }
        return -1;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int oddOccurrencesOptimised(int[] arr) {
        int n= arr.length;
        Set<Integer> set = new HashSet<>(n);

        for(int num: arr) {
            if(set.contains(num))
                set.remove(num);
            else
                set.add(num);
        }
        return set.iterator().next();
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(n)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^2)
        System.out.println("Result: "+oddOccurrencesBruteForce(new int[]{9,3,9,3,9,7,9}));

        //Optimised Solution - o(n)
        System.out.println("\nResult: "+oddOccurrencesOptimised(new int[]{9,3,9,3,9,7,9}));
    }
}
