package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

/*
* Question:
* Given a string write a function to check if is a permutation of a palindrome.
* A palindrome is a word or phrase that is same forwards and backwards
* A permutation is a re-arrangement of letters. It does not need to be
* limited to just dictionary words
*
* In short, identify if a given string is a permutation of a palindrome.
* eg. tact coa -> answer true
* */
public class PalindromePermutation {
    private static boolean bruteForceSolution(String str) {
        /*
        * Intent: Identify all permutations and check if at least one of it is palindrome
        *         You don't need to find rest of permutations
        *         if first palindromic permutation is found
        * */

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
                //We do not need to generate all permutations
                if(flag)
                    return flag;
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
        /*
        * Intent: A permutation is a different sequence of same set of characters.
        *         Any string is a palindrome if all characters in string has
        *         even number of occurrences and
        *         only one character can have occurrence 1 if length of string is odd.
        *
        *         Use Hashmap to store list of characters and their count of occurrences
        * */

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

    private static boolean solutionUsingBitManipulation(String str) {
        /*
        * Intent : Assuming that str has a-z characters
        *          hence taking bitmask variable as 32 bits representation
        *          We will set each bit for every alphabet w.r.t 'a'
        *          if a bit is set for a char, we will reset it pack to ensure even occurrences
        * */

        int length = str.length();
        int bitmask = 0;
        for(int i=0; i<length; i++) {
            // Toggle the bit corresponding to the character
            bitmask ^= (1 << (str.charAt(i) - 'a'));
        }
        // Check if at most one bit is set in the bitmask
        return (bitmask & (bitmask - 1)) == 0;

        /*
        * Time Complexity = o(n) -- o(n) for traversal
        *                 = o(n)
        * Space Complexity = o(1)
        * */
    }

    public static void main (String[] args) {
        //Brute Force Solution 0(n^2 * n!)
        System.out.println(bruteForceSolution(" tact coa"));
        System.out.println(bruteForceSolution(" tact oa"));

        //Solution using extra space o(n) - using HashMap
        System.out.println("\n" + checkPalindromePermutationUsingMap(" tact coa"));
        System.out.println(checkPalindromePermutationUsingMap(" tact oa"));

        //Optimised solution using bit vector - o(n)
        System.out.println("\n" + solutionUsingBitManipulation(" tact coa"));
        System.out.println(solutionUsingBitManipulation(" tact oa"));
    }
}
