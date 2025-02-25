package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

/*
 * Question:
 * Given a string write a function to check if is a permutation of a palindrome.
 * A palindrome is a word or phrase that is same forwards and backwards
 * A permutation is a re-arrangement of letters. It does not need to be
 * limited to just dictionary words
 * eg. tact coa -> answer true
 * */
public class PalindromePermutation {
    private static boolean bruteForceSolution(String str) {
        //Intent: Identify all permutations and check if any of it is palindrome
        return checkPalindromePermutation(str, "");
        /*
         * Time Complexity = o(n * n!) * o(n) --> o(n * n!) for permutations & o(n) each time checking palindrome
         *                 = o(n^2 * n!)
         * Space Complexity = o(n);
         * */
    }

    private static boolean checkPalindromePermutation(String str, String prefix) {
        boolean flag = false;
        if(str.isEmpty()) {
            flag = checkPalindrome(prefix);
        } else {
            int n= str.length();
            for(int i=0;i<n;i++){
                String rem = str.substring(0,i)+str.substring(i+1);
                flag = flag || checkPalindromePermutation(rem, prefix + str.charAt(i));
            }
        }
        return flag;
    }

    private static boolean checkPalindrome(String str) {
        int left =0, right = str.length()-1;
        while(left<=right){
            if(str.charAt(left) != str.charAt(right))
                return false;
            left ++;
            right --;
        }
        return true;
    }

    private static boolean checkPalindromePermutationUsingMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        int n = str.length();
        for(int i=0;i<n ;i++) {
            char ch = str.charAt(i);
            if(map.containsKey(ch)) {
                int count = map.get(ch)-1;
                if(count == 0)
                    map.remove(ch);
                else
                    map.put(ch, count);
            } else
                map.put(ch,1);
        }
        return (map.size() <= 1);
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(n);
         * */
    }

    public static void main (String[] args) {
        //Brute Force Solution
        System.out.println(bruteForceSolution(" tact coa"));
        System.out.println(bruteForceSolution(" tact oa"));

        //Solution using extra space o(n) - using HashMap
        System.out.println("\n" + checkPalindromePermutationUsingMap(" tact coa"));
        System.out.println(checkPalindromePermutationUsingMap(" tact oa"));
    }
}
