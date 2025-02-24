package com.problems.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
* Question:
* Implement an algorithm to determine if a string has all unique characters
* Follow up : What if you can not use additional data structures
* */
public class IsUnique {
    private static void bruteForceSolution1(String str) {
        //intent : Compare every character with every other character and check if it is repeated
        int length = str.length();
        for(int i=0; i<length; i++) {
            for(int j=i+1; j<length; j++) {
                if(str.charAt(i) == str.charAt(j)) {
                    System.out.println(str + ": All characters are not unique");
                    return;
                }
            }
        }
        System.out.println(str + ": All characters are unique");
        /*
         * Time Complexity = O(n) * o(n)
         *                 = o(n^2)
         * Space Complexity = o(1)
         * */
    }

    private static void bruteForceSolution2(String str) {
        //intent : search every character and check if it is repeated
        int length = str.length();
        for(int i=0; i<length; i++) {
            char ch = str.charAt(i);
            if(i != str.lastIndexOf(ch)) {
                System.out.println(str + ": All characters are not unique");
                return;
            }
        }
        System.out.println(str + ": All characters are unique");
        /*
        * Time Complexity = O(n) * o(n) -- one for forloop and another for searching for position everytime
        *                 = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static void solutionUsingHashSet(String str) {
        //intent : While traversing array store each character in a set and check if it is repeated
        int length = str.length();
        Set<Character> set = new HashSet<>();
        for(int i=0; i<length; i++) {
            char ch = str.charAt(i);
            if(set.contains(ch)) {
                System.out.println(str + ": All characters are not unique");
                return;
            }
            set.add(ch);
        }
        System.out.println(str + ": All characters are unique");
        /*
         * Time Complexity = O(n) * o(1) -- one for forloop and another for searching for position everytime
         *                 = o(n)
         * Space Complexity = o(n)
         * */
    }

    private static void solutionUsingSorting(String str) {
        //intent : convert string to char array, Sort array and check adjacent characters
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        for(int i=0; i<arr.length-1; i++) {
            if(arr[i] == arr[i+1]) {
                System.out.println(str + ": All characters are not unique");
                return;
            }
        }
        System.out.println(str + ": All characters are unique");
        /*
         * Time Complexity = o(n log n) + o(n) -- o(n log n) for sorting and o(n) for traversal
         *                 = o(n log n)
         * Space Complexity = o(1)
         * */
    }

    private static void solutionUsingBitManipulation(String str) {
        int length = str.length();
        int checker = 0;
        for(int i=0; i<length; i++) {
            int bitAtIndex = str.charAt(i)-'a';
            if((checker & (1<<bitAtIndex)) > 0) {
                System.out.println(str + ": All characters are not unique");
                return;
            }
            checker = checker | 1<<bitAtIndex;
        }
        System.out.println(str + ": All characters are unique");
        /*
         * Time Complexity = o(n) -- o(n) for traversal
         *                 = o(n)
         * Space Complexity = o(1)
         * */
    }

    public static void main (String[] args) {
        //O(n^2) Solution
        System.out.println();
        bruteForceSolution1("abcdefgha");
        bruteForceSolution1("abcdefgh");

        //O(n^2) Solution
        System.out.println();
        bruteForceSolution2("abcdefgha");
        bruteForceSolution2("abcdefgh");

        //Optimised solution using extra memory : O(n)
        System.out.println();
        solutionUsingHashSet("abcdefgha");
        solutionUsingHashSet("abcdefgh");

        //Optimised solution without using extra memory : O(nlogn)
        System.out.println();
        solutionUsingSorting("abcdefgha");
        solutionUsingSorting("abcdefgh");

        //Optimised solution without using extra space - bit vector manipulation: 0(n)
        System.out.println();
        solutionUsingBitManipulation("abcdefgha");
        solutionUsingBitManipulation("abcdefgh");
    }
}
