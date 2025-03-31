package com.problems.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
* Question:
* A DNA sequence can be represented as a string consisting of the letters A, C, G and T,
* which correspond to the types of successive nucleotides in the sequence. Each nucleotide
* has an impact factor, which is an integer. Nucleotides of types A, C, G and T have impact
* factors of 1, 2, 3 and 4, respectively. You are going to answer several queries of the form:
* What is the minimal impact factor of nucleotides contained in a particular part of the given
* DNA sequence?
* The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters.
* There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers.
* The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained
* in the DNA sequence between positions P[K] and Q[K] (inclusive).
*
* For example, consider string S = CAGCCTA and arrays P, Q such that:
    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6
* The answers to these M = 3 queries are as follows:
*   - The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice),
*     whose impact factors are 3 and 2 respectively, so the answer is 2.
*   - The part between positions 5 and 5 contains a single nucleotide T, whose impact factor
*     is 4, so the answer is 4.
*   - The part between positions 0 and 6 (the whole string) contains all nucleotides, in
*     particular nucleotide A whose impact factor is 1, so the answer is 1.
*/
public class GenomicRangeQuery {
    private static int[] genomicRangeQueryBruteForce(String S, int[] P, int[] Q) {
        int n= S.length();
        int[] result = new int[P.length];

        Map<Character, Integer> map = new HashMap<>();
        map.put('A',1);
        map.put('C',2);
        map.put('G',3);
        map.put('T',4);

        for(int i=0;i<P.length; i++) {
            int start = P[i];
            int end = Q[i];
            int min = 5;
            for(int j=start; j<=end; j++) {
                int val = map.get(S.charAt(j));
                if(val < min)
                    min = val;
            }
            result[i] = min;
        }
        return result;
        /*
        * Time Complexity = o(n * m) --> n is length of String and m is length of P
        * Space Complexity = o(1)
        * */
    }

    private static int[] genomicRangeQueryOptimised(String S, int[] P, int[] Q) {
        int n= S.length();
        int[] result = new int[P.length];

        int[] prefixSumA = new int[n+1];
        int[] prefixSumC = new int[n+1];
        int[] prefixSumG = new int[n+1];

        for(int i=0;i<n;i++) {
            char ch = S.charAt(i);
            prefixSumA[i+1] = prefixSumA[i] + (ch == 'A' ? 1 : 0);
            prefixSumC[i+1] = prefixSumC[i] + (ch == 'C' ? 1 : 0);
            prefixSumG[i+1] = prefixSumG[i] + (ch == 'G' ? 1 : 0);
        }

        for(int i=0;i<P.length; i++) {
            int start = P[i];
            int end = Q[i] + 1;

            if(prefixSumA[end] - prefixSumA[start] > 0)
                result[i] = 1;
            else if(prefixSumC[end] - prefixSumC[start] > 0)
                result[i] = 2;
            else if(prefixSumG[end] - prefixSumG[start] > 0)
                result[i] = 3;
            else
                result[i] = 4;
        }
        return result;
        /*
         * Time Complexity = o(n + m) --> n is length of String and m is length of P
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute force Solution - o(n*m)
        System.out.println("Result: "+ Arrays.toString(genomicRangeQueryBruteForce("CAGCCTA", new int[]{2, 5, 0}, new int[]{4, 5, 6})));
        System.out.println("Result: "+ Arrays.toString(genomicRangeQueryBruteForce("CAGTTTCACTA", new int[]{1, 1, 0, 2}, new int[]{2, 1, 4, 4})));

        //Optimised Solution - o(n + m)
        System.out.println("\nResult: "+ Arrays.toString(genomicRangeQueryOptimised("CAGCCTA", new int[]{2, 5, 0}, new int[]{4, 5, 6})));
        System.out.println("Result: "+ Arrays.toString(genomicRangeQueryOptimised("CAGTTTCACTA", new int[]{1, 1, 0, 2}, new int[]{2, 1, 4, 4})));
    }
}
