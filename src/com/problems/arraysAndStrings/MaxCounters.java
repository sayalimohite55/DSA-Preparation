package com.problems.arraysAndStrings;

import java.util.Arrays;

/*
* Question:
* You are given N counters, initially set to 0, and you have two possible operations on them:
*   increase(X) − counter X is increased by 1,
*   max counter − all counters are set to the maximum value of any counter.
* A non-empty array A of M integers is given. This array represents consecutive operations:
*   if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
*   if A[K] = N + 1 then operation K is max counter.
*
* For example,
* given integer N = 5 and array A such that: [3,4,4,6,1,4,4]
* the values of the counters after each consecutive operation will be:
    (0, 0, 1, 0, 0)
    (0, 0, 1, 1, 0)
    (0, 0, 1, 2, 0)
    (2, 2, 2, 2, 2)
    (3, 2, 2, 2, 2)
    (3, 2, 2, 3, 2)
    (3, 2, 2, 4, 2)
* The goal is to calculate the value of every counter after all operations.
* */
public class MaxCounters {

    public static int[] maxCounterBruteforce(int N, int[] A) {
        int M = A.length;
        int[] result = new int[N];
        int maxVal = 0;

        for(int i =0; i<M; i++) {
            if(A[i] == N+1) {
                //max counter operation
                Arrays.fill(result, maxVal); //This action will take o(n) time
            } else if(A[i] <= N) {
                //increase operation
                int index = A[i]-1;
                result[index] ++;
                if(maxVal < result[index])
                    maxVal = result[index];
            }
        }
        return result;
        /*
        * Time Complexity = o(M * N)
        * Space Complexity = o(1)
        * */
    }

    public static int[] maxCounterOptimised(int N, int[] A) {
        int[] result = new int[N];
        int maxVal = 0, lastMaxToBeUpdated = 0;

        for(int num : A) {
            if(num == N+1) {
                //max counter operation
                lastMaxToBeUpdated = maxVal;
            } else if(num >= 1 && num <= N) {
                //increase operation
                int index = num-1;
                // Apply the max update lazily
                if (result[index] < lastMaxToBeUpdated) {
                    result[index] = lastMaxToBeUpdated;
                }
                result[index] ++;
                if(maxVal < result[index])
                    maxVal = result[index];
            }
        }

        for(int i =0;i<N; i++) {
            if(result[i] < lastMaxToBeUpdated)
                result[i] = lastMaxToBeUpdated;
        }
        return result;
        /*
         * Time Complexity = o(M+N)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 4, 6, 1, 4, 4};
        //Brute Force Solution - o(M * N)
        System.out.println("Result: ");
        Arrays.stream(maxCounterBruteforce(5, arr)).forEach(System.out::print);

        //Optimised Solution - o(M + N)
        System.out.println("\nResult: ");
        Arrays.stream(maxCounterOptimised(5, arr)).forEach(System.out::print);
    }
}
